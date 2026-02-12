package com.app.apptodo.apptodo.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.app.apptodo.apptodo.edit.TodoEditTaskFragment
import com.app.apptodo.data.Task
import com.app.apptodo.databinding.ListItemsBinding

class TodoListAdapter(
    private val onLongClick: (Task) -> Unit = {},
    private val onClick: (Task) -> Unit = {},
    private val onClickIsFavorite: (Task) -> Unit = {},
    private val onEditView: (taskId: Int) -> Unit = {}
): RecyclerView.Adapter<TodoListAdapter.AppTodoViewHold>() {

    private val tasks = mutableListOf<Task>()

    fun setTasks(newTask: List<Task>) {
        tasks.clear()
        tasks.addAll(newTask)
        notifyItemRangeChanged(0, newTask.size)
    }

    fun updateTask(task: Task) {
        val index = tasks.indexOfFirst { it.id == task.id }
        tasks[index] = task
        notifyItemChanged(index)
    }

    fun removeTask(newTask: Task) {
        val index = tasks.indexOfFirst { taskId -> taskId.id == newTask.id }
        if (index != -1) {
            tasks.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    inner class AppTodoViewHold(private val binding: ListItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.apply {
                textviewItem.text = task.name
                if (task.description.isNullOrEmpty()) {
                    textviewItemDescription.visibility = View.GONE
                } else {
                    textviewItemDescription.visibility = View.VISIBLE
                    textviewItemDescription.text = task.description
                }
                checkboxItem.isChecked = task.isCompleted
                ivFavorite.isChecked = task.isFavorite

                root.setOnClickListener {
                    onEditView(task.id)
                }

                ivFavorite.setOnClickListener {
                    onClickIsFavorite(task)
                }

                checkboxItem.setOnClickListener {
                    onClick(task)
                }
                root.setOnLongClickListener {
                    onLongClick(task)
                    true
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int ): AppTodoViewHold =
        AppTodoViewHold(
            ListItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder( holder: AppTodoViewHold, position: Int ) {
        val task = tasks[position]
        holder.bind(task)
    }
}