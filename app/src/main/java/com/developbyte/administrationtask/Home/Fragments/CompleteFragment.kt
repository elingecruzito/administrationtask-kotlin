package com.developbyte.administrationtask.Home.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developbyte.administrationtask.Adapters.ListHomeTaskCompleteAdapter
import com.developbyte.administrationtask.Adapters.Swipers.ListHomeTaskCompleteSwiper
import com.developbyte.administrationtask.Home.IHome.IHomeRepresentationDelegate
import com.developbyte.administrationtask.Model.TasksModel
import com.developbyte.administrationtask.R
import com.developbyte.administrationtask.Widgets.Utilerias


class CompleteFragment: Fragment {

    private var tasksModelList: MutableList<TasksModel>? = null
    private var representationDelegate: IHomeRepresentationDelegate? = null
    private var utilerias: Utilerias? = null

    private var viec: View? = null
    private var lstTaskComplete: RecyclerView? = null

    constructor(tasksModelList: MutableList<TasksModel>?, representationDelegate: IHomeRepresentationDelegate?, utilerias: Utilerias?) : super() {
        this.tasksModelList = tasksModelList
        this.representationDelegate = representationDelegate
        this.utilerias = utilerias
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viec = inflater.inflate(R.layout.fragment_complete, container, false)

        lstTaskComplete = viec!!.findViewById(R.id.lst_task_complete)
        lstTaskComplete!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        lstTaskComplete!!.adapter = ListHomeTaskCompleteAdapter(tasksModelList!!, context, representationDelegate!!)

        ItemTouchHelper(ListHomeTaskCompleteSwiper(context, tasksModelList!!, representationDelegate, utilerias)).attachToRecyclerView(lstTaskComplete)

        return viec
    }
}