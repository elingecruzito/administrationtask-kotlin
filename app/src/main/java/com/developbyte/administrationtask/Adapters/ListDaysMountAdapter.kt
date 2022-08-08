package com.developbyte.administrationtask.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.developbyte.administrationtask.Home.IHome
import com.developbyte.administrationtask.Home.IHome.IHomeRepresentationDelegate
import com.developbyte.administrationtask.Model.DaysMountModel
import com.developbyte.administrationtask.R


class ListDaysMountAdapter : RecyclerView.Adapter<ListDaysMountAdapter.ViewHolder> {

    private var daysMountModelList: MutableList<DaysMountModel>? = null
    private var context: Context? = null
    var indexToday = -1
    private var month = 0
    private var representationDelegate: IHomeRepresentationDelegate? = null

    constructor(context: Context, representationDelegate: IHome.IHomeRepresentationDelegate) : super(){
        this.context = context
        this.representationDelegate = representationDelegate
    }

    fun setDaysMountModel(daysMountModelList: MutableList<DaysMountModel>, month: Int){
        this.daysMountModelList = daysMountModelList
        this.month = month
        if( indexToday < 0 ){
            for ( i in 0..daysMountModelList.size){
                if(daysMountModelList[i].isToday){
                    indexToday = i
                    break
                }
            }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.widget_days_mount, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cardDayMonth!!.setCardBackgroundColor(context!!.resources.getColor(R.color.white))
        holder.txtDayMount!!.setTextColor(context!!.resources.getColor(R.color.color_text))
        holder.txtDayTextMount!!.setTextColor(context!!.resources.getColor(R.color.color_text))

        if (daysMountModelList!![position].selected) {
            holder.cardDayMonth!!.setCardBackgroundColor(context!!.resources.getColor(R.color.background_form_buttons))
            holder.txtDayMount!!.setTextColor(context!!.resources.getColor(R.color.white))
            holder.txtDayTextMount!!.setTextColor(context!!.resources.getColor(R.color.white))
        }
        if (daysMountModelList!![position].isToday) {
            holder.cardDayMonth!!.setCardBackgroundColor(context!!.resources.getColor(R.color.blue_background))
            holder.txtDayMount!!.setTextColor(context!!.resources.getColor(R.color.white))
            holder.txtDayTextMount!!.setTextColor(context!!.resources.getColor(R.color.white))
        }

        holder.txtDayMount!!.text = daysMountModelList!![position].day.toString()
        holder.txtDayTextMount!!.text = daysMountModelList!![position].day_text

        holder.cardDayMonth!!.setOnClickListener {
            indexToday = position
            representationDelegate!!.getDaysOfCurrentMount(month)
            val sizeList = daysMountModelList!!.size - 1
            for (i in 0..sizeList) {
                daysMountModelList!![i].selected = false
                if (i == position) {
                    daysMountModelList!![i].selected = true
                }
            }
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return if (daysMountModelList == null) 0 else daysMountModelList!!.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val cardDayMonth: CardView = itemView.findViewById(R.id.card_day_month)
        val txtDayMount: AppCompatTextView = itemView.findViewById(R.id.txt_day_mount)
        val txtDayTextMount: AppCompatTextView = itemView.findViewById(R.id.txt_day_text_mount)

    }

}