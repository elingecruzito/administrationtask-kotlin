package com.developbyte.administrationtask.Home.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developbyte.administrationtask.Adapters.ListHomeTaskProgressAdapter
import com.developbyte.administrationtask.Adapters.Swipers.ListHomeTaskProgressSwiper
import com.developbyte.administrationtask.Home.IHome.IHomeRepresentationDelegate
import com.developbyte.administrationtask.Model.TasksModel
import com.developbyte.administrationtask.R
import com.developbyte.administrationtask.Widgets.Utilerias


class ProgressFragment: Fragment {


    private var tasksModelList: MutableList<TasksModel>? = null
    private var representationDelegate: IHomeRepresentationDelegate? = null
    private var utilerias: Utilerias? = null

    private var viewc: View? = null
    private var lstTaskProgress: RecyclerView? = null


    constructor(tasksModelList: MutableList<TasksModel>?, representationDelegate: IHomeRepresentationDelegate?, utilerias: Utilerias?) {
        this.tasksModelList = tasksModelList
        this.representationDelegate = representationDelegate
        this.utilerias = utilerias
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewc = inflater.inflate(R.layout.fragment_progress, container, false)

        lstTaskProgress = viewc!!.findViewById(R.id.lst_task_progress)
        lstTaskProgress!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        lstTaskProgress!!.adapter = ListHomeTaskProgressAdapter(tasksModelList!!, context, representationDelegate)

        ItemTouchHelper(ListHomeTaskProgressSwiper(context, tasksModelList, representationDelegate, utilerias)).attachToRecyclerView(lstTaskProgress)

        return viewc
    }
}