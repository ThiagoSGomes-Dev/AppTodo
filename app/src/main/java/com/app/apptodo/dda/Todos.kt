package com.app.apptodo.dda

data class Tasks (
    var name: String = ""
)

object DbData {
    val data = mutableListOf<Tasks>()
}

class Todos {

    val objs = listOf(
        Tasks(
            "Compas"
        ),
        Tasks(
            "Vendas"
        )
    )

    val listas = DbData.data

    fun addItem(item: Tasks) {
        listas.add(item)
    }

    fun getList(): MutableList<Tasks> = DbData.data
}

fun main() {
    val todo = Todos()

    val task1 = Tasks()
    val task2 = Tasks()

    task1.name = "Thiago"
    task2.name = "Jéssica"

    todo.addItem(task1)
    todo.addItem(task2)

    todo.getList().forEach {
        println(it.name)
    }



}
