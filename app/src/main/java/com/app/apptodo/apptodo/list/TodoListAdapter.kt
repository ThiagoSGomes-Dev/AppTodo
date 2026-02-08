package com.app.apptodo.apptodo.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.apptodo.data.Task
import com.app.apptodo.databinding.ListItemsBinding

class TodoListAdapter(private val onLongClick: (Task) -> Unit = {} ): RecyclerView.Adapter<TodoListAdapter.AppTodoViewHold>() {

    private val task = mutableListOf<Task>()

    fun setTask(newTask: List<Task>) {
        task.clear()
        task.addAll(newTask)
        notifyDataSetChanged()
    }

    fun addTask(newTask: Task) {
        task.add(newTask)
        notifyItemInserted(task.size -1)
    }

    fun removeTask(newTask: Task) {
        val index = task.indexOfFirst { taskId -> taskId.id == newTask.id }
        if (index != -1) {
            task.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    inner class AppTodoViewHold(private val binding: ListItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.apply {
                textviewItem.text = task.name
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

    override fun getItemCount(): Int = task.size

    override fun onBindViewHolder( holder: AppTodoViewHold, position: Int ) {
        val task = task[position]
        holder.bind(task)
    }
}