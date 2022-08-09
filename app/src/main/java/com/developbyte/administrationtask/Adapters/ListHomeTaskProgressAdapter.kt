package com.developbyte.administrationtask.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.developbyte.administrationtask.Home.IHome.IHomeRepresentationDelegate
import com.developbyte.administrationtask.Model.TasksModel
import com.developbyte.administrationtask.R


class ListHomeTaskProgressAdapter: RecyclerView.Adapter<ListHomeTaskProgressAdapter.ViewHolder>{
    var tasksModelList: MutableList<TasksModel>? = null
    var context: Context? = null
    var representationDelegate: IHomeRepresentationDelegate? = null

    constructor(tasksModelList: MutableList<TasksModel>, context: Context?, representationDelegate: IHomeRepresentationDelegate?) {
        this.tasksModelList = tasksModelList
        this.context = context
        this.representationDelegate = representationDelegate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.widget_task_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.lyItemTask.setOnClickListener{
            representationDelegate!!.showInfoProject(tasksModelList!![position].id_project)
        }
        holder.iconStatusTask.background = context!!.resources.getDrawable(R.mipmap.progress)
        holder.txtNameTask.text = tasksModelList!![position].task
        holder.txtNameProject.text = tasksModelList!![position].project
        holder.txtDateTask.text = tasksModelList!![position].hour
    }

    override fun getItemCount(): Int {
        return if (tasksModelList == null) 0 else tasksModelList!!.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lyItemTask: RelativeLayout = itemView.findViewById(R.id.ly_item_task)
        val iconStatusTask: ImageView = itemView.findViewById(R.id.icon_status_task)
        val txtNameTask: AppCompatTextView = itemView.findViewById(R.id.txt_name_task)
        val txtDateTask: AppCompatTextView = itemView.findViewById(R.id.txt_date_task)
        val txtNameProject: AppCompatTextView = itemView.findViewById(R.id.txt_name_project)

    }
}