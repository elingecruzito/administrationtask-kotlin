package com.developbyte.administrationtask.NewProject

import com.developbyte.administrationtask.Abstract.AbstractBusinessController

class NewProjectBusinessController: AbstractBusinessController(), INewProject.INewProjectRepresentationDelegate,
    INewProject.INewProjectTransactionHandler,
    INewProject.INewProjectInformationDelegate {

    var representationHandler: INewProject.INewProjectRepresentationHandler? = null
    var transactionDelegate: INewProject.INewProjectTransactionDelegate? = null
    var informationHandler: INewProject.INewProjectInformationHandler? = null

    override fun startNewProject() {
        representationHandler?.showNewProject()
    }

    

}