package com.developbyte.administrationtask.Widgets

import com.developbyte.administrationtask.Adapters.ListNewTaskAdapter
import com.developbyte.administrationtask.Model.TasksModel

class RunnableWidgetAddItemList: Runnable {

    var listNewTaskAdapter: ListNewTaskAdapter? = null
    var name: String? = null
    var date: String? = null
    var hour: String? = null

    override fun run() {
        val taskModel: TasksModel? = null
        taskModel!!.task = name
        taskModel!!.date = date
        taskModel!!.hour = hour
        listNewTaskAdapter!!.setTasksModel( taskModel )
    }

}