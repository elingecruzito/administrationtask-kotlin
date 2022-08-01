package com.developbyte.administrationtask.Abstract

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import com.developbyte.administrationtask.DataBase.Entrys.ProjectEntry
import com.developbyte.administrationtask.DataBase.Entrys.TaskAdministrationDBHelper
import com.developbyte.administrationtask.DataBase.Entrys.TaskEntry
import com.developbyte.administrationtask.Model.TaskModel

abstract class AbstractService {

    val modelList: MutableList<TaskModel> = mutableListOf()
    var values: ContentValues? = null
    var countUpdate: Int? = 0
    var countDeletes: Int? = 0

    fun initListModel(){
        if(!modelList!!.isEmpty()){
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
    fun loadData(query:String, selectionArgs:Array<String>, context: Context){
        var dbHelper = TaskAdministrationDBHelper(context)
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery(query, selectionArgs)
        cursor.moveToFirst()
        if(cursor.columnCount > 0){
            initListModel()
            do {
                modelList?.add(TaskModel(
                        cursor.getInt(cursor.getColumnIndex(TaskEntry.COLUMN_NAME_ID)),
                        cursor.getString(cursor.getColumnIndex(TaskEntry.COLUMN_NAME_TASK)),
                        cursor.getInt(cursor.getColumnIndex(TaskEntry.COLUMN_NAME_ID_PROJECT)),
                        cursor.getString(cursor.getColumnIndex(ProjectEntry.COLUMN_NAME_PROJECT)),
                        cursor.getString(cursor.getColumnIndex(TaskEntry.COLUMN_NAME_HOUR)),
                        cursor.getString(cursor.getColumnIndex(TaskEntry.COLUMN_NAME_DATE)),
                        cursor.getInt(cursor.getColumnIndex(TaskEntry.COLUMN_NAME_STATUS))
                ))
            }while (cursor.moveToNext())
            cursor.close()
            db.close()
            dbHelper.close()
        }
    }

    fun createTask(taskModel: TaskModel, context: Context): TaskModel {
        var dbHelper = TaskAdministrationDBHelper(context)
        val db = dbHelper.writableDatabase
        initValues()
        values?.put(TaskEntry.COLUMN_NAME_TASK, taskModel.task);
        values?.put(TaskEntry.COLUMN_NAME_DATE, taskModel.date);
        values?.put(TaskEntry.COLUMN_NAME_HOUR, taskModel.hour);
        values?.put(TaskEntry.COLUMN_NAME_STATUS, taskModel.STATUS_IN_PROGRESS);
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
        values?.put(TaskEntry.COLUMN_NAME_STATUS, TaskModel().STATUS_COMPLETE)
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

    abstract fun mutableListOf(): MutableList<TaskModel>

}