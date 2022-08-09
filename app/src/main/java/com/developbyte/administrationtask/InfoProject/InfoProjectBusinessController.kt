package com.developbyte.administrationtask.InfoProject

import com.developbyte.administrationtask.Abstract.AbstractBusinessController
import com.developbyte.administrationtask.Model.ProjectModel
import com.developbyte.administrationtask.Model.TasksModel


class InfoProjectBusinessController: AbstractBusinessController(), IInfoProject.IInfoProjectRepresentationDelegate,
    IInfoProject.IInfoProjectTransactionHandler,
    IInfoProject.IInfoProjectInformationDelegate {

    var representationHandler: IInfoProject.IInfoProjectRepresentationHandler? = null
    var transactionDelegate: IInfoProject.IInfoProjectTransactionDelegate? = null
    var informationHandler: IInfoProject.IInfoProjectInformationHandler? = null

    override fun startInfoProject(idTask:Int?) {
        representationHandler?.showInfoProject(idTask)
    }

    override fun getDataProject(id: Int) {
        informationHandler!!.getDataProject(id)
    }

    override fun getAllProgressTask(idProject: Int) {
        informationHandler!!.getAllProgressTask(idProject)
    }

    override fun getAllCompleteTask(idProject: Int) {
        informationHandler!!.getAllCompleteTask(idProject)
    }

    override fun createNewTask(tasksModel: TasksModel?) {
        informationHandler!!.createNewTask(tasksModel)
    }

    override fun updateStatusTask(idtask: Int?) {
        informationHandler!!.updateStatusTask(idtask)
    }

    override fun deleteTask(idtask: Int?) {
        informationHandler!!.deleteTask(idtask)
    }

    override fun setDataProject(project: ProjectModel?) {
        representationHandler!!.setDataProject(project)
    }

    override fun setAllProgressTask(progressTask: List<TasksModel?>?) {
        representationHandler!!.setAllProgressTask(progressTask)
    }

    override fun setAllCompleteTask(completeTask: List<TasksModel?>?) {
        representationHandler!!.setAllCompleteTask(completeTask)
    }

    override fun setInsertTask(task: TasksModel?) {
        representationHandler!!.setInsertTask(task)
    }

    override fun updateStatusTaskResult(ready: Boolean) {
        representationHandler!!.updateStatusTaskResult(ready)
    }

    override fun deleteTask(ready: Boolean) {
        representationHandler!!.deleteTask(ready)
    }
}