package com.smu.leisure.base

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.widget.TextView
import com.smu.leisure.R

class CustomDialog (
    context: Context,
    private var nIndex: Int
) : Dialog(context) {
    private lateinit var tvNumber: TextView
    private lateinit var tvDegree: TextView
    private var strPhysical: String = "0.0"
    private var strDegree: String = "Head"
    private var bIsMinus: Boolean = false

    init {

        val dialogView = layoutInflater.inflate(R.layout.dialog_custom_degree, null)
        setContentView(dialogView)

        tvNumber = findViewById(R.id.tvNumber)
        tvDegree = findViewById(R.id.tvDegree)

        val dataItem = Constants.fSelIndex[nIndex - 1]
        strDegree = dataItem.toString()
        strPhysical = Constants.BODY_SELECTION[nIndex - 1]

        if(dataItem < 0) {
            bIsMinus = true
        } else {
            bIsMinus = false
        }

        dialogView.setBackgroundResource(R.drawable.popup)
        setupDialog()
    }

    private fun setupDialog() {
        // Apply logic for changing color based on condition
        if (this.bIsMinus) {
            tvDegree.setTextColor(context.getColor(R.color.red))
        } else {
            tvDegree.setTextColor(context.getColor(R.color.black))
        }

        tvNumber.text = strPhysical
        val strDegreeD = "$strDegree ${context.getString(R.string.app_common_degree)}"
        tvDegree.text = strDegreeD
    }

    fun updateValue(index: Int) {
        val dataItem = Constants.fSelIndex[index - 1]
        this.strDegree = dataItem.toString()
        this.strPhysical = Constants.BODY_SELECTION[index - 1]

//        Log.e("eleutheria2", "updataValue : $index, strDegree : ${this.strDegree}")
        if(dataItem < 0) {
            this.bIsMinus = true
        } else {
            this.bIsMinus = false
        }

        tvNumber.text = this.strPhysical
        val strDegreeD = "${this.strDegree} ${context.getString(R.string.app_common_degree)}"
        tvDegree.text = strDegreeD

        if (this.bIsMinus) {
            tvDegree.setTextColor(context.getColor(R.color.red))
        } else {
            tvDegree.setTextColor(context.getColor(R.color.black))
        }
    }
}