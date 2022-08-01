package com.developbyte.administrationtask.DataBase.Entrys

import android.provider.BaseColumns

object ProjectEntry: BaseColumns {

    const val TABLE_NAME = "projects"
    const val COLUMN_NAME_ID = "id_project"
    const val COLUMN_NAME_PROJECT = "project_name"

    const val CREATE_TABLE = ("CREATE TABLE " + TABLE_NAME + " ( "
            + COLUMN_NAME_ID + " INTEGER PRIMARY KEY,"
            + COLUMN_NAME_PROJECT + " TEXT"
            + " );")
    const val DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME;"

    val projection = arrayOf(
            COLUMN_NAME_ID,
            COLUMN_NAME_PROJECT
    )

    const val sortOrder = COLUMN_NAME_PROJECT + " DESC"
}