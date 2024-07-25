package com.smu.leisure

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.smu.leisure.base.Constants
import com.smu.leisure.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvS01.text = Constants.LeisureData.s01.toString()
        binding.tvS02.text = Constants.LeisureData.s02.toString()
        binding.tvS03.text = Constants.LeisureData.s03.toString()
        binding.tvS04.text = Constants.LeisureData.s04.toString()
        binding.tvS05.text = Constants.LeisureData.s05.toString()
        binding.tvS06.text = Constants.LeisureData.s06.toString()
        binding.tvS07.text = Constants.LeisureData.s07.toString()
        binding.tvS08.text = Constants.LeisureData.s08.toString()
        binding.tvS09.text = Constants.LeisureData.s09.toString()
        binding.tvS10.text = Constants.LeisureData.s10.toString()
        binding.tvS11.text = Constants.LeisureData.s11.toString()
        binding.tvS12.text = Constants.LeisureData.s12.toString()
        binding.tvS13.text = Constants.LeisureData.s13.toString()
        binding.tvS14.text = Constants.LeisureData.s14.toString()
        binding.tvS15.text = Constants.LeisureData.s15.toString()
        binding.tvS16.text = Constants.LeisureData.s16.toString()


//        binding.ivDetailHuman.setOnTouchListener { view, motionEvent ->
//            if(motionEvent.action == MotionEvent.ACTION_DOWN) {
//                val touchX = motionEvent.x
//                val touchY = motionEvent.y
//
//                Log.e("eleutheria", "touchX : $touchX, touchY : $touchY")
//            }
//            true
//        }
    }

    private fun showCustomDialog(index : Int) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_custom_degree, null)
        val dialogRoot = dialogView.findViewById<ConstraintLayout>(R.id.clDialogDegree)

        var strAngle = "0.0f"

        when(index) {

        }

        // Create the dialog
        val dialog = Dialog(this)
        dialog.setContentView(dialogView)
        dialog.setCancelable(true) // Set to false if you don't want it to be dismissible

        // Access TextViews if you need to set text dynamically
        val tvDegree = dialogView.findViewById<TextView>(R.id.tvDegree)

        val strAngleDegree = strAngle + getString(R.string.app_common_degree)
        tvDegree.text = strAngleDegree

        // Show the dialog
        dialog.show()
    }
}