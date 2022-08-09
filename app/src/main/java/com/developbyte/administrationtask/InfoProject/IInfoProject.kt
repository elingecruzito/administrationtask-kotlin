package com.developbyte.administrationtask.InfoProject

import com.developbyte.administrationtask.Model.ProjectModel
import com.developbyte.administrationtask.Model.TasksModel


interface IInfoProject {
    //Comunica de MasterBussinesController a BussinesController
    interface IInfoProjectTransactionHandler {
        fun startInfoProject(idTask:Int?)
    }

    //Comunica de BussinesController a MasterBussinesController
    interface IInfoProjectTransactionDelegate {
        
    }

    //Comunica de BusinessController a ViewController
    interface IInfoProjectRepresentationHandler {
        fun showInfoProject(idTask:Int?)
        fun setDataProject(project: ProjectModel?)
        fun setAllProgressTask(progressTask: List<TasksModel?>?)
        fun setAllCompleteTask(completeTask: List<TasksModel?>?)
        fun setInsertTask(task: TasksModel?)
        fun updateStatusTaskResult(ready: Boolean)
        fun deleteTask(ready: Boolean)
    }

    //Comunica de Service a BusinessComtroller
    interface IInfoProjectInformationDelegate {
        fun setDataProject(project: ProjectModel?)
        fun setAllProgressTask(progressTask: List<TasksModel?>?)
        fun setAllCompleteTask(completeTask: List<TasksModel?>?)
        fun setInsertTask(task: TasksModel?)
        fun updateStatusTaskResult(ready: Boolean)
        fun deleteTask(ready: Boolean)
    }

    //Comunica de BusinessController a Service
    interface IInfoProjectInformationHandler{
        fun getDataProject(id: Int)
        fun getAllProgressTask(id: Int)
        fun getAllCompleteTask(id: Int)
        fun createNewTask(tasksModel: TasksModel?)
        fun updateStatusTask(idtask: Int)
        fun deleteTask(idtask: Int)
    }

    //Comunica de ViewController a Businnes
    interface IInfoProjectRepresentationDelegate {
        fun getDataProject(id: Int)
        fun getAllProgressTask(idProject: Int)
        fun getAllCompleteTask(idProject: Int)
        fun createNewTask(tasksModel: TasksModel?)
        fun updateStatusTask(idtask: Int)
        fun deleteTask(idtask: Int)
    }
}