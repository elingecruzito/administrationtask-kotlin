package com.developbyte.administrationtask.Widgets

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.developbyte.administrationtask.R
import java.text.SimpleDateFormat
import java.util.*


class Utilerias {

    private var calendar: Calendar? = null
    private var dateFormat: SimpleDateFormat? = null

    private var activity: Activity? = null
    private var dialogBuilder: AlertDialog.Builder? = null
    private var alertDialog: AlertDialog? = null
    private var viewModal: View? = null
    private var imgIconTypeModal: AppCompatImageView? = null
    private var lblMessageModal: AppCompatTextView? = null
    private var btnCancelModal: AppCompatButton? = null
    private var btnConfirmationModal: AppCompatButton? = null

    private var icon: Bitmap? = null
    private var canvas: Canvas? = null

    constructor(){
        calendar = Calendar.getInstance()
        dateFormat = SimpleDateFormat("dd/MM/yyyy")
    }

    fun setActivity(activity: Activity){
        this.activity = activity
        generateViewModal()
    }

    fun getDatePickerDialog(context: Context?, editText: AppCompatEditText): DatePickerDialog? {
        return DatePickerDialog(
            context!!, R.style.DialogTheme,
            { datePicker, year, month, day ->
                calendar!![Calendar.YEAR] = year
                calendar!![Calendar.MONTH] = month
                calendar!![Calendar.DAY_OF_MONTH] = day
                editText.setText(dateFormat!!.format(calendar!!.time))
            }, calendar!![Calendar.YEAR], calendar!![Calendar.MONTH],
            calendar!![Calendar.DAY_OF_MONTH]
        )
    }

    fun getTimePickerDialog(context: Context?, editText: AppCompatEditText): TimePickerDialog? {
        return TimePickerDialog(
            context!!, R.style.DialogTheme,
            { timePicker, hourOfDay, minute ->
                calendar!![Calendar.HOUR_OF_DAY] = hourOfDay
                calendar!![Calendar.MINUTE] = minute
                editText.setText("$hourOfDay:$minute")
            }, calendar!![Calendar.HOUR_OF_DAY], calendar!![Calendar.MINUTE], false
        )
    }

    private fun generateViewModal() {
        dialogBuilder = AlertDialog.Builder(activity)
        viewModal = activity!!.layoutInflater.inflate(R.layout.widget_modal_messages, null)
        imgIconTypeModal = viewModal!!.findViewById(R.id.img_icon_type_modal)
        lblMessageModal = viewModal!!.findViewById(R.id.lbl_message_modal)
        btnConfirmationModal = viewModal!!.findViewById(R.id.btn_confirmation_modal)
        btnCancelModal = viewModal!!.findViewById(R.id.btn_cancel_modal)
        btnCancelModal?.setOnClickListener(View.OnClickListener { alertDialog!!.dismiss() })
        alertDialog = dialogBuilder!!.setView(viewModal).create()
    }

    fun showMessageConfirmation(message: Int, runnable: Runnable) {
        imgIconTypeModal!!.background = activity!!.getDrawable(R.mipmap.icon_info_96)
        lblMessageModal!!.text = activity!!.getString(message)
        btnCancelModal!!.visibility = View.VISIBLE
        btnConfirmationModal!!.setOnClickListener {
            alertDialog!!.dismiss()
            runnable.run()
        }
        alertDialog!!.show()
    }

    fun showMessageError(message: Int) {
        imgIconTypeModal!!.background = activity!!.getDrawable(R.mipmap.icon_cancelar_96)
        lblMessageModal!!.text = activity!!.getString(message)
        btnCancelModal!!.visibility = View.GONE
        btnConfirmationModal!!.setOnClickListener { alertDialog!!.dismiss() }
        alertDialog!!.show()
    }

    fun showMessageConfirmation(message: Int){
        imgIconTypeModal!!.background = activity!!.getDrawable(R.mipmap.icon_info_96)
        lblMessageModal!!.text = activity!!.getString(message)
        btnCancelModal!!.visibility = View.GONE
        btnConfirmationModal!!.setOnClickListener {
            alertDialog!!.dismiss()
        }
        alertDialog!!.show()
    }

    fun randomString(): String? {
        val DATA = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val RANDOM = Random()
        val sb = StringBuilder(RANDOM.nextInt(999))
        for (value in 0..sb.capacity()) {
            sb.append(DATA[RANDOM.nextInt(DATA.length)])
        }
        return sb.toString()
    }

    fun drawableToBitmap(drawable: Drawable): Bitmap? {
        icon = null
        if (drawable is BitmapDrawable) {
            val bitmapDrawable = drawable
            if (bitmapDrawable.bitmap != null) {
                return bitmapDrawable.bitmap
            }
        }
        icon = if (drawable.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0) {
            Bitmap.createBitmap(
                1,
                1,
                Bitmap.Config.ARGB_8888
            ) // Single color bitmap will be created of 1x1 pixel
        } else {
            Bitmap.createBitmap(
                drawable.intrinsicWidth,
                drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
        }
        canvas = Canvas(icon!!)
        drawable.setBounds(0, 0, canvas!!.width, canvas!!.height)
        drawable.draw(canvas!!)
        return icon
    }

    fun convertDpToPx(dp: Int, context: Context): Int {
        return Math.round(dp * (context.resources.displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
    }
}