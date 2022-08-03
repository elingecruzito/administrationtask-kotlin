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


class ListInfoTaskProgressAdapter: RecyclerView.Adapter<ListInfoTaskProgressAdapter.ViewHolder>{

    var tasksModels: List<TasksModel>? = null
    var context: Context? = null

    constructor(tasksModels: List<TasksModel>?, context: Context?) {
        this.tasksModels = tasksModels
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.widget_task_info_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imgInfoTask.background = context!!.getResources().getDrawable(R.mipmap.progress_task)
        holder.txtNameInfoTask.text = tasksModels!![position].task
        holder.txtDateInfoTask.text = tasksModels!![position].date
    }

    override fun getItemCount(): Int {
        return if (tasksModels == null) 0 else tasksModels!!.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgInfoTask: AppCompatImageView
        val txtNameInfoTask: AppCompatTextView
        val txtDateInfoTask: AppCompatTextView

        init {
            imgInfoTask = itemView.findViewById(R.id.img_info_task)
            txtNameInfoTask = itemView.findViewById(R.id.txt_name_info_task)
            txtDateInfoTask = itemView.findViewById(R.id.txt_date_info_task)
        }
    }

}