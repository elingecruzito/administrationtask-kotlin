package com.developbyte.administrationtask.Abstract

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.developbyte.administrationtask.Interface.IBackPressed
import com.developbyte.administrationtask.Interface.IMasterViewController

abstract class AbstractViewController : Fragment(), IBackPressed{

    var masterViewController: IMasterViewController? = null
    var tag = 0
    protected var viewc: View? = null
    var savedInstanceState: Bundle? = null

    abstract fun init(inflater: LayoutInflater?, container: ViewGroup?): View?
    abstract fun resume()
    abstract fun restoreData(savedInstanceState: Bundle?)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.savedInstanceState = savedInstanceState
        if(savedInstanceState != null)
            restoreData(savedInstanceState);

        if(view == null){
            viewc = init(inflater,container);
        }

        return viewc;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(savedInstanceState != null)
            restoreData(savedInstanceState);

        resume();
    }
}