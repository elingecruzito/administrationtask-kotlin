package com.developbyte.administrationtask.Model

data class TasksModel(val task: String, val hour: String, val date: String) {
    var id_task:Int? = null
//    var task: String? = null
    var id_project:Int? = null
    var project: String? = null
//    var hour: String? = null
//    var date: String? = null
    var status:Int = 0
}