package com.developbyte.administrationtask.InfoProject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import com.developbyte.administrationtask.Abstract.AbstractViewController
import com.developbyte.administrationtask.R

class InfoProjectViewController : AbstractViewController(), IInfoProject.IInfoProjectRepresentationHandler {

    var representationDelegate: IInfoProject.IInfoProjectRepresentationDelegate? = null


    override fun init(inflater: LayoutInflater?, container: ViewGroup?): View? {
        viewc = inflater?.inflate(R.layout.content_infoproject,container,false);

        return viewc
    }

    override fun resume() {

    }

    override fun restoreData(savedInstanceState: Bundle?) {

    }

    override fun onBackPressed() {
        activity?.supportFragmentManager?.popBackStack()
    }

    override fun showInfoProject() {
        masterViewController?.presetFragment(tag)
    }
}