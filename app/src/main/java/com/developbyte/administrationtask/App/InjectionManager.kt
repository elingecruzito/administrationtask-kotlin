package com.developbyte.administrationtask.App

import com.developbyte.administrationtask.Home.HomeBusinessController
import com.developbyte.administrationtask.Home.HomeMasterViewController
import com.developbyte.administrationtask.Home.HomeService
import com.developbyte.administrationtask.Home.HomeViewController
import com.developbyte.administrationtask.InfoProject.InfoProjectBusinessController
import com.developbyte.administrationtask.InfoProject.InfoProjectService
import com.developbyte.administrationtask.InfoProject.InfoProjectViewController
import com.developbyte.administrationtask.NewProject.NewProjectBusinessController
import com.developbyte.administrationtask.NewProject.NewProjectService
import com.developbyte.administrationtask.NewProject.NewProjectViewController


class InjectionManager {

    private val masterBusinessController = MasterBusinessController()

    fun startHome(homeMasterViewController : HomeMasterViewController){

        val infoprojectViewController = InfoProjectViewController()
        val infoprojectBusinessController = InfoProjectBusinessController()
        val infoprojectService = InfoProjectService()

        infoprojectBusinessController.representationHandler = infoprojectViewController
        infoprojectBusinessController.transactionDelegate = masterBusinessController
        infoprojectBusinessController.informationHandler = infoprojectService

        infoprojectViewController.tag = homeMasterViewController.INFOPROJECT_CONTROLLER
        infoprojectViewController.representationDelegate = infoprojectBusinessController
        infoprojectViewController.masterViewController = homeMasterViewController

        infoprojectService.informationDelegate = infoprojectBusinessController

        homeMasterViewController.addFragment(infoprojectViewController)
        masterBusinessController.infoprojectController = infoprojectBusinessController

        //-----------------------------------------------------------------------------------------

        val newprojectViewController = NewProjectViewController()
        val newprojectBusinessController = NewProjectBusinessController()
        val newprojectService = NewProjectService()

        newprojectBusinessController.representationHandler = newprojectViewController
        newprojectBusinessController.transactionDelegate = masterBusinessController
        newprojectBusinessController.informationHandler = newprojectService

        newprojectViewController.tag = homeMasterViewController.NEWPROJECT_CONTROLLER
        newprojectViewController.representationDelegate = newprojectBusinessController
        newprojectViewController.masterViewController = homeMasterViewController

        newprojectService.informationDelegate = newprojectBusinessController

        homeMasterViewController.addFragment(newprojectViewController)
        masterBusinessController.newprojectController = newprojectBusinessController

        //-----------------------------------------------------------------------------------------

        val homeViewController = HomeViewController()
        val homeBusinessController = HomeBusinessController()
        val homeService = HomeService()

        homeBusinessController.representationHandler = homeViewController
        homeBusinessController.transactionDelegate = masterBusinessController
        homeBusinessController.informationHandler = homeService

        homeViewController.tag = homeMasterViewController.HOME_CONTROLLER
        homeViewController.representationDelegate = homeBusinessController
        homeViewController.masterViewController = homeMasterViewController

        homeService.informationDelegate = homeBusinessController

        homeMasterViewController.addFragment(homeViewController)
        masterBusinessController.homeController = homeBusinessController

        //-----------------------------------------------------------------------------------------



        masterBusinessController.homeInit()

    }



}