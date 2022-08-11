package com.developbyte.administrationtask.InfoProject

import android.graphics.Color
import android.graphics.Typeface
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
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.MPPointF
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import java.util.*


class InfoProjectViewController : AbstractViewController(), IInfoProject.IInfoProjectRepresentationHandler {

    var idTask:Int? = 0;
    var representationDelegate: IInfoProject.IInfoProjectRepresentationDelegate? = null
    var utilerias: Utilerias? = null

    private var txtNameProjectInfo: AppCompatTextView? = null
    private var tbTaskInfo: TabLayout? = null
    private var btnAddTaskInfo: FloatingActionButton? = null
    private var newTask: WidgetCreateNewTask? = null

    var pieChart: PieChart? = null
    var sizeInfoProgressTask:Int = 0
    var sizeInfoCompleteTask:Int = 0

    override fun init(inflater: LayoutInflater?, container: ViewGroup?): View? {
        viewc = inflater?.inflate(R.layout.content_infoproject, container, false);

        txtNameProjectInfo = viewc!!.findViewById(R.id.txt_name_project_info);

        pieChart = viewc!!.findViewById(R.id.chart_project)

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
        setChartSettings()
    }

    override fun setAllCompleteTask(completeTask: MutableList<TasksModel>) {
        sizeInfoCompleteTask = completeTask.size
        setFragmentTabTask(CompleteInfoFragment(completeTask, representationDelegate, utilerias))
        setChartSettings()
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

    private fun setChartSettings(){
        // on below line we are setting user percent value,
        // setting description as enabled and offset for pie chart
        pieChart!!.setUsePercentValues(true)
        pieChart!!.description.isEnabled = false
        pieChart!!.setExtraOffsets(5f, 10f, 5f, 5f)
        // on below line we are setting drag for our pie chart
        pieChart!!.dragDecelerationFrictionCoef = 0.95f
        // on below line we are setting hole
        // and hole color for pie chart
        pieChart!!.isDrawHoleEnabled = true
        pieChart!!.setHoleColor(Color.WHITE)
        // on below line we are setting circle color and alpha
        pieChart!!.setTransparentCircleColor(Color.WHITE)
        pieChart!!.setTransparentCircleAlpha(110)
        // on  below line we are setting hole radius
        pieChart!!.holeRadius = 58f
        pieChart!!.transparentCircleRadius = 61f
        // on below line we are setting center text
        pieChart!!.setDrawCenterText(true)
        // on below line we are setting
        // rotation for our pie chart
        pieChart!!.rotationAngle = 0f
        // enable rotation of the pieChart!! by touch
        pieChart!!.isRotationEnabled = true
        pieChart!!.isHighlightPerTapEnabled = true
        // on below line we are setting animation for our pie chart
        pieChart!!.animateY(1400, Easing.EaseInOutQuad)
        // on below line we are disabling our legend for pie chart
//        pieChart!!.legend.isEnabled = false
        pieChart!!.setEntryLabelColor(Color.WHITE)
        pieChart!!.setEntryLabelTextSize(10f)

        // on below line we are creating array list and
        // adding data to it to display in pie chart
        val entries: ArrayList<PieEntry> = ArrayList()
        entries.add(PieEntry(sizeInfoCompleteTask.toFloat(), resources.getString(R.string.lbl_chart_complete)))
        entries.add(PieEntry(sizeInfoProgressTask.toFloat(), resources.getString(R.string.lbl_chart_progress)))

        // on below line we are setting pie data set
        val dataSet = PieDataSet(entries, resources.getString(R.string.lbl_title_chart_task))
        // on below line we are setting icons.
        dataSet.setDrawIcons(false)
        // on below line we are setting slice for pie
        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f

        // add a lot of colors to list
        val colors: ArrayList<Int> = ArrayList()
        colors.add(resources.getColor(R.color.color_text))
        colors.add(resources.getColor(R.color.blue_background))
        // on below line we are setting colors.
        dataSet.colors = colors
        // on below line we are setting pie data set
        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(15f)
        data.setValueTypeface(Typeface.DEFAULT_BOLD)
        data.setValueTextColor(Color.WHITE)
        pieChart!!.data = data
        // undo all highlights
        pieChart!!.highlightValues(null)
        // loading chart
        pieChart!!.invalidate()
    }
}