package com.developbyte.administrationtask.InfoProject

import android.annotation.SuppressLint
import android.content.Context
import com.developbyte.administrationtask.Abstract.AbstractService
import com.developbyte.administrationtask.DataBase.Entrys.ProjectEntry
import com.developbyte.administrationtask.DataBase.Entrys.TaskAdministrationDBHelper
import com.developbyte.administrationtask.DataBase.Entrys.TaskEntry
import com.developbyte.administrationtask.DataBase.RelationProjectTask
import com.developbyte.administrationtask.Model.ProjectModel
import com.developbyte.administrationtask.Model.TasksModel
import java.lang.String


class InfoProjectService : AbstractService(), IInfoProject.IInfoProjectInformationHandler {

    var informationDelegate : IInfoProject.IInfoProjectInformationDelegate? = null
    var context: Context? = null
    var projectModel: ProjectModel? = null

    @SuppressLint("Range")
    override fun getDataProject(id: Int) {
        val dbHelper = TaskAdministrationDBHelper(context!!)
        val db = dbHelper.getReadableDatabase()

        val selection = ProjectEntry.COLUMN_NAME_ID + " = ?"
        val selectionArgs = arrayOf(String.valueOf(id))

        val cursor = db.query(
                ProjectEntry.TABLE_NAME,  // The table to query
                null,  // The array of columns to return (pass null to get all)
                selection,  // The columns for the WHERE clause
                selectionArgs,  // The values for the WHERE clause
                null,  // don't group the rows
                null,  // don't filter by row groups
                null // The sort order
        )

        cursor.moveToFirst()
        if (cursor.getCount() > 0) {
            do {
                projectModel!!.id_project = cursor.getString(cursor.getColumnIndex(ProjectEntry.COLUMN_NAME_ID))
                projectModel!!.project_name = cursor.getString(cursor.getColumnIndex(ProjectEntry.COLUMN_NAME_PROJECT))
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        dbHelper.close()
        informationDelegate?.setDataProject(projectModel)
    }

    override fun getAllProgressTask(id: Int) {
        loadData(
                RelationProjectTask.QUERY_RELATION
                        + " AND " + TaskEntry.COLUMN_NAME_STATUS + " = ?"
                        + " AND " + TaskEntry.TABLE_NAME + "." + TaskEntry.COLUMN_NAME_ID_PROJECT + " = ? ORDER BY " + TaskEntry.COLUMN_NAME_DATE + " DESC", arrayOf(String.valueOf(TasksModel.STATUS_IN_PROGRESS), String.valueOf(id)),
                (context)!!
        )
        informationDelegate?.setAllProgressTask(modelList)
    }

    override fun getAllCompleteTask(id: Int) {
        loadData(
                RelationProjectTask.QUERY_RELATION
                        + " AND " + TaskEntry.COLUMN_NAME_STATUS + " = ?"
                        + " AND " + TaskEntry.TABLE_NAME + "." + TaskEntry.COLUMN_NAME_ID_PROJECT + " = ? ORDER BY " + TaskEntry.COLUMN_NAME_DATE + " DESC", arrayOf(String.valueOf(TasksModel.STATUS_COMPLETE), String.valueOf(id)),
                (context)!!
        )
        informationDelegate?.setAllCompleteTask(modelList)
    }

    override fun createNewTask(tasksModel: TasksModel?) {
        informationDelegate?.setInsertTask(createTask(tasksModel!!, context!!));
    }

    override fun updateStatusTask(idtask: Int) {
        updateStatusTask(idtask, context!!)
        informationDelegate?.updateStatusTaskResult(countUpdate!! > 0)
    }

    override fun deleteTask(idtask: Int) {
        deleteTask(idtask, context!!)
        informationDelegate?.deleteTask(countDeletes!! > 0)
    }

}