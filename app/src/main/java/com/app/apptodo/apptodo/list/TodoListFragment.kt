package com.app.apptodo.apptodo.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.apptodo.R
import com.app.apptodo.apptodo.addtask.TodoAddTaskFragment
import com.app.apptodo.apptodo.edit.TodoEditTaskFragment
import com.app.apptodo.data.Task
import com.app.apptodo.databinding.FragmentTaskBinding
import io.reactivex.rxjava3.disposables.CompositeDisposable

class TodoListFragment: Fragment(), TodoListContract.View {

    private val disposable = CompositeDisposable()

    private val presenter: TodoListPresenter by lazy {
        TodoListPresenter(this, ListRepositoryImplementation(), disposable)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        presenter.onDestroyView()
    }

    override fun onResume() {
        super.onResume()
        presenter.loadTasks()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = TodoListAdapter(
            onClick = presenter::onTaskClicked,
            onLongClick = presenter::onTaskLongClicked,
            onClickIsFavorite = { isFavorite ->
                presenter.toggleFavorite(isFavorite)
            },
            onEditView = presenter::onClickedEditView
        )

        with(binding) {

            recyclerList.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = this@TodoListFragment.adapter
            }

            btnTask.setOnClickListener {
                presenter.onAddTaskButtonClicked()
            }
        }
    }

    override fun showTaskRemoved(task: Task) {
        adapter.removeTask(task)
    }

    override fun bindEmptyState() {
        binding.apply {
            recyclerList.visibility = View.GONE
            clEmptyState.visibility = View.VISIBLE

            emptyImage.setImageResource(R.drawable.emptyimage)
            tvEmptyState.text = getString(R.string.nao_ha_tarefas_ainda)
            tvEmptyStateH2.text = getString(R.string.adicione_tarefas)
        }
    }

    override fun showTaskUpDate(task: Task) {
        binding.apply {
            adapter.updateTask(task)
        }
    }

    override fun navigationToEdit(taskId: Int) {
        parentFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            TodoEditTaskFragment.newInstance(
                taskId = taskId,
                onTaskUpDated = { updatedTask ->
                    adapter.updateTask(updatedTask)
                }
            )
        ).addToBackStack(null).commit()
    }

    override fun showIsFavoriteUpdate(task: Task) {
        adapter.updateTask(task)
    }

    override fun returnTasks(tasks: List<Task>) {
        binding.apply {
            clEmptyState.visibility = View.GONE
            recyclerList.visibility = View.VISIBLE

            adapter.setTasks(tasks)
        }
    }

    override fun navigateToAddTaskFragment() {
        TodoAddTaskFragment().show(parentFragmentManager, "add_task_sheet")
    }
}
