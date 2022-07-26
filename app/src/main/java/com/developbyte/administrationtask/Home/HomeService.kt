package com.developbyte.administrationtask.Home

import com.developbyte.administrationtask.Abstract.AbstractService

class HomeService : AbstractService(), IHome.IHomeInformationHandler {

    var informationDelegate : IHome.IHomeInformationDelegate? = null

}