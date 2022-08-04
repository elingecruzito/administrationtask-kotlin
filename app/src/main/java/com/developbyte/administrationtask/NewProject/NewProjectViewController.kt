package com.developbyte.administrationtask.NewProject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developbyte.administrationtask.Abstract.AbstractViewController
import com.developbyte.administrationtask.Adapters.ListNewTaskAdapter
import com.developbyte.administrationtask.R
import com.developbyte.administrationtask.Widgets.RunnableWidgetAddItemList
import com.developbyte.administrationtask.Widgets.Utilerias
import com.developbyte.administrationtask.Widgets.WidgetCreateNewTask


class NewProjectViewController : AbstractViewController(), INewProject.INewProjectRepresentationHandler {

    var representationDelegate: INewProject.INewProjectRepresentationDelegate? = null
    var utilerias: Utilerias? = null
    private var txtNewNameProject: AppCompatEditText? = null
    private var btnAddNewTask: AppCompatButton? = null
    private var lstNewProjectTask: RecyclerView? = null
    private var btnCreateProject: AppCompatButton? = null
    private var btnCancelProject: AppCompatButton? = null

    private var newTask:WidgetCreateNewTask? = null
    private val listNewTaskAdapter = ListNewTaskAdapter()


    override fun init(inflater: LayoutInflater?, container: ViewGroup?): View? {
        viewc = inflater?.inflate(R.layout.content_newproject, container, false);

        newTask = WidgetCreateNewTask(requireActivity(), utilerias!!)

        txtNewNameProject = viewc!!.findViewById(R.id.txt_new_name_project)
        lstNewProjectTask = viewc!!.findViewById(R.id.lst_new_project_task)

        lstNewProjectTask!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        lstNewProjectTask!!.adapter = listNewTaskAdapter

        btnAddNewTask = viewc!!.findViewById(R.id.btn_add_new_task)
        btnAddNewTask!!.setOnClickListener { newTask!!.showCreateNewTask(listNewTaskAdapter, RunnableWidgetAddItemList()) }

        btnCreateProject = viewc!!.findViewById(R.id.btn_create_new_project)
        btnCreateProject!!.setOnClickListener{
            if (txtNewNameProject!!.text.toString() == null || txtNewNameProject!!.text.toString() == "") {
                utilerias!!.showMessageError(R.string.msg_error_create_project)
            } else {
                utilerias!!.showMessageConfirmation(R.string.msg_confirmation_create_project) {
                    representationDelegate!!.createNewProject(
                            txtNewNameProject!!.text.toString(),
                            listNewTaskAdapter.getListTasksModels()
                    )
                }
            }
        }
        btnCancelProject = viewc!!.findViewById(R.id.btn_cancel_new_project)
        btnCancelProject!!.setOnClickListener { onBackPressed() }

        return viewc
    }

    override fun resume() {

    }

    override fun restoreData(savedInstanceState: Bundle?) {

    }

    override fun onBackPressed() {
        txtNewNameProject!!.setText("")
        listNewTaskAdapter!!.clearListTaskModels()
        activity?.supportFragmentManager?.popBackStack()
    }

    override fun showNewProject() {
        masterViewController?.presetFragment(tag)
    }

    override fun createNewProject(ready: Boolean) {
        if(ready){
            utilerias!!.showMessageConfirmation(R.string.msg_confirmation_ready_create_project)
            onBackPressed()
        }
    }
}