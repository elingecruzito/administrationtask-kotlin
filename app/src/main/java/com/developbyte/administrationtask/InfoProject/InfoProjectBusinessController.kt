package com.developbyte.administrationtask.InfoProject

import com.developbyte.administrationtask.Abstract.AbstractBusinessController

class InfoProjectBusinessController: AbstractBusinessController(), IInfoProject.IInfoProjectRepresentationDelegate,
    IInfoProject.IInfoProjectTransactionHandler,
    IInfoProject.IInfoProjectInformationDelegate {

    var representationHandler: IInfoProject.IInfoProjectRepresentationHandler? = null
    var transactionDelegate: IInfoProject.IInfoProjectTransactionDelegate? = null
    var informationHandler: IInfoProject.IInfoProjectInformationHandler? = null

    override fun startInfoProject() {
        representationHandler?.showInfoProject()
    }

    

}