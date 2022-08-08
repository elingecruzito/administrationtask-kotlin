package com.developbyte.administrationtask.Home

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.developbyte.administrationtask.Abstract.AbstractBusinessController
import com.developbyte.administrationtask.Model.DaysMountModel
import com.developbyte.administrationtask.Model.MonthsModel
import com.developbyte.administrationtask.Model.TasksModel
import java.text.DateFormat
import java.text.DateFormatSymbols
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.YearMonth
import java.util.*


class HomeBusinessController: AbstractBusinessController(), IHome.IHomeRepresentationDelegate,
    IHome.IHomeTransactionHandler,
    IHome.IHomeInformationDelegate {

    var representationHandler: IHome.IHomeRepresentationHandler? = null
    var transactionDelegate: IHome.IHomeTransactionDelegate? = null
    var informationHandler: IHome.IHomeInformationHandler? = null

    private var daysMountModels: MutableList<DaysMountModel>? = ArrayList()
    private var dateFormat: DateFormat? = null
    private var format: SimpleDateFormat? = null
    private var date: Date? = null
    private var day_text: String = ""

    private var monthsModelList: MutableList<MonthsModel>? = ArrayList()

    override fun startHome() {
        representationHandler?.showHome()
        format = SimpleDateFormat("dd/MM/yyyy")
        dateFormat = SimpleDateFormat("EEEE")
    }

    override fun showInfoProject() {
        transactionDelegate?.showInfoProject()
    }

    override fun showNewProject() {
        transactionDelegate?.showNewProject()
    }

    @SuppressLint("LongLogTag")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun getDaysOfCurrentMount(mount: Int) {

        val dayOfMonth:Int = Calendar.getInstance()[Calendar.DAY_OF_MONTH]
        val actualMonth:Int = Calendar.getInstance()[Calendar.MONTH] + 1
        val actualYear:Int = Calendar.getInstance()[Calendar.YEAR]
        val longDays = YearMonth.of(Calendar.getInstance()[Calendar.YEAR], mount).lengthOfMonth()
        daysMountModels?.clear()

        for (i in 1..longDays) {
            try {
                date = format?.parse("$i/$mount/$actualYear")
                day_text = dateFormat?.format(date)?.subSequence(0, 3).toString()
            } catch (e: ParseException) {
                day_text = ""
                Log.e("HomeBusinessController -> ", e.message.toString())
            }
            daysMountModels?.add(DaysMountModel(
                    i,
                    day_text,
                    dayOfMonth === i && actualMonth === mount
            ))
        }
        if (actualMonth !== mount) {
            daysMountModels?.get(0)?.selected = true
        } else {
            daysMountModels?.get(dayOfMonth - 1)?.selected = true
        }
        representationHandler!!.setDaysOfCurrentMount(daysMountModels!!)
    }

    override fun getTaskInProgress(date: String?) {
        informationHandler!!.getTaskInProgress(date)
    }

    override fun getTaskComplete(date: String?) {
        informationHandler!!.getTaskComplete(date)
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    override fun getMonthsList() {
        monthsModelList?.clear()
        for (i in 0..DateFormatSymbols.getInstance().getMonths().size) {
            var monthsModel: MonthsModel? = null
            monthsModel?.month = DateFormatSymbols.getInstance().months[i]
            monthsModel?.isActive = YearMonth.now().monthValue - 1 === i
        }
        representationHandler!!.setMonthList(monthsModelList!!)
    }

    override fun updateStatusTask(idTask: Int) {
        informationHandler!!.updateStatusTask(idTask)
    }

    override fun deleteTask(idTask: Int) {
        informationHandler!!.deleteTask(idTask)
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    override fun setTaskInProgress(taskInProgress: MutableList<TasksModel>) {
        representationHandler!!.setTaskInProgress(taskInProgress!!)
    }

    override fun setTaskComplete(taskComplete: MutableList<TasksModel>) {
        representationHandler!!.setTaskComplete(taskComplete!!)
    }

    override fun updateStatusTaskResult(ready: Boolean) {
        representationHandler!!.updateStatusTaskResult(ready)
    }

    override fun deleteTaskResult(ready: Boolean) {
        representationHandler!!.deleteTaskResult(ready)
    }


}