package com.developbyte.administrationtask.Abstract

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.provider.Telephony.TextBasedSmsColumns.STATUS_COMPLETE
import com.developbyte.administrationtask.DataBase.Entrys.ProjectEntry
import com.developbyte.administrationtask.DataBase.Entrys.TaskAdministrationDBHelper
import com.developbyte.administrationtask.DataBase.Entrys.TaskEntry
import com.developbyte.administrationtask.Model.TasksModel
import java.util.*

abstract class AbstractService {

    val modelList: MutableList<TasksModel> = ArrayList()
    var values: ContentValues? = null
    var countUpdate: Int? = 0
    var countDeletes: Int? = 0

    fun initListModel(){
        if(modelList!!.size > 0){
            modelList.clear()
        }
    }

    fun initValues(){
        if(values == null){
            values = ContentValues()
        }else{
            values?.clear()
        }
    }

    @SuppressLint("Range")
    fun loadData(query:String, selectionArgs: Array<String?>, context: Context){
        var dbHelper = TaskAdministrationDBHelper(context)
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery(query, selectionArgs)
        cursor.moveToFirst()
        if(cursor.count > 0){
            initListModel()
            do {
                var tasksModel =  TasksModel(
                        cursor.getString(cursor.getColumnIndex(TaskEntry.COLUMN_NAME_TASK)),
                        cursor.getString(cursor.getColumnIndex(TaskEntry.COLUMN_NAME_HOUR)),
                        cursor.getString(cursor.getColumnIndex(TaskEntry.COLUMN_NAME_DATE))
                )
                tasksModel?.id_task = cursor.getInt(cursor.getColumnIndex(TaskEntry.COLUMN_NAME_ID))
                tasksModel?.id_project = cursor.getInt(cursor.getColumnIndex(TaskEntry.COLUMN_NAME_ID_PROJECT))
                tasksModel?.project = cursor.getString(cursor.getColumnIndex(ProjectEntry.COLUMN_NAME_PROJECT))
                tasksModel?.status = cursor.getInt(cursor.getColumnIndex(TaskEntry.COLUMN_NAME_STATUS))
                modelList?.add(tasksModel!!)
            }while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        dbHelper.close()
    }

    fun createTask(taskModel: TasksModel, context: Context): TasksModel {
        var dbHelper = TaskAdministrationDBHelper(context)
        val db = dbHelper.writableDatabase
        initValues()
        values?.put(TaskEntry.COLUMN_NAME_TASK, taskModel.task);
        values?.put(TaskEntry.COLUMN_NAME_DATE, taskModel.date);
        values?.put(TaskEntry.COLUMN_NAME_HOUR, taskModel.hour);
        values?.put(TaskEntry.COLUMN_NAME_STATUS, TaskEntry.STATUS_IN_PROGRESS);
        values?.put(TaskEntry.COLUMN_NAME_ID_PROJECT, taskModel.id_project);
        var idRow = db.insert(
                TaskEntry.TABLE_NAME,
                null,
                values
        )

        taskModel.id_task = idRow.toInt()
        db.close()
        dbHelper.close()

        return taskModel
    }

    fun updateStatusTask(idTask: Int, context: Context){
        var dbHelper = TaskAdministrationDBHelper(context)
        val db = dbHelper.writableDatabase
        initValues()
        values?.put(TaskEntry.COLUMN_NAME_STATUS, TaskEntry.STATUS_COMPLETE)
        countUpdate = db.update(
                TaskEntry.TABLE_NAME,
                values,
                TaskEntry.COLUMN_NAME_ID + " = ?",
                arrayOf( idTask.toString() )
        )
        db.close()
        dbHelper.close()
    }

    fun deleteTask(idTask: Int, context: Context){
        var dbHelper = TaskAdministrationDBHelper(context)
        val db = dbHelper.writableDatabase
        countDeletes = db.delete(
                TaskEntry.TABLE_NAME,
                TaskEntry.COLUMN_NAME_ID + " = ?",
                arrayOf( idTask.toString() )
        )
        db.close()
        dbHelper.close()

    }

}