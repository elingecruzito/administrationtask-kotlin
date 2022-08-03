package com.developbyte.administrationtask.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.developbyte.administrationtask.Model.TasksModel
import com.developbyte.administrationtask.R


class ListNewTaskAdapter: RecyclerView.Adapter<ListNewTaskAdapter.ViewHolder>(){

    private var listTasksModels: MutableList<TasksModel>? = null

    fun setTasksModel(tasksModel: TasksModel) {
        listTasksModels!!.add(tasksModel)
        notifyDataSetChanged()
    }

    fun getListTasksModels(): List<TasksModel>? {
        return listTasksModels
    }

    fun clearListTaskModels() {
        if (listTasksModels != null) {
            listTasksModels!!.clear()
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.widget_new_task_project, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtNameNewTaskProject.text = listTasksModels!![position].task
        holder.txtDateNewTaskProject.text = listTasksModels!![position].date
        holder.txtHourNewTaskProject.text = listTasksModels!![position].hour
        holder.btnDeleteItemTask.setOnClickListener{
            listTasksModels!!.removeAt(position)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return if (listTasksModels == null) 0 else listTasksModels!!.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtDateNewTaskProject: AppCompatTextView
        val txtHourNewTaskProject: AppCompatTextView
        val txtNameNewTaskProject: AppCompatTextView
        val btnDeleteItemTask: AppCompatImageView

        init {
            txtDateNewTaskProject = itemView.findViewById(R.id.txt_date_new_task_project)
            txtHourNewTaskProject = itemView.findViewById(R.id.txt_hour_new_task_project)
            txtNameNewTaskProject = itemView.findViewById(R.id.txt_name_new_task_project)
            btnDeleteItemTask = itemView.findViewById(R.id.btn_delete_item_task)
        }
    }

}