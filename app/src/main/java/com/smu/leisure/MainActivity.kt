package com.smu.leisure

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.smu.leisure.base.ClickableArea
import com.smu.leisure.base.CommonUtils
import com.smu.leisure.base.Constants
import com.smu.leisure.base.Measurement
import com.smu.leisure.databinding.ActivityMainBinding
import com.smu.leisure.db.AppDatabase
import com.smu.leisure.db.LeisureDao
import com.smu.leisure.db.LeisureEntity
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.net.InetSocketAddress
import java.net.Socket
import java.util.ArrayList
import kotlin.random.Random

class MainActivity : AppCompatActivity(), View.OnClickListener{
    private lateinit var binding: ActivityMainBinding
    private var commonUtils = CommonUtils()

    lateinit var db : AppDatabase
    lateinit var leisureDao: LeisureDao

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(applicationContext)!!
        leisureDao = db.getLeisureDao()

        settingAreas()

//        binding.ivHuman.post(Runnable() {
//            run() {
//                Constants.ivHumanWidth = binding.ivHuman.width
//                Constants.ivHumanHeight = binding.ivHuman.height
//                val displayMetrics : DisplayMetrics = resources.displayMetrics
//
//                Log.e("eleutheria", "width : ${Constants.ivHumanWidth}, height : ${Constants.ivHumanHeight}")
//                Log.e("eleutheria", "screenwidth : ${displayMetrics.widthPixels}, screenheight : ${displayMetrics.heightPixels}")
//            }
//        });

        binding.ivHuman.setOnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                val touchX = motionEvent.x
                val touchY = motionEvent.y
                
                handleImageClick(touchX, touchY)
            }
            true
        }

        binding.btConnect.setOnClickListener(this)
        binding.btData.setOnClickListener(this)
        binding.btSetting.setOnClickListener(this)

        // Temp Test Data
        Constants.fSelIndex.add(12.3f)
        Constants.fSelIndex.add(4.7f)
        Constants.fSelIndex.add(6.3f)
        Constants.fSelIndex.add(11.8f)
        Constants.fSelIndex.add(9.4f)
        Constants.fSelIndex.add(16.4f)
        Constants.fSelIndex.add(7.1f)
        Constants.fSelIndex.add(8.3f)
        Constants.fSelIndex.add(18.8f)
        Constants.fSelIndex.add(12.4f)
        Constants.fSelIndex.add(6.3f)
        Constants.fSelIndex.add(9.7f)
        Constants.fSelIndex.add(10.2f)
        Constants.fSelIndex.add(13.2f)
        Constants.fSelIndex.add(1.9f)
        Constants.fSelIndex.add(15.8f)
    }

    private fun settingAreas() {
        Constants.arClickArea.add(
            ClickableArea(
                commonUtils.convertDpToPx(this, Constants.S01_LEFT_DP),
                commonUtils.convertDpToPx(this, Constants.S01_TOP_DP),
                commonUtils.convertDpToPx(this, Constants.S01_RIGHT_DP),
                commonUtils.convertDpToPx(this, Constants.S01_BOTTOM_DP),
                ::s01Click)
        )
        Constants.arClickArea.add(
            ClickableArea(
                commonUtils.convertDpToPx(this, Constants.S02_LEFT_DP),
                commonUtils.convertDpToPx(this, Constants.S02_TOP_DP),
                commonUtils.convertDpToPx(this, Constants.S02_RIGHT_DP),
                commonUtils.convertDpToPx(this, Constants.S02_BOTTOM_DP),
                ::s02Click)
        )
        Constants.arClickArea.add(
            ClickableArea(
                commonUtils.convertDpToPx(this, Constants.S03_LEFT_DP),
                commonUtils.convertDpToPx(this, Constants.S03_TOP_DP),
                commonUtils.convertDpToPx(this, Constants.S03_RIGHT_DP),
                commonUtils.convertDpToPx(this, Constants.S03_BOTTOM_DP),
                ::s03Click)
        )
        Constants.arClickArea.add(
            ClickableArea(
                commonUtils.convertDpToPx(this, Constants.S04_LEFT_DP),
                commonUtils.convertDpToPx(this, Constants.S04_TOP_DP),
                commonUtils.convertDpToPx(this, Constants.S04_RIGHT_DP),
                commonUtils.convertDpToPx(this, Constants.S04_BOTTOM_DP),
                ::s04Click)
        )
        Constants.arClickArea.add(
            ClickableArea(
                commonUtils.convertDpToPx(this, Constants.S05_LEFT_DP),
                commonUtils.convertDpToPx(this, Constants.S05_TOP_DP),
                commonUtils.convertDpToPx(this, Constants.S05_RIGHT_DP),
                commonUtils.convertDpToPx(this, Constants.S05_BOTTOM_DP),
                ::s05Click)
        )
        Constants.arClickArea.add(
            ClickableArea(
                commonUtils.convertDpToPx(this, Constants.S06_LEFT_DP),
                commonUtils.convertDpToPx(this, Constants.S06_TOP_DP),
                commonUtils.convertDpToPx(this, Constants.S06_RIGHT_DP),
                commonUtils.convertDpToPx(this, Constants.S06_BOTTOM_DP),
                ::s06Click)
        )
        Constants.arClickArea.add(
            ClickableArea(
                commonUtils.convertDpToPx(this, Constants.S07_LEFT_DP),
                commonUtils.convertDpToPx(this, Constants.S07_TOP_DP),
                commonUtils.convertDpToPx(this, Constants.S07_RIGHT_DP),
                commonUtils.convertDpToPx(this, Constants.S07_BOTTOM_DP),
                ::s07Click)
        )
        Constants.arClickArea.add(
            ClickableArea(
                commonUtils.convertDpToPx(this, Constants.S08_LEFT_DP),
                commonUtils.convertDpToPx(this, Constants.S08_TOP_DP),
                commonUtils.convertDpToPx(this, Constants.S08_RIGHT_DP),
                commonUtils.convertDpToPx(this, Constants.S08_BOTTOM_DP),
                ::s08Click)
        )
        Constants.arClickArea.add(
            ClickableArea(
                commonUtils.convertDpToPx(this, Constants.S09_LEFT_DP),
                commonUtils.convertDpToPx(this, Constants.S09_TOP_DP),
                commonUtils.convertDpToPx(this, Constants.S09_RIGHT_DP),
                commonUtils.convertDpToPx(this, Constants.S09_BOTTOM_DP),
                ::s09Click)
        )
        Constants.arClickArea.add(
            ClickableArea(
                commonUtils.convertDpToPx(this, Constants.S10_LEFT_DP),
                commonUtils.convertDpToPx(this, Constants.S10_TOP_DP),
                commonUtils.convertDpToPx(this, Constants.S10_RIGHT_DP),
                commonUtils.convertDpToPx(this, Constants.S10_BOTTOM_DP),
                ::s10Click)
        )
        Constants.arClickArea.add(
            ClickableArea(
                commonUtils.convertDpToPx(this, Constants.S11_LEFT_DP),
                commonUtils.convertDpToPx(this, Constants.S11_TOP_DP),
                commonUtils.convertDpToPx(this, Constants.S11_RIGHT_DP),
                commonUtils.convertDpToPx(this, Constants.S11_BOTTOM_DP),
                ::s11Click)
        )
        Constants.arClickArea.add(
            ClickableArea(
                commonUtils.convertDpToPx(this, Constants.S12_LEFT_DP),
                commonUtils.convertDpToPx(this, Constants.S12_TOP_DP),
                commonUtils.convertDpToPx(this, Constants.S12_RIGHT_DP),
                commonUtils.convertDpToPx(this, Constants.S12_BOTTOM_DP),
                ::s12Click)
        )
        Constants.arClickArea.add(
            ClickableArea(
                commonUtils.convertDpToPx(this, Constants.S13_LEFT_DP),
                commonUtils.convertDpToPx(this, Constants.S13_TOP_DP),
                commonUtils.convertDpToPx(this, Constants.S13_RIGHT_DP),
                commonUtils.convertDpToPx(this, Constants.S13_BOTTOM_DP),
                ::s13Click)
        )
        Constants.arClickArea.add(
            ClickableArea(
                commonUtils.convertDpToPx(this, Constants.S14_LEFT_DP),
                commonUtils.convertDpToPx(this, Constants.S14_TOP_DP),
                commonUtils.convertDpToPx(this, Constants.S14_RIGHT_DP),
                commonUtils.convertDpToPx(this, Constants.S14_BOTTOM_DP),
                ::s14Click)
        )
        Constants.arClickArea.add(
            ClickableArea(
                commonUtils.convertDpToPx(this, Constants.S15_LEFT_DP),
                commonUtils.convertDpToPx(this, Constants.S15_TOP_DP),
                commonUtils.convertDpToPx(this, Constants.S15_RIGHT_DP),
                commonUtils.convertDpToPx(this, Constants.S15_BOTTOM_DP),
                ::s15Click)
        )
        Constants.arClickArea.add(
            ClickableArea(
                commonUtils.convertDpToPx(this, Constants.S16_LEFT_DP),
                commonUtils.convertDpToPx(this, Constants.S16_TOP_DP),
                commonUtils.convertDpToPx(this, Constants.S16_RIGHT_DP),
                commonUtils.convertDpToPx(this, Constants.S16_BOTTOM_DP),
                ::s16Click)
        )
    }

    private fun handleImageClick(touchX: Float, touchY: Float) {
        for (area in Constants.arClickArea) {
            if(area.contains(touchX, touchY)) {
                area.performAction()
                return
            }
        }
        Toast.makeText(this, "Clicked Outside", Toast.LENGTH_SHORT).show()

    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("Exit")
            .setMessage("Are you sure you want to exit?")
            .setPositiveButton("Yes") { dialog, _ ->
                finishAffinity()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
    override fun onDestroy() {
        super.onDestroy()
        ObNetworkClient.disconnect()
    }

    override fun onClick(p0: View?) {
        when(p0!!.id) {
            R.id.btConnect -> {
//                Thread {
//                    ObNetworkClient.connect(Constants.NETWORK_IP, Constants.NETWORK_PORT, applicationContext)
//                }.start()
                insertTestData()
            }
            R.id.btData -> {
                val intent = Intent(this@MainActivity, DataActivity::class.java)
                startActivity(intent)

            }
            R.id.btSetting -> {
                val intent = Intent(this@MainActivity, SettingActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun showCustomDialog(index : Int) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_custom_degree, null)
        val dialogRoot = dialogView.findViewById<ConstraintLayout>(R.id.clDialogDegree)

        var strDegree : String = "0.0"

        when(index) {
            Constants.POINT_AREA_S01 -> {
                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S01 - 1]
                strDegree = dataItem.toString()
                // Change the background image
                dialogRoot.setBackgroundResource(R.drawable.popup)
            }
            Constants.POINT_AREA_S02 -> {
                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S02 - 1]
                strDegree = dataItem.toString()
                // Change the background image
                dialogRoot.setBackgroundResource(R.drawable.popup)
            }
            Constants.POINT_AREA_S03 -> {
                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S03 - 1]
                strDegree = dataItem.toString()
                // Change the background image
                dialogRoot.setBackgroundResource(R.drawable.popup)
            }
            Constants.POINT_AREA_S04 -> {
                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S04 - 1]
                strDegree = dataItem.toString()
                // Change the background image
                dialogRoot.setBackgroundResource(R.drawable.popup)
            }
            Constants.POINT_AREA_S05 -> {
                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S05 - 1]
                strDegree = dataItem.toString()
                // Change the background image
                dialogRoot.setBackgroundResource(R.drawable.popup)
            }
            Constants.POINT_AREA_S06 -> {
                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S06 - 1]
                strDegree = dataItem.toString()
                // Change the background image
                dialogRoot.setBackgroundResource(R.drawable.popup)
            }
            Constants.POINT_AREA_S07 -> {
                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S07 - 1]
                strDegree = dataItem.toString()
                // Change the background image
                dialogRoot.setBackgroundResource(R.drawable.popup)
            }
            Constants.POINT_AREA_S08 -> {
                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S08 - 1]
                strDegree = dataItem.toString()
                // Change the background image
                dialogRoot.setBackgroundResource(R.drawable.popup)
            }
            Constants.POINT_AREA_S09 -> {
                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S09 - 1]
                strDegree = dataItem.toString()
                // Change the background image
                dialogRoot.setBackgroundResource(R.drawable.popup)
            }
            Constants.POINT_AREA_S10 -> {
                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S10 - 1]
                strDegree = dataItem.toString()
                // Change the background image
                dialogRoot.setBackgroundResource(R.drawable.popup)
            }
            Constants.POINT_AREA_S11 -> {
                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S11 - 1]
                strDegree = dataItem.toString()
                // Change the background image
                dialogRoot.setBackgroundResource(R.drawable.popup)
            }
            Constants.POINT_AREA_S12 -> {
                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S12 - 1]
                strDegree = dataItem.toString()
                // Change the background image
                dialogRoot.setBackgroundResource(R.drawable.popup)
            }
            Constants.POINT_AREA_S13 -> {
                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S13 - 1]
                strDegree = dataItem.toString()
                // Change the background image
                dialogRoot.setBackgroundResource(R.drawable.popup)
            }
            Constants.POINT_AREA_S14 -> {
                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S14 - 1]
                strDegree = dataItem.toString()
                // Change the background image
                dialogRoot.setBackgroundResource(R.drawable.popup)
            }
            Constants.POINT_AREA_S15 -> {
                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S15 - 1]
                strDegree = dataItem.toString()
                // Change the background image
                dialogRoot.setBackgroundResource(R.drawable.popup)
            }
            Constants.POINT_AREA_S16 -> {
                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S16 - 1]
                strDegree = dataItem.toString()
                // Change the background image
                dialogRoot.setBackgroundResource(R.drawable.popup)
            }
        }

        // Create the dialog
        val dialog = Dialog(this)
        dialog.setContentView(dialogView)
        dialog.setCancelable(true) // Set to false if you don't want it to be dismissible

        // Access TextViews if you need to set text dynamically
        val tvDegree = dialogView.findViewById<TextView>(R.id.tvDegree)

        val strDegreeD = strDegree + getString(R.string.app_common_degree)
        tvDegree.text = strDegreeD

        // Show the dialog
        dialog.show()
    }

    private fun sendMessage(message: String) {
        Thread {
            ObNetworkClient.sendString(message)

            val response = ObNetworkClient.receiveString()
            if(response != null) {
                if (response.contains("Chest")) {
                    val startIndex = response.indexOf('{')
                    if (startIndex != -1) {
                        // Extract the JSON part from the raw data
                        val jsonData = response.substring(startIndex)
                        val modifiedJsonData = jsonData.replace("Back of Hand", "Hand")

                        val gson = Gson()
                        val jsonObject = JsonParser.parseString(modifiedJsonData).asJsonObject

                        val chestJson = jsonObject.getAsJsonObject("Chest")
                        val chestData = gson.fromJson(chestJson, Measurement::class.java)

                        val handJson = jsonObject.getAsJsonObject("Hand")
                        val handData = gson.fromJson(handJson, Measurement::class.java)

                        val wristJson = jsonObject.getAsJsonObject("Wrist")
                        val wristData = gson.fromJson(wristJson, Measurement::class.java)

                        val stomachJson = jsonObject.getAsJsonObject("Stomach")
                        val stomachData = gson.fromJson(stomachJson, Measurement::class.java)

                        val headJson = jsonObject.getAsJsonObject("Head")
                        val headData = gson.fromJson(headJson, Measurement::class.java)

                        // Assign the values to your variables
                        Constants.s01 = 0.0f
                        Constants.s02 = 0.0f
                        Constants.s03 = 0.0f
                        Constants.s04 = 0.0f
                        Constants.s05 = 0.0f

                        Thread {
                            leisureDao.insertTodo(
                                LeisureEntity(
                                    null,
                                    Constants.recentCreateddate,
                                    Constants.emergency,
                                    Constants.s01,
                                    Constants.s02,
                                    Constants.s03,
                                    Constants.s04,
                                    Constants.s05,
                                    Constants.s06,
                                    Constants.s07,
                                    Constants.s08,
                                    Constants.s09,
                                    Constants.s10,
                                    Constants.s11,
                                    Constants.s12,
                                    Constants.s13,
                                    Constants.s14,
                                    Constants.s15,
                                    Constants.s16,
                                )
                            )
                        }.start()
                    }
                }
            }
        }.start()
    }

    private fun saveRecentData(strFileName : String, strType : String) {
        Constants.recentCreateddate = strFileName
        Constants.emergency = 1
    }

    private fun connectClient() {
        Thread {
            val timeoutMillis = 5000

            val socket = Socket()

            try {
                val socketAddress = InetSocketAddress(Constants.NETWORK_IP, Constants.NETWORK_PORT)
                socket.connect(socketAddress, timeoutMillis)

                val writer = BufferedWriter(OutputStreamWriter(socket.getOutputStream()))
                writer.write("application")
                writer.flush()

                while(true) {
                    // Receive data from the socket
                    val inputStream = socket.getInputStream()
                    val buffer = ByteArray(1024)

                    val bytesRead = inputStream.read(buffer)
                    val byteData = if (bytesRead != buffer.size) buffer.copyOfRange(0, bytesRead) else buffer
                    val data = String(buffer, 0, bytesRead)
                }

            } catch (e: Exception) {
                println("Error during connect or transmission: ${e.message}")
            } finally {
                socket.close()
            }
        }.start()
    }

    private fun s01Click() {
        showCustomDialog(Constants.POINT_AREA_S01)
    }

    private fun s02Click() {
        showCustomDialog(Constants.POINT_AREA_S02)
    }

    private fun s03Click() {
        showCustomDialog(Constants.POINT_AREA_S03)
    }

    private fun s04Click() {
        showCustomDialog(Constants.POINT_AREA_S04)
    }

    private fun s05Click() {
        showCustomDialog(Constants.POINT_AREA_S05)
    }

    private fun s06Click() {
        showCustomDialog(Constants.POINT_AREA_S06)
    }

    private fun s07Click() {
        showCustomDialog(Constants.POINT_AREA_S07)
    }

    private fun s08Click() {
        showCustomDialog(Constants.POINT_AREA_S08)
    }

    private fun s09Click() {
        showCustomDialog(Constants.POINT_AREA_S09)
    }

    private fun s10Click() {
        showCustomDialog(Constants.POINT_AREA_S10)
    }

    private fun s11Click() {
        showCustomDialog(Constants.POINT_AREA_S11)
    }

    private fun s12Click() {
        showCustomDialog(Constants.POINT_AREA_S12)
    }

    private fun s13Click() {
        showCustomDialog(Constants.POINT_AREA_S13)
    }

    private fun s14Click() {
        showCustomDialog(Constants.POINT_AREA_S14)
    }

    private fun s15Click() {
        showCustomDialog(Constants.POINT_AREA_S15)
    }

    private fun s16Click() {
        showCustomDialog(Constants.POINT_AREA_S16)
    }

    fun insertTestData() {

        val createddate = commonUtils.getCurrentDateTime()
        val nEmergency = Random.nextInt(1, 3)
        val s01 = Random.nextFloat() * (10.0f - 0.1f) + 0.1f
        val s02 = Random.nextFloat() * (10.0f - 0.1f) + 0.1f
        val s03 = Random.nextFloat() * (10.0f - 0.1f) + 0.1f
        val s04 = Random.nextFloat() * (10.0f - 0.1f) + 0.1f
        val s05 = Random.nextFloat() * (10.0f - 0.1f) + 0.1f
        val s06 = Random.nextFloat() * (10.0f - 0.1f) + 0.1f
        val s07 = Random.nextFloat() * (10.0f - 0.1f) + 0.1f
        val s08 = Random.nextFloat() * (10.0f - 0.1f) + 0.1f
        val s09 = Random.nextFloat() * (10.0f - 0.1f) + 0.1f
        val s10 = Random.nextFloat() * (10.0f - 0.1f) + 0.1f
        val s11 = Random.nextFloat() * (10.0f - 0.1f) + 0.1f
        val s12 = Random.nextFloat() * (10.0f - 0.1f) + 0.1f
        val s13 = Random.nextFloat() * (10.0f - 0.1f) + 0.1f
        val s14 = Random.nextFloat() * (10.0f - 0.1f) + 0.1f
        val s15 = Random.nextFloat() * (10.0f - 0.1f) + 0.1f
        val s16 = Random.nextFloat() * (10.0f - 0.1f) + 0.1f

        Log.e("eleutheria", "strEmergency : $nEmergency")
        Thread {
            leisureDao.insertTodo(
                LeisureEntity(
                    null,
                    createddate,
                    nEmergency,
                    s01,
                    s02,
                    s03,
                    s04,
                    s05,
                    s06,
                    s07,
                    s08,
                    s09,
                    s10,
                    s11,
                    s12,
                    s13,
                    s14,
                    s15,
                    s16,
                )
            )
        }.start()
    }
}