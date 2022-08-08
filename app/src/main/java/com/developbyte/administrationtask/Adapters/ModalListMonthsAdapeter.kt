package com.developbyte.administrationtask.Adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.developbyte.administrationtask.Model.MonthsModel
import com.developbyte.administrationtask.R


class ModalListMonthsAdapeter: RecyclerView.Adapter<ModalListMonthsAdapeter.ViewHolder>{

    private var modelList: MutableList<MonthsModel>? = null
    var context: Context? = null
    var indexSelected = 0
    var sizeList:Int = 0

    constructor(modelList: MutableList<MonthsModel>, context: Context?) {
        this.modelList = modelList
        this.context = context
        sizeList = modelList.size - 1
        for (i in 0..sizeList) {
            if (modelList[i].isActive) {
                indexSelected = i
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.widget_items_list_modal_monts, parent, false)
        return ViewHolder(itemView)
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.lyModalMonthYear.setBackgroundColor(
                context!!.getColor(
                        if (modelList!![position].isActive) R.color.blue_background else R.color.white
                )
        )
        holder.lyModalMonthYear.setOnClickListener{
            setSelectedOption(position)
        }
        holder.lblValueItemModalMonths.text = modelList!![position].month
        holder.lblValueItemModalMonths.setTextColor(context!!.getColor(
                if (modelList!![position].isActive) R.color.white else R.color.black
        ))
        holder.lblValueItemModalMonths.setOnClickListener{
            setSelectedOption(position)
        }
    }

    private fun setSelectedOption(position: Int) {
        for (i in 0..sizeList) {
            if (i == position) {
                indexSelected = position + 1
                modelList!![i].isActive = true
            } else {
                modelList!![i].isActive = false
            }
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if (modelList == null) 0 else modelList!!.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lyModalMonthYear: RelativeLayout = itemView.findViewById(R.id.ly_modal_month_year)
        val lblValueItemModalMonths: AppCompatTextView = itemView.findViewById(R.id.lbl_value_item_modal_months)

    }

}