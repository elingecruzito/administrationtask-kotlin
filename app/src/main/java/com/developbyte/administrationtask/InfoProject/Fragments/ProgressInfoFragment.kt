package com.developbyte.administrationtask.InfoProject.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developbyte.administrationtask.Adapters.ListInfoTaskProgressAdapter
import com.developbyte.administrationtask.Adapters.Swipers.ListInfoTaskProgressSwiper
import com.developbyte.administrationtask.InfoProject.IInfoProject.IInfoProjectRepresentationDelegate
import com.developbyte.administrationtask.Model.TasksModel
import com.developbyte.administrationtask.R
import com.developbyte.administrationtask.Widgets.Utilerias


class ProgressInfoFragment: Fragment {

    private var viewc: View? = null
    private var lstInfoProgressTask: RecyclerView? = null

    private var tasksModelList: MutableList<TasksModel>? = null
    private var representationDelegate: IInfoProjectRepresentationDelegate? = null
    private var utilerias: Utilerias? = null

    constructor(tasksModelList: MutableList<TasksModel>?, representationDelegate: IInfoProjectRepresentationDelegate?, utilerias: Utilerias?) : super() {
        this.tasksModelList = tasksModelList
        this.representationDelegate = representationDelegate
        this.utilerias = utilerias
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewc = inflater.inflate(R.layout.fragment_info_progress, container, false)

        lstInfoProgressTask = viewc!!.findViewById(R.id.lst_info_progress_task)
        lstInfoProgressTask!!.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        lstInfoProgressTask!!.adapter = ListInfoTaskProgressAdapter(tasksModelList, requireContext())

        ItemTouchHelper(ListInfoTaskProgressSwiper(requireContext(), tasksModelList, representationDelegate, utilerias)).attachToRecyclerView(lstInfoProgressTask)

        return viewc
    }
}