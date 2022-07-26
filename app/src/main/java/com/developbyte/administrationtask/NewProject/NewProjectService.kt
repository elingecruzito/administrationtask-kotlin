package com.developbyte.administrationtask.NewProject

import com.developbyte.administrationtask.Abstract.AbstractService

class NewProjectService : AbstractService(), INewProject.INewProjectInformationHandler {

    var informationDelegate : INewProject.INewProjectInformationDelegate? = null

}