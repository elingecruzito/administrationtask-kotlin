package com.developbyte.administrationtask.App

import com.developbyte.administrationtask.Home.IHome
import com.developbyte.administrationtask.InfoProject.IInfoProject
import com.developbyte.administrationtask.NewProject.INewProject


class MasterBusinessController: IHome.IHomeTransactionDelegate,
                                IInfoProject.IInfoProjectTransactionDelegate,
                                INewProject.INewProjectTransactionDelegate{

    var homeController: IHome.IHomeTransactionHandler? = null
    var infoprojectController: IInfoProject.IInfoProjectTransactionHandler? = null
    var newprojectController: INewProject.INewProjectTransactionHandler? = null


    fun homeInit(){
        homeController?.startHome()
    }

    override fun showInfoProject(idTask:Int?) {
        infoprojectController?.startInfoProject(idTask)
    }

    override fun showNewProject() {
        newprojectController?.startNewProject()
    }

}