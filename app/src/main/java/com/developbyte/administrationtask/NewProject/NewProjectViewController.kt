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
import com.developbyte.administrationtask.R


class NewProjectViewController : AbstractViewController(), INewProject.INewProjectRepresentationHandler {

    var representationDelegate: INewProject.INewProjectRepresentationDelegate? = null
    private var txtNewNameProject: AppCompatEditText? = null
    private var btnAddNewTask: AppCompatButton? = null
    private var lstNewProjectTask: RecyclerView? = null
    private var btnCreateProject: AppCompatButton? = null
    private var btnCancelProject: AppCompatButton? = null


    override fun init(inflater: LayoutInflater?, container: ViewGroup?): View? {
        viewc = inflater?.inflate(R.layout.content_newproject, container, false);
        txtNewNameProject = viewc?.findViewById(R.id.txt_new_name_project)


        return viewc
    }

    override fun resume() {

    }

    override fun restoreData(savedInstanceState: Bundle?) {

    }

    override fun onBackPressed() {
        activity?.supportFragmentManager?.popBackStack()
    }

    override fun showNewProject() {
        masterViewController?.presetFragment(tag)
    }
}