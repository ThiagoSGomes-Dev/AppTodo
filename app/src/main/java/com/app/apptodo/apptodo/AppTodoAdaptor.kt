package com.app.apptodo.apptodo

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.app.apptodo.databinding.ListItemsBinding

class AppTodoAdaptor(private val context: Context, private var tasks: MutableList<Task>): RecyclerView.Adapter<AppTodoAdaptor.AppTodoViewHold>() {
    // TODO: Espera a classe atual, e uma Classe interna a atual, que extende o ViewHolder. VH <ClasseAtual.ClasseInterna>

    fun updateData(newTasks: MutableList<Task>) {
        tasks = newTasks
    }

    inner class AppTodoViewHold(private val listItemsBinding: ListItemsBinding) : ViewHolder(listItemsBinding.root) {
        fun bind(task: Task) {
            listItemsBinding.textviewItem.text = task.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int ): AppTodoViewHold {
        return AppTodoViewHold(
            ListItemsBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder( holder: AppTodoViewHold, position: Int ) {
        val task = tasks[position]
        holder.bind(task)
    }

}
