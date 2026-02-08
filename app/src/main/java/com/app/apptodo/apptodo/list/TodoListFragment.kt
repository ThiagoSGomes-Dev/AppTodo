package com.app.apptodo.apptodo.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.apptodo.R
import com.app.apptodo.data.Task
import com.app.apptodo.apptodo.list.TodoListAdapter
import com.app.apptodo.apptodo.addtask.TodoAddTaskFragment
import com.app.apptodo.data.AppTodoRepositoryImplementation
import com.app.apptodo.databinding.FragmentTaskBinding

class TodoListFragment: Fragment(), TodoListContract.View {
    private val presenter: TodoListPresenter by lazy {
        TodoListPresenter(this, AppTodoRepositoryImplementation())
    }
    private lateinit var adapter: TodoListAdapter
    private var _binding: FragmentTaskBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         _binding = FragmentTaskBinding.inflate(
            layoutInflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = TodoListAdapter(
            onLongClick = { task ->
                presenter.removeTaskLongClick(task)
            }
        )

        binding.recyclerList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapter
        }

        presenter.loadTasks()

        with(binding) {
            btnTask.setOnClickListener {
                presenter.onAddTaskButtonClicked()
            }
        }
    }

    override fun showTaskRemoved(task: Task) {
        adapter.removeTask(task)
    }

    override fun returnTasks(tasks: List<Task>) {
        adapter.setTask(tasks)
    }

    override fun navigateToAddTaskFragment() {
        parentFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            TodoAddTaskFragment()
        ).addToBackStack(null).commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
