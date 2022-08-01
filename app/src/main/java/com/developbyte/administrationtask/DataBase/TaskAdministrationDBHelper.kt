package com.developbyte.administrationtask.DataBase.Entrys

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class TaskAdministrationDBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(ProjectEntry.CREATE_TABLE)
        db?.execSQL(TaskEntry.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(ProjectEntry.DELETE_TABLE)
        db?.execSQL(TaskEntry.DELETE_TABLE)
        onCreate(db)
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "taskAdministration.db"
    }
}