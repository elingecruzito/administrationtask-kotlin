package com.developbyte.administrationtask.Home

import android.content.ContentValues
import android.content.Context
import com.developbyte.administrationtask.Abstract.AbstractService
import com.developbyte.administrationtask.DataBase.Entrys.TaskEntry
import com.developbyte.administrationtask.DataBase.RelationProjectTask
import com.developbyte.administrationtask.Model.TasksModel


class HomeService : AbstractService(), IHome.IHomeInformationHandler {

    var informationDelegate : IHome.IHomeInformationDelegate? = null
    private val parameters = ContentValues()
    var context: Context? = null

    override fun getTaskInProgress(date: String?) {

        loadData(
                RelationProjectTask.QUERY_RELATION + " AND " + TaskEntry.COLUMN_NAME_STATUS + " = ?" + " AND " + TaskEntry.COLUMN_NAME_DATE + " = ?",
                arrayOf(TasksModel.STATUS_IN_PROGRESS.toString(), date),
                context!!
        )

        informationDelegate?.setTaskInProgress(modelList)
    }

    override fun getTaskComplete(date: String?) {
        loadData(
                RelationProjectTask.QUERY_RELATION + " AND " + TaskEntry.COLUMN_NAME_STATUS + " = ?" + " AND " + TaskEntry.COLUMN_NAME_DATE + " = ?",
                arrayOf(TasksModel.STATUS_COMPLETE.toString(), date),
                context!!
        )


        informationDelegate?.setTaskComplete(modelList)
    }

    override fun updateStatusTask(idTask: Int) {
        updateStatusTask(idTask, context!!)
        informationDelegate?.updateStatusTaskResult(countUpdate!! > 0)
    }

    override fun deleteTask(idTask: Int) {
        deleteTask(idTask, context!!)
        informationDelegate?.deleteTaskResult(countDeletes!! > 0);
    }

}