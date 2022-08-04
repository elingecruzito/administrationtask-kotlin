package com.developbyte.administrationtask.Widgets

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.developbyte.administrationtask.Adapters.ListNewTaskAdapter
import com.developbyte.administrationtask.InfoProject.IInfoProject.IInfoProjectRepresentationDelegate
import com.developbyte.administrationtask.Model.TasksModel
import com.developbyte.administrationtask.R
import com.google.android.material.bottomsheet.BottomSheetDialog


class WidgetCreateNewTask {

    private var activity: Activity? = null
    private var utilerias: Utilerias? = null

    private var alertDialog: BottomSheetDialog? = null
    private var viewAddNewTask: View? = null
    private var txtNameNewTask: AppCompatEditText? = null
    private var txtDateNewTask: AppCompatEditText? = null
    private var btnModalCalendar: AppCompatButton? = null
    private var txtDateNewHour: AppCompatEditText? = null
    private var btnModalClock: AppCompatButton? = null
    private var btnCancelNewTask: AppCompatButton? = null
    private var btnCreateNewTask: AppCompatButton? = null

    private var datePickerDialog: DatePickerDialog? = null
    private var timePickerDialog: TimePickerDialog? = null

    constructor(activity: Activity, utilerias: Utilerias){
        this.activity = activity
        this.utilerias = utilerias
        init()
    }

    fun init(){
        alertDialog = BottomSheetDialog(activity!!)
        viewAddNewTask = activity!!.layoutInflater.inflate(R.layout.widget_modal_new_task, null)
        txtNameNewTask = viewAddNewTask!!.findViewById(R.id.txt_name_new_task)

        txtDateNewTask = viewAddNewTask!!.findViewById(R.id.txt_date_new_task)
        datePickerDialog = utilerias!!.getDatePickerDialog(activity, txtDateNewTask!!)
        btnModalCalendar = viewAddNewTask!!.findViewById(R.id.btn_modal_calendar)
        btnModalCalendar!!.setOnClickListener { datePickerDialog!!.show() }

        txtDateNewHour = viewAddNewTask!!.findViewById(R.id.txt_date_new_hour)
        timePickerDialog = utilerias!!.getTimePickerDialog(activity, txtDateNewHour!!)
        btnModalClock = viewAddNewTask!!.findViewById(R.id.btn_modal_clock)
        btnModalClock!!.setOnClickListener{ timePickerDialog!!.show() }

        btnCancelNewTask = viewAddNewTask!!.findViewById(R.id.btn_cancel_new_task)
        btnCancelNewTask!!.setOnClickListener{
            clearModalNewTask()
            alertDialog!!.dismiss()
        }

        btnCreateNewTask = viewAddNewTask!!.findViewById(R.id.btn_create_new_task)
        //alertDialog = alertDialogAddNewTask.setView(viewAddNewTask).create();
        alertDialog!!.setContentView(viewAddNewTask!!)
    }

    private fun clearModalNewTask() {
        txtNameNewTask!!.setText("")
        txtDateNewTask!!.setText("")
        txtDateNewHour!!.setText("")
    }

    fun showCreateNewTask(
            listNewTaskAdapter: ListNewTaskAdapter?,
            runnable: RunnableWidgetAddItemList
    ) {
        btnCreateNewTask!!.setOnClickListener {
            runnable.listNewTaskAdapter = listNewTaskAdapter
            runnable.name = txtNameNewTask!!.text.toString()
            runnable.date = txtDateNewTask!!.text.toString()
            runnable.hour = txtDateNewHour!!.text.toString()
            runnable.run()
            clearModalNewTask()
            alertDialog!!.dismiss()
        }
        alertDialog!!.show()
    }

    fun showCreateNewTask(
        id_project: Int,
        addItem: RunnableWidgetAddItem,
        representationDelegate: IInfoProjectRepresentationDelegate?
    ) {
        btnCreateNewTask!!.setOnClickListener {
            addItem.representationDelegate = representationDelegate
            var tasksModel = TasksModel(
                txtNameNewTask!!.text.toString(),
                txtDateNewHour!!.text.toString(),
                txtDateNewTask!!.text.toString()
            )
            tasksModel?.id_project = id_project
            addItem.tasksModel = tasksModel
            addItem.run()
            clearModalNewTask()
            alertDialog!!.dismiss()
        }
        alertDialog!!.show()
    }
}