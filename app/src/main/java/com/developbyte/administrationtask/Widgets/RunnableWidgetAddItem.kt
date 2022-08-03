package com.developbyte.administrationtask.Widgets

import com.developbyte.administrationtask.InfoProject.IInfoProject.IInfoProjectRepresentationDelegate
import com.developbyte.administrationtask.Model.TasksModel


class RunnableWidgetAddItem: Runnable {

    var tasksModel: TasksModel? = null
    var representationDelegate: IInfoProjectRepresentationDelegate? = null

    override fun run() {
        representationDelegate?.createNewTask(tasksModel)
    }

}