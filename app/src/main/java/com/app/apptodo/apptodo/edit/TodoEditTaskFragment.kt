package com.app.apptodo.apptodo.edit

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.app.apptodo.data.Task
import com.app.apptodo.databinding.FragmentEditBinding
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.doOnTextChanged
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class TodoEditTaskFragment: Fragment(), TodoEditTaskContract.View {
    private val taskId: Int get() = requireArguments().getInt(ARG_ID)
    private var onTaskUpDate: ((Task) -> Unit)? = null
    private val presenter: TodoEditTaskPresenter by lazy {
        TodoEditTaskPresenter(this, TodoEditTaskRepositoryImplementation())
    }
    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditBinding.inflate(
            layoutInflater,
            container,
            false
        )

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        presenter.onDestroyView()
    }

    override fun onResume() {
        super.onResume()
        presenter.loadTask(taskId)
    }


    override fun showBindTask(task: Task) {
        // registerBackPressed(task)
        binding.apply {
            textInputEditText.setText(task.name)
            textInputEditTextDesc.setText(task.description)

            observeTextChanges(task)
            btnBackUpDate.setOnClickListener {
                // saveTask(task)
                val updateTask = task.copy(
                    name = textInputEditText.text.toString(),
                    description = textInputEditTextDesc.text.toString()
                )
                // presenter.upDateTask(updateTask)
            }
        }
    }

    private fun observeTextChanges(task: Task) {
        val nameChanges = binding.textInputEditText.textChanges()
        val descChanges = binding.textInputEditTextDesc.textChanges()

        Observable
            .combineLatest(nameChanges, descChanges) { name, desc ->
                task.copy(
                    name = name.toString(),
                    description = desc.toString()
                )
            }
            .skip(1)
            .debounce(500, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { updatedTask ->
                presenter.upDateTask(updatedTask)
            }
    }

    private fun saveTask(task: Task) {
        val updateTask = task.copy(
            name = binding.textInputEditText.text.toString(),
            description = binding.textInputEditTextDesc.text.toString()
        )
        presenter.upDateTask(updateTask)
    }

    private fun registerBackPressed(task: Task) {
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {

                override fun handleOnBackPressed() {
                    saveTask(task)
                }
            })
    }

    override fun closeFragment() {
       // onTaskUpDated(upDateTask)
       parentFragmentManager.popBackStack()
    }

    override fun onTaskUpDated(task: Task) {
        onTaskUpDate?.invoke(task)
    }

    companion object {
        private const val ARG_ID = "task_id"
        fun newInstance(
            taskId: Int,
            onTaskUpDated: (Task) -> Unit
        ) = TodoEditTaskFragment().apply {
            val fragment = TodoEditTaskFragment()
            fragment.arguments = bundleOf(
                ARG_ID to taskId
            )
            fragment.onTaskUpDate = onTaskUpDated

            return fragment
        }
    }

}