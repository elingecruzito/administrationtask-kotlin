package com.developbyte.administrationtask.Model

data class DaysMountModel(var day:Int, var day_text:String, var isToday:Boolean) {
    var selected:Boolean = false
}