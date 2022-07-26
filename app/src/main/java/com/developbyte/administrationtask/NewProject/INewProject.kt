package com.developbyte.administrationtask.NewProject

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

    }

    //Comunica de ViewController a Businnes
    interface INewProjectRepresentationDelegate {
        
    }
}