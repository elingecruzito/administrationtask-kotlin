package com.developbyte.administrationtask.DataBase.Entrys

import android.provider.BaseColumns

object TaskEntry: BaseColumns {

    const val TABLE_NAME = "task"
    const val COLUMN_NAME_ID = "id_task"
    const val COLUMN_NAME_TASK = "task_name"
    const val COLUMN_NAME_DATE = "task_date"
    const val COLUMN_NAME_HOUR = "task_hour"
    const val COLUMN_NAME_STATUS = "task_status"
    const val COLUMN_NAME_ID_PROJECT = "id_project"

    const val STATUS_IN_PROGRESS:Int = 0
    const val STATUS_COMPLETE:Int = 1

    const val CREATE_TABLE = ("CREATE TABLE " + TABLE_NAME + " ( "
            + COLUMN_NAME_ID + " INTEGER PRIMARY KEY,"
            + COLUMN_NAME_TASK + " TEXT, "
            + COLUMN_NAME_DATE + " TEXT, "
            + COLUMN_NAME_HOUR + " TEXT, "
            + COLUMN_NAME_STATUS + " INTEGER, "
            + COLUMN_NAME_ID_PROJECT + " INTEGER"
            + " );")

    const val DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME;"

    val projection = arrayOf(
            COLUMN_NAME_ID,
            COLUMN_NAME_TASK,
            COLUMN_NAME_DATE,
            COLUMN_NAME_HOUR,
            COLUMN_NAME_STATUS,
            COLUMN_NAME_ID_PROJECT
    )

    const val sortOrder = COLUMN_NAME_DATE + " DESC"

}