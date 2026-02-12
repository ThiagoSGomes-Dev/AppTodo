package com.app.apptodo.apptodo.addtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.apptodo.data.Task
import com.app.apptodo.databinding.FragmentInputBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.core.view.isGone

class TodoAddTaskFragment: BottomSheetDialogFragment(), TodoAddTaskContract.View {

    private val presenter: TodoAddTaskContract.Presenter by lazy {
        TodoAddTaskPresenter(this, AddTaskRepositoryImplementation())
    }
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

        with (binding) {

            btnButton.setOnClickListener {
                val title = inputText.text.toString()
                val description = inputTextDescription.text.toString().ifBlank { null }
                val isFavorite = rbIsFavoriteBottomSheet.isChecked

                val task = Task(name = title, description = description, isFavorite = isFavorite)
                dismiss()
                presenter.onAddTaskClicked(task, description, isFavorite)
            }

            btnDescription.setOnClickListener {
                if (inputLayoutDescription.isGone) {
                    inputLayoutDescription.visibility = View.VISIBLE
                } else {
                    inputLayoutDescription.visibility = View.GONE
                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        presenter.onDestroyView()
    }
}