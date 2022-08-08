package com.developbyte.administrationtask.Home

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developbyte.administrationtask.Abstract.AbstractViewController
import com.developbyte.administrationtask.Adapters.ListDaysMountAdapter
import com.developbyte.administrationtask.Adapters.ModalListMonthsAdapeter
import com.developbyte.administrationtask.Model.DaysMountModel
import com.developbyte.administrationtask.Model.MonthsModel
import com.developbyte.administrationtask.Model.TasksModel
import com.developbyte.administrationtask.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import java.text.DateFormatSymbols
import java.time.YearMonth
import java.util.*

class HomeViewController : AbstractViewController(), IHome.IHomeRepresentationHandler {

    var representationDelegate: IHome.IHomeRepresentationDelegate? = null

    private var txtDayToday: AppCompatTextView? = null
    private var btnCalendar: AppCompatButton? = null
    private var builderMonth: AlertDialog.Builder? = null
    private var alertDialogMonths: AlertDialog? = null
    private var viewDialogMonths: View? = null
    private var lstModalMonths: RecyclerView? = null
    private var listMonthsAdapeter: ModalListMonthsAdapeter? = null
    private var btnConfirmationModalMonths: AppCompatButton? = null

    private var lstDaysMount: RecyclerView? = null
    private var listDaysMountAdapter: ListDaysMountAdapter? = null

    private var txtTaskProgress: AppCompatTextView? = null
    private var txtTaskComplete: AppCompatTextView? = null

    private var tbTask: TabLayout? = null
    private var dateSelected:String = ""

    private var btnNewProject: FloatingActionButton? = null


    @RequiresApi(Build.VERSION_CODES.O)
    override fun init(inflater: LayoutInflater?, container: ViewGroup?): View? {
        viewc = inflater?.inflate(R.layout.content_home, container, false);

        dateSelected = (Calendar.getInstance()[Calendar.DAY_OF_MONTH].toString() + "/"
                + Calendar.getInstance()[Calendar.MONTH] + "/"
                + Calendar.getInstance()[Calendar.YEAR])

        txtDayToday = viewc!!.findViewById(R.id.txt_day_today)
        txtDayToday!!.text = (YearMonth.now().month.name + " " + Calendar.getInstance()[Calendar.DAY_OF_MONTH] + ", " + YearMonth.now().year)

        btnCalendar = viewc!!.findViewById(R.id.btn_select_month);
        btnCalendar!!.text = YearMonth.now().month.name
        btnCalendar!!.setOnClickListener { showListMonths() }

        lstDaysMount = viewc!!.findViewById(R.id.lst_days_mount)
        lstDaysMount!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        listDaysMountAdapter = ListDaysMountAdapter(requireContext(), representationDelegate!!)
        lstDaysMount!!.adapter = listDaysMountAdapter

        btnNewProject = viewc?.findViewById(R.id.btn_new_project)
        btnNewProject?.setOnClickListener { representationDelegate?.showNewProject() }

        representationDelegate!!.getDaysOfCurrentMount(YearMonth.now().monthValue)

        return viewc
    }

    override fun resume() {

    }

    override fun restoreData(savedInstanceState: Bundle?) {

    }

    override fun onBackPressed() {
        activity?.finish()
    }

    override fun showHome(): Boolean {
        return masterViewController!!.presetFragment2(tag)
    }

    private fun reloadData() {
        if (tbTask!!.selectedTabPosition == 0) {
            representationDelegate!!.getTaskComplete(dateSelected)
            representationDelegate!!.getTaskInProgress(dateSelected)
        } else {
            representationDelegate!!.getTaskInProgress(dateSelected)
            representationDelegate!!.getTaskComplete(dateSelected)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun setDaysOfCurrentMount(daysOfCurrentMount: MutableList<DaysMountModel>) {
        val currentMonth = if (listMonthsAdapeter == null) YearMonth.now().monthValue else listMonthsAdapeter!!.indexSelected
        listDaysMountAdapter!!.setDaysMountModel(daysOfCurrentMount, currentMonth)

        dateSelected = ((listDaysMountAdapter!!.indexToday + 1).toString() + "/"
                + currentMonth + "/"
                + Calendar.getInstance()[Calendar.YEAR])

        btnCalendar!!.text = DateFormatSymbols.getInstance().months[currentMonth - 1]
        lstDaysMount!!.scrollToPosition(listDaysMountAdapter!!.indexToday)
    }

    override fun setTaskInProgress(taskInProgress: MutableList<TasksModel>) {
    }

    override fun setTaskComplete(taskComplete: MutableList<TasksModel>) {
    }

    override fun setMonthList(monthList: MutableList<MonthsModel>) {
        lstModalMonths = viewDialogMonths!!.findViewById(R.id.lst_months_modal)
        lstModalMonths!!.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        listMonthsAdapeter = ModalListMonthsAdapeter(monthList, requireContext())
        lstModalMonths!!.adapter = listMonthsAdapeter
        lstModalMonths!!.scrollToPosition(listMonthsAdapeter!!.indexSelected - 2)
    }

    override fun updateStatusTaskResult(ready: Boolean) {
    }

    override fun deleteTaskResult(ready: Boolean) {
    }

    private fun showListMonths(){
        if(builderMonth == null){
            builderMonth = AlertDialog.Builder(requireContext())
            viewDialogMonths = activity?.layoutInflater!!.inflate(R.layout.widget_modal_list_month, null)
            representationDelegate!!.getMonthsList()
            btnConfirmationModalMonths = viewDialogMonths!!.findViewById(R.id.btn_confirmation_modal_months)
            btnConfirmationModalMonths!!.setOnClickListener {
                representationDelegate!!.getDaysOfCurrentMount(listMonthsAdapeter!!.indexSelected)
                alertDialogMonths!!.dismiss()
            }
            alertDialogMonths = builderMonth!!.setView(viewDialogMonths).create()
        }
        alertDialogMonths!!.show()
    }
}