package com.developbyte.administrationtask.Adapters.Swipers

import android.content.Context
import android.graphics.Canvas
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.developbyte.administrationtask.InfoProject.IInfoProject.IInfoProjectRepresentationDelegate
import com.developbyte.administrationtask.Model.TasksModel
import com.developbyte.administrationtask.Widgets.Utilerias


class ListInfoTaskProgressSwiper: ItemTouchHelper.Callback{

    private var context: Context? = null
    private var tasksModelList: MutableList<TasksModel>? = null
    private var representationDelegate: IInfoProjectRepresentationDelegate? = null
    private var utilerias: Utilerias? = null

    constructor(context: Context?, tasksModelList: MutableList<TasksModel>?, representationDelegate: IInfoProjectRepresentationDelegate?, utilerias: Utilerias?) {
        this.context = context
        this.tasksModelList = tasksModelList
        this.representationDelegate = representationDelegate
        this.utilerias = utilerias
    }

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        return makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        if (direction == ItemTouchHelper.RIGHT) {
            representationDelegate!!.updateStatusTask(tasksModelList!![viewHolder.position].id_task)
        } else if (direction == ItemTouchHelper.LEFT) {
            representationDelegate!!.deleteTask(tasksModelList!![viewHolder.position].id_task)
        }
    }

    override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            SwiperChildDraw(c, viewHolder, dX, utilerias!!, context!!)
        } else {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        }
    }

}