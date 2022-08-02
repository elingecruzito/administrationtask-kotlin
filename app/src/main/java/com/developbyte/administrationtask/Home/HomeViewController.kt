package com.developbyte.administrationtask.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import com.developbyte.administrationtask.Abstract.AbstractViewController
import com.developbyte.administrationtask.Model.DaysMountModel
import com.developbyte.administrationtask.Model.MonthsModel
import com.developbyte.administrationtask.Model.TasksModel
import com.developbyte.administrationtask.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeViewController : AbstractViewController(), IHome.IHomeRepresentationHandler {

    var representationDelegate: IHome.IHomeRepresentationDelegate? = null
    var btnNewProject: FloatingActionButton? = null


    override fun init(inflater: LayoutInflater?, container: ViewGroup?): View? {
        viewc = inflater?.inflate(R.layout.content_home,container,false);

        btnNewProject = viewc?.findViewById(R.id.btn_new_project)
        btnNewProject?.setOnClickListener { representationDelegate?.showNewProject() }

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

    override fun setDaysOfCurrentMount(daysOfCurrentMount: List<DaysMountModel?>?) {
    }

    override fun setTaskInProgress(taskInProgress: List<TasksModel?>?) {
    }

    override fun setTaskComplete(taskComplete: List<TasksModel?>?) {
    }

    override fun setMonthList(monthList: List<MonthsModel?>?) {
    }

    override fun updateStatusTaskResult(ready: Boolean) {
    }

    override fun deleteTaskResult(ready: Boolean) {
    }
}