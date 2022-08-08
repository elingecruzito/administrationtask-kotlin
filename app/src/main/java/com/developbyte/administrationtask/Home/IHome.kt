package com.developbyte.administrationtask.Home

import com.developbyte.administrationtask.Model.DaysMountModel
import com.developbyte.administrationtask.Model.MonthsModel
import com.developbyte.administrationtask.Model.TasksModel


interface IHome {
    //Comunica de MasterBussinesController a BussinesController
    interface IHomeTransactionHandler {
        fun startHome()
    }

    //Comunica de BussinesController a MasterBussinesController
    interface IHomeTransactionDelegate {
        fun showInfoProject()
        fun showNewProject()
        
    }

    //Comunica de BusinessController a ViewController
    interface IHomeRepresentationHandler {
        fun showHome(): Boolean
        fun setDaysOfCurrentMount(daysOfCurrentMount: MutableList<DaysMountModel>)
        fun setTaskInProgress(taskInProgress: MutableList<TasksModel>)
        fun setTaskComplete(taskComplete: MutableList<TasksModel>)
        fun setMonthList(monthList: MutableList<MonthsModel>)
        fun updateStatusTaskResult(ready: Boolean)
        fun deleteTaskResult(ready: Boolean)
    }

    //Comunica de Service a BusinessComtroller
    interface IHomeInformationDelegate {
        fun setTaskInProgress(taskInProgress: MutableList<TasksModel>)
        fun setTaskComplete(taskComplete: MutableList<TasksModel>)
        fun updateStatusTaskResult(ready: Boolean)
        fun deleteTaskResult(ready: Boolean)
    }

    //Comunica de BusinessController a Service
    interface IHomeInformationHandler{
        fun getTaskInProgress(date: String?)
        fun getTaskComplete(date: String?)
        fun updateStatusTask(idTask: Int)
        fun deleteTask(idTask: Int)
    }

    //Comunica de ViewController a Businnes
    interface IHomeRepresentationDelegate {
        fun showInfoProject()
        fun showNewProject()
        fun getDaysOfCurrentMount(mount: Int)
        fun getTaskInProgress(date: String?)
        fun getTaskComplete(date: String?)
        fun getMonthsList()
        fun updateStatusTask(idTask: Int)
        fun deleteTask(idTask: Int)
    }
}