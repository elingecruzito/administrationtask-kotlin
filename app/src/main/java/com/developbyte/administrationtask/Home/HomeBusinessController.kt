package com.developbyte.administrationtask.Home

import com.developbyte.administrationtask.Abstract.AbstractBusinessController

class HomeBusinessController: AbstractBusinessController(), IHome.IHomeRepresentationDelegate,
    IHome.IHomeTransactionHandler,
    IHome.IHomeInformationDelegate {

    var representationHandler: IHome.IHomeRepresentationHandler? = null
    var transactionDelegate: IHome.IHomeTransactionDelegate? = null
    var informationHandler: IHome.IHomeInformationHandler? = null

    override fun startHome() {
        representationHandler?.showHome()
    }

    override fun showInfoProject() {
        transactionDelegate?.showInfoProject()
    }

    override fun showNewProject() {
        transactionDelegate?.showNewProject()
    }

    

}