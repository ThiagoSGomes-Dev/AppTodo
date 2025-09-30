package com.app.apptodo.apptodo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.app.apptodo.AppTodoRepositoryImplementation
import com.app.apptodo.R
import com.app.apptodo.databinding.FragmentInputBinding

class FragmentInput: Fragment(), AppTodoContract.View {

    private lateinit var adaptor : AppTodoAdaptor
    private var _binding : FragmentInputBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInputBinding.inflate(
            layoutInflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val presenter = AppTodoPresenter(this, AppTodoRepositoryImplementation())

        adaptor = AppTodoAdaptor(requireContext(), mutableListOf())

        with (binding) {
            btnButton.setOnClickListener {
                val task = binding.inputText.text.toString()
                presenter.addTask(task)

                Toast.makeText(context,"Task created!", Toast.LENGTH_SHORT).show()

                // PopBackStack
                parentFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    FragmentTask()
                ).commit()

            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    override fun returnTasks(tasks: MutableList<String>) {
        adaptor.updateData(tasks)
    }


}