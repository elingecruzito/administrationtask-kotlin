package com.developbyte.administrationtask.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.developbyte.administrationtask.Model.TasksModel
import com.developbyte.administrationtask.R


class ListInfoTaskCompleteAdapter: RecyclerView.Adapter<ListInfoTaskCompleteAdapter.ViewHolder> {

    var tasksModels: MutableList<TasksModel>? = null
    var context: Context? = null

    constructor(tasksModels: MutableList<TasksModel>?, context: Context?) {
        this.tasksModels = tasksModels
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.widget_task_info_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imgInfoTask.background = context!!.resources.getDrawable(R.mipmap.check)
        holder.txtNameInfoTask.text = tasksModels!![position].task
        holder.txtDateInfoTask.text = tasksModels!![position].date
    }

    override fun getItemCount(): Int {
        return if (tasksModels == null) 0 else tasksModels!!.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgInfoTask: AppCompatImageView = itemView.findViewById(R.id.img_info_task)
        val txtNameInfoTask: AppCompatTextView = itemView.findViewById(R.id.txt_name_info_task)
        val txtDateInfoTask: AppCompatTextView = itemView.findViewById(R.id.txt_date_info_task)

    }

}