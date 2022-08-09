package com.developbyte.administrationtask.InfoProject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.developbyte.administrationtask.Abstract.AbstractViewController
import com.developbyte.administrationtask.InfoProject.Fragments.CompleteInfoFragment
import com.developbyte.administrationtask.InfoProject.Fragments.ProgressInfoFragment
import com.developbyte.administrationtask.Model.ProjectModel
import com.developbyte.administrationtask.Model.TasksModel
import com.developbyte.administrationtask.R
import com.developbyte.administrationtask.Widgets.RunnableWidgetAddItem
import com.developbyte.administrationtask.Widgets.Utilerias
import com.developbyte.administrationtask.Widgets.WidgetCreateNewTask
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout


class InfoProjectViewController : AbstractViewController(), IInfoProject.IInfoProjectRepresentationHandler {

    var idTask:Int? = 0;
    var representationDelegate: IInfoProject.IInfoProjectRepresentationDelegate? = null
    var utilerias: Utilerias? = null

    private var txtNameProjectInfo: AppCompatTextView? = null
    private var tbTaskInfo: TabLayout? = null
    private var btnAddTaskInfo: FloatingActionButton? = null
    private var newTask: WidgetCreateNewTask? = null

    var sizeInfoProgressTask:Int = 0
    var sizeInfoCompleteTask:Int = 0

    override fun init(inflater: LayoutInflater?, container: ViewGroup?): View? {
        viewc = inflater?.inflate(R.layout.content_infoproject, container, false);

        txtNameProjectInfo = viewc!!.findViewById(R.id.txt_name_project_info);

        representationDelegate!!.getDataProject(idTask!!);

        tbTaskInfo = viewc!!.findViewById(R.id.tb_task_info);
        tbTaskInfo!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> representationDelegate!!.getAllProgressTask(idTask!!)
                    1 -> representationDelegate!!.getAllCompleteTask(idTask!!)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
        refreshFragment()

        btnAddTaskInfo = viewc!!.findViewById(R.id.btn_add_task_info)
        newTask = WidgetCreateNewTask(requireActivity(), utilerias!!)
        btnAddTaskInfo!!.setOnClickListener { newTask!!.showCreateNewTask(idTask!!, RunnableWidgetAddItem(), representationDelegate) }

        return viewc
    }

    override fun resume() {

    }

    override fun restoreData(savedInstanceState: Bundle?) {

    }

    override fun onBackPressed() {
        activity?.supportFragmentManager?.popBackStack()
    }

    override fun showInfoProject(idTask: Int?) {
        this.idTask = idTask
        masterViewController?.presetFragment(tag)
    }

    override fun setDataProject(project: ProjectModel?) {
        txtNameProjectInfo!!.text = project!!.project_name
    }

    override fun setAllProgressTask(progressTask: MutableList<TasksModel>) {
        sizeInfoProgressTask = progressTask.size
        setFragmentTabTask(ProgressInfoFragment(progressTask, representationDelegate, utilerias))
    }

    override fun setAllCompleteTask(completeTask: MutableList<TasksModel>) {
        sizeInfoCompleteTask = completeTask.size
        setFragmentTabTask(CompleteInfoFragment(completeTask, representationDelegate, utilerias))
    }

    override fun setInsertTask(task: TasksModel?) {
        refreshFragment()
    }

    override fun updateStatusTaskResult(ready: Boolean) {
        if(ready){
            refreshFragment()
        }
    }

    override fun deleteTask(ready: Boolean) {
        if(ready){
            refreshFragment()
        }
    }

    private fun refreshFragment() {
        if (tbTaskInfo!!.selectedTabPosition == 0) {
            representationDelegate!!.getAllCompleteTask(idTask!!)
            representationDelegate!!.getAllProgressTask(idTask!!)
        } else {
            representationDelegate!!.getAllProgressTask(idTask!!)
            representationDelegate!!.getAllCompleteTask(idTask!!)
        }
    }

    private fun setFragmentTabTask(fragmentTask: Fragment) {
        activity?.supportFragmentManager!!
                .beginTransaction()
                .replace(R.id.fm_tab_task_info, fragmentTask)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
    }
}