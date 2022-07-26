package com.developbyte.administrationtask.NewProject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import com.developbyte.administrationtask.Abstract.AbstractViewController
import com.developbyte.administrationtask.R

class NewProjectViewController : AbstractViewController(), INewProject.INewProjectRepresentationHandler {

    var representationDelegate: INewProject.INewProjectRepresentationDelegate? = null


    override fun init(inflater: LayoutInflater?, container: ViewGroup?): View? {
        viewc = inflater?.inflate(R.layout.content_newproject,container,false);

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