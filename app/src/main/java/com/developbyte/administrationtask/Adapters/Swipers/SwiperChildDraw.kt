package com.developbyte.administrationtask.Adapters.Swipers

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.developbyte.administrationtask.R
import com.developbyte.administrationtask.Widgets.Utilerias


class SwiperChildDraw {
    private var icon: Bitmap? = null
    private var itemView: View? = null
    private val p: Paint = Paint()
    val ALPHA_FULL = 1.0f

    constructor(c: Canvas, viewHolder: RecyclerView.ViewHolder, dX: Float, utilerias: Utilerias, context: Context) {
        itemView = viewHolder.itemView
        if (dX > 0) {
            icon = utilerias.drawableToBitmap(ContextCompat.getDrawable(context, R.drawable.ic_baseline_post_add)!!)
            p.setColor(context.getResources().getColor(R.color.blue_background))
            c.drawRect(itemView!!.left.toFloat(), itemView!!.top.toFloat(), dX,
                    itemView!!.bottom.toFloat(), p)
            c.drawBitmap(icon!!,
                    itemView!!.left.toFloat() + utilerias.convertDpToPx(16, context),
                    itemView!!.top.toFloat() + (itemView!!.bottom.toFloat() - itemView!!.top.toFloat() - icon!!.height) / 2,
                    p)
        } else {
            icon = utilerias.drawableToBitmap(ContextCompat.getDrawable(context, R.drawable.ic_baseline_delete_white)!!)
            p.setColor(context.getResources().getColor(R.color.btn_color_red))
            c.drawRect(itemView!!.right.toFloat() + dX, itemView!!.top.toFloat(),
                    itemView!!.right.toFloat(), itemView!!.bottom.toFloat(), p)
            c.drawBitmap(icon!!,
                    itemView!!.right.toFloat() - utilerias.convertDpToPx(16, context) - icon!!.width,
                    itemView!!.top.toFloat() + (itemView!!.bottom.toFloat() - itemView!!.top.toFloat() - icon!!.height) / 2,
                    p)
        }
        viewHolder.itemView.alpha = ALPHA_FULL - Math.abs(dX) / viewHolder.itemView.width.toFloat()
        viewHolder.itemView.translationX = dX
    }
}