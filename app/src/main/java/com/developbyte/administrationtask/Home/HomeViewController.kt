package com.developbyte.administrationtask.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import com.developbyte.administrationtask.Abstract.AbstractViewController
import com.developbyte.administrationtask.R

class HomeViewController : AbstractViewController(), IHome.IHomeRepresentationHandler {

    var representationDelegate: IHome.IHomeRepresentationDelegate? = null


    override fun init(inflater: LayoutInflater?, container: ViewGroup?): View? {
        viewc = inflater?.inflate(R.layout.content_home,container,false);

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
}