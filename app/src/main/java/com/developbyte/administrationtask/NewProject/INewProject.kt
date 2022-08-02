package com.developbyte.administrationtask.NewProject

import com.developbyte.administrationtask.Model.TasksModel


interface INewProject {
    //Comunica de MasterBussinesController a BussinesController
    interface INewProjectTransactionHandler {
        fun startNewProject()
    }

    //Comunica de BussinesController a MasterBussinesController
    interface INewProjectTransactionDelegate {
        
    }

    //Comunica de BusinessController a ViewController
    interface INewProjectRepresentationHandler {
        fun showNewProject()
    }

    //Comunica de Service a BusinessComtroller
    interface INewProjectInformationDelegate {

    }

    //Comunica de BusinessController a Service
    interface INewProjectInformationHandler{
        fun createNewProject(name: String?, tasksModelList: List<TasksModel?>?)
    }

    //Comunica de ViewController a Businnes
    interface INewProjectRepresentationDelegate {
        fun createNewProject(name: String?, tasksModelList: List<TasksModel?>?)
    }
}