package com.developbyte.administrationtask.NewProject

import android.R.attr
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.developbyte.administrationtask.Abstract.AbstractService
import com.developbyte.administrationtask.DataBase.Entrys.ProjectEntry
import com.developbyte.administrationtask.DataBase.Entrys.TaskAdministrationDBHelper
import com.developbyte.administrationtask.DataBase.Entrys.TaskEntry
import com.developbyte.administrationtask.Model.TasksModel


class NewProjectService : AbstractService(), INewProject.INewProjectInformationHandler {

    var informationDelegate : INewProject.INewProjectInformationDelegate? = null

    var parameters = ContentValues()
    var context: Context? = null

    var dbHelper: TaskAdministrationDBHelper? = null
    var db: SQLiteDatabase? = null
    var idRow: Long? = null

    override fun createNewProject(name: String?, tasksModelList: List<TasksModel?>?) {
        if (context != null) {
            dbHelper = TaskAdministrationDBHelper(context!!)
            db = dbHelper!!.writableDatabase
            parameters.put(ProjectEntry.COLUMN_NAME_PROJECT, name)
            idRow = db?.insert(ProjectEntry.TABLE_NAME, null, parameters)
            parameters.clear()
            for (tasksModel in tasksModelList!!) {
                parameters.put(TaskEntry.COLUMN_NAME_TASK, tasksModel?.task)
                parameters.put(TaskEntry.COLUMN_NAME_DATE, tasksModel?.date)
                parameters.put(TaskEntry.COLUMN_NAME_HOUR, tasksModel?.hour)
                parameters.put(TaskEntry.COLUMN_NAME_STATUS, TaskEntry.STATUS_IN_PROGRESS)
                parameters.put(TaskEntry.COLUMN_NAME_ID_PROJECT, idRow!!.toInt())
                db?.insert(TaskEntry.TABLE_NAME, null, parameters)
                parameters.clear()
            }
            db?.close()
            dbHelper!!.close()
            informationDelegate!!.createNewProjectReady(idRow != null)
        }
    }

}