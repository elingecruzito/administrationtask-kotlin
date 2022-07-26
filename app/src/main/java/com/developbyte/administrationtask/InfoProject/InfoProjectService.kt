package com.developbyte.administrationtask.InfoProject

import com.developbyte.administrationtask.Abstract.AbstractService

class InfoProjectService : AbstractService(), IInfoProject.IInfoProjectInformationHandler {

    var informationDelegate : IInfoProject.IInfoProjectInformationDelegate? = null

}