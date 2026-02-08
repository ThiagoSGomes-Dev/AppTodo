package com.app.apptodo.apptodo.addtask

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.app.apptodo.data.Task
import com.app.apptodo.data.AppTodoRepositoryImplementation
import com.app.apptodo.apptodo.list.TodoListAdapter
import com.app.apptodo.databinding.FragmentInputBinding

class TodoAddTaskFragment: Fragment(), TodoAddTaskContract.View {

    private val presenter: TodoAddTaskContract.Presenter by lazy {
        TodoAddTaskPresenter(this, AppTodoRepositoryImplementation())
    }
    private lateinit var adapter : TodoListAdapter
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

        adapter = TodoListAdapter()

        with (binding) {
            btnButton.setOnClickListener {
                val title = inputText.text.toString()
                val task = Task(name = title)
                presenter.onAddTaskClicked(task)

                Log.d("Click", "${task.id}, ${task.name}")
                Toast.makeText(context,"Task created!", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    override fun navigateToListFragment() {
        parentFragmentManager.popBackStack()
    }

    override fun showAddTask(task: Task) {
        adapter.addTask(task)
    }
}