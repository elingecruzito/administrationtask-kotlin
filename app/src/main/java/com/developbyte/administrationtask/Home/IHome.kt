package com.developbyte.administrationtask.Home

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
    }

    //Comunica de Service a BusinessComtroller
    interface IHomeInformationDelegate {

    }

    //Comunica de BusinessController a Service
    interface IHomeInformationHandler{

    }

    //Comunica de ViewController a Businnes
    interface IHomeRepresentationDelegate {
        fun showInfoProject()
        fun showNewProject()
        
    }
}