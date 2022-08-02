package com.developbyte.administrationtask.Model

object TasksModel {

    var id_task = 0
    var task: String? = null
    var id_project = 0
    var project: String? = null
    var hour: String? = null
    var date: String? = null
    var status = 0

    val STATUS_IN_PROGRESS = 0
    val STATUS_COMPLETE = 1

    /*
    constructor(){

    }

    constructor(id_task: Int, task: String?, id_project: Int, project: String?, hour: String?, date: String?, status: Int) {
        this.id_task = id_task
        this.task = task
        this.id_project = id_project
        this.project = project
        this.hour = hour
        this.date = date
        this.status = status
    }
    */
}