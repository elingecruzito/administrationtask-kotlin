package com.developbyte.administrationtask.InfoProject.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developbyte.administrationtask.Adapters.ListInfoTaskCompleteAdapter
import com.developbyte.administrationtask.Adapters.Swipers.ListInfoTaskCompleteSwiper
import com.developbyte.administrationtask.InfoProject.IInfoProject.IInfoProjectRepresentationDelegate
import com.developbyte.administrationtask.Model.TasksModel
import com.developbyte.administrationtask.R
import com.developbyte.administrationtask.Widgets.Utilerias


class CompleteInfoFragment: Fragment {

    private var viewc: View? = null
    private var lstInfoCompletTask: RecyclerView? = null

    private var tasksModelList: MutableList<TasksModel>? = null
    private var representationDelegate: IInfoProjectRepresentationDelegate? = null
    private var utilerias: Utilerias? = null

    constructor(tasksModelList: MutableList<TasksModel>?, representationDelegate: IInfoProjectRepresentationDelegate?, utilerias: Utilerias?) : super() {
        this.tasksModelList = tasksModelList
        this.representationDelegate = representationDelegate
        this.utilerias = utilerias
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewc = inflater.inflate(R.layout.fragment_info_complet, container, false)

        lstInfoCompletTask = viewc!!.findViewById(R.id.lst_info_complet_task)
        lstInfoCompletTask!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        lstInfoCompletTask!!.adapter = ListInfoTaskCompleteAdapter(tasksModelList, context)

        ItemTouchHelper(ListInfoTaskCompleteSwiper(context, tasksModelList, representationDelegate, utilerias)).attachToRecyclerView(lstInfoCompletTask)


        return viewc
    }
}