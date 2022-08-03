package com.developbyte.administrationtask.Adapters

import com.developbyte.administrationtask.R
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


class ListHomeTaskCompleteAdapter: RecyclerView.Adapter<ListHomeTaskCompleteAdapter.ViewHolder> {

    private var tasksModelList: List<TasksModel>? = null
    private var context: Context? = null
    private var representationDelegate: IHomeRepresentationDelegate? = null

    constructor(tasksModelList: List<TasksModel>?, context: Context?, representationDelegate: IHomeRepresentationDelegate?) {
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
            //representationDelegate!!.showInfoProject(tasksModelList!![position].id_project)
        }
        holder.iconStatusTask.setBackground(context!!.resources.getDrawable(R.mipmap.check))
        holder.txtNameTask.text = tasksModelList!![position].task
        holder.txtNameProject.text = tasksModelList!![position].project
        holder.txtDateTask.text = tasksModelList!![position].hour
    }

    override fun getItemCount(): Int {
        return if (tasksModelList == null) 0 else tasksModelList!!.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val lyItemTask: RelativeLayout
        val iconStatusTask: ImageView
        val txtNameTask: AppCompatTextView
        val txtDateTask: AppCompatTextView
        val txtNameProject: AppCompatTextView

        init {
            lyItemTask = itemView.findViewById(R.id.ly_item_task)
            iconStatusTask = itemView.findViewById(R.id.icon_status_task)
            txtNameTask = itemView.findViewById(R.id.txt_name_task)
            txtDateTask = itemView.findViewById(R.id.txt_date_task)
            txtNameProject = itemView.findViewById(R.id.txt_name_project)
        }
    }
}