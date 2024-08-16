package com.smu.leisure

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
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
import com.smu.leisure.base.ClickableArea
import com.smu.leisure.base.CommonUtils
import com.smu.leisure.base.Constants
import com.smu.leisure.databinding.ActivityMainBinding
import com.smu.leisure.db.AppDatabase
import com.smu.leisure.db.LeisureDao
import com.smu.leisure.db.LeisureEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.InetSocketAddress
import java.net.Socket
import java.net.SocketAddress
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

        binding.ivHuman.setOnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                val touchX = motionEvent.x
                val touchY = motionEvent.y

                handleImageClick(touchX, touchY)
            }
            true
        }

        binding.ivHuman.post {
            // Now you can get the width and height
            Constants.ivHumanWidth = binding.ivHuman.width
            Constants.ivHumanHeight = binding.ivHuman.height

            settingAreas()
        }

        binding.btStart.setOnClickListener(this)
        binding.btData.setOnClickListener(this)
        binding.btSetting.setOnClickListener(this)

        // Temp Test Data
        Constants.fSelIndex.add(1.1f)
        Constants.fSelIndex.add(2.2f)
        Constants.fSelIndex.add(3.3f)
        Constants.fSelIndex.add(4.4f)
        Constants.fSelIndex.add(5.5f)
        Constants.fSelIndex.add(6.6f)
        Constants.fSelIndex.add(7.7f)
        Constants.fSelIndex.add(8.8f)
        Constants.fSelIndex.add(9.9f)
        Constants.fSelIndex.add(10.0f)
        Constants.fSelIndex.add(11.1f)
        Constants.fSelIndex.add(12.2f)
        Constants.fSelIndex.add(13.3f)
        Constants.fSelIndex.add(14.4f)
        Constants.fSelIndex.add(15.5f)
        Constants.fSelIndex.add(16.6f)
    }

    private fun settingAreas() {
        Constants.arClickArea.add(
            ClickableArea(
                commonUtils.calculateRatioX(this, Constants.S01_LEFT_DP),
                commonUtils.calculateRatioY(this, Constants.S01_TOP_DP),
                commonUtils.calculateRatioX(this, Constants.S01_RIGHT_DP),
                commonUtils.calculateRatioY(this, Constants.S01_BOTTOM_DP),
                ::s01Click)
        )
        Constants.arClickArea.add(
            ClickableArea(
                commonUtils.calculateRatioX(this, Constants.S02_LEFT_DP),
                commonUtils.calculateRatioY(this, Constants.S02_TOP_DP),
                commonUtils.calculateRatioX(this, Constants.S02_RIGHT_DP),
                commonUtils.calculateRatioY(this, Constants.S02_BOTTOM_DP),
                ::s02Click)
        )
        Constants.arClickArea.add(
            ClickableArea(
                commonUtils.calculateRatioX(this, Constants.S03_LEFT_DP),
                commonUtils.calculateRatioY(this, Constants.S03_TOP_DP),
                commonUtils.calculateRatioX(this, Constants.S03_RIGHT_DP),
                commonUtils.calculateRatioY(this, Constants.S03_BOTTOM_DP),
                ::s03Click)
        )
        Constants.arClickArea.add(
            ClickableArea(
                commonUtils.calculateRatioX(this, Constants.S04_LEFT_DP),
                commonUtils.calculateRatioY(this, Constants.S04_TOP_DP),
                commonUtils.calculateRatioX(this, Constants.S04_RIGHT_DP),
                commonUtils.calculateRatioY(this, Constants.S04_BOTTOM_DP),
                ::s04Click)
        )
        Constants.arClickArea.add(
            ClickableArea(
                commonUtils.calculateRatioX(this, Constants.S05_LEFT_DP),
                commonUtils.calculateRatioY(this, Constants.S05_TOP_DP),
                commonUtils.calculateRatioX(this, Constants.S05_RIGHT_DP),
                commonUtils.calculateRatioY(this, Constants.S05_BOTTOM_DP),
                ::s05Click)
        )
        Constants.arClickArea.add(
            ClickableArea(
                commonUtils.calculateRatioX(this, Constants.S06_LEFT_DP),
                commonUtils.calculateRatioY(this, Constants.S06_TOP_DP),
                commonUtils.calculateRatioX(this, Constants.S06_RIGHT_DP),
                commonUtils.calculateRatioY(this, Constants.S06_BOTTOM_DP),
                ::s06Click)
        )
        Constants.arClickArea.add(
            ClickableArea(
                commonUtils.calculateRatioX(this, Constants.S07_LEFT_DP),
                commonUtils.calculateRatioY(this, Constants.S07_TOP_DP),
                commonUtils.calculateRatioX(this, Constants.S07_RIGHT_DP),
                commonUtils.calculateRatioY(this, Constants.S07_BOTTOM_DP),
                ::s07Click)
        )
        Constants.arClickArea.add(
            ClickableArea(
                commonUtils.calculateRatioX(this, Constants.S08_LEFT_DP),
                commonUtils.calculateRatioY(this, Constants.S08_TOP_DP),
                commonUtils.calculateRatioX(this, Constants.S08_RIGHT_DP),
                commonUtils.calculateRatioY(this, Constants.S08_BOTTOM_DP),
                ::s08Click)
        )
        Constants.arClickArea.add(
            ClickableArea(
                commonUtils.calculateRatioX(this, Constants.S09_LEFT_DP),
                commonUtils.calculateRatioY(this, Constants.S09_TOP_DP),
                commonUtils.calculateRatioX(this, Constants.S09_RIGHT_DP),
                commonUtils.calculateRatioY(this, Constants.S09_BOTTOM_DP),
                ::s09Click)
        )
        Constants.arClickArea.add(
            ClickableArea(
                commonUtils.calculateRatioX(this, Constants.S10_LEFT_DP),
                commonUtils.calculateRatioY(this, Constants.S10_TOP_DP),
                commonUtils.calculateRatioX(this, Constants.S10_RIGHT_DP),
                commonUtils.calculateRatioY(this, Constants.S10_BOTTOM_DP),
                ::s10Click)
        )

        Log.e("eleutheria", "11")
        Constants.arClickArea.add(
            ClickableArea(
                commonUtils.calculateRatioX(this, Constants.S11_LEFT_DP),
                commonUtils.calculateRatioY(this, Constants.S11_TOP_DP),
                commonUtils.calculateRatioX(this, Constants.S11_RIGHT_DP),
                commonUtils.calculateRatioY(this, Constants.S11_BOTTOM_DP),
                ::s11Click)
        )
        Log.e("eleutheria", "12")
        Constants.arClickArea.add(
            ClickableArea(
                commonUtils.calculateRatioX(this, Constants.S12_LEFT_DP),
                commonUtils.calculateRatioY(this, Constants.S12_TOP_DP),
                commonUtils.calculateRatioX(this, Constants.S12_RIGHT_DP),
                commonUtils.calculateRatioY(this, Constants.S12_BOTTOM_DP),
                ::s12Click)
        )
        Log.e("eleutheria", "13")
        Constants.arClickArea.add(
            ClickableArea(
                commonUtils.calculateRatioX(this, Constants.S13_LEFT_DP),
                commonUtils.calculateRatioY(this, Constants.S13_TOP_DP),
                commonUtils.calculateRatioX(this, Constants.S13_RIGHT_DP),
                commonUtils.calculateRatioY(this, Constants.S13_BOTTOM_DP),
                ::s13Click)
        )
        Log.e("eleutheria", "14")
        Constants.arClickArea.add(
            ClickableArea(
                commonUtils.calculateRatioX(this, Constants.S14_LEFT_DP),
                commonUtils.calculateRatioY(this, Constants.S14_TOP_DP),
                commonUtils.calculateRatioX(this, Constants.S14_RIGHT_DP),
                commonUtils.calculateRatioY(this, Constants.S14_BOTTOM_DP),
                ::s14Click)
        )
        Constants.arClickArea.add(
            ClickableArea(
                commonUtils.calculateRatioX(this, Constants.S15_LEFT_DP),
                commonUtils.calculateRatioY(this, Constants.S15_TOP_DP),
                commonUtils.calculateRatioX(this, Constants.S15_RIGHT_DP),
                commonUtils.calculateRatioY(this, Constants.S15_BOTTOM_DP),
                ::s15Click)
        )
        Constants.arClickArea.add(
            ClickableArea(
                commonUtils.calculateRatioX(this, Constants.S16_LEFT_DP),
                commonUtils.calculateRatioY(this, Constants.S16_TOP_DP),
                commonUtils.calculateRatioX(this, Constants.S16_RIGHT_DP),
                commonUtils.calculateRatioY(this, Constants.S16_BOTTOM_DP),

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
            R.id.btStart -> {
//                Thread {
//                    ObNetworkClient.connect(Constants.NETWORK_IP, Constants.NETWORK_PORT, applicationContext)
//                }.start()
                insertTestData()

//                CoroutineScope(Dispatchers.IO).launch {
//                    handleSocketCommunication()
//                }
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

    private suspend fun handleSocketCommunication() {
        withContext(Dispatchers.IO) {
            var socket: Socket? = null
            var reader: BufferedReader? = null
            var writer: OutputStreamWriter? = null

            try {
                val socketAddress: SocketAddress = InetSocketAddress(Constants.NETWORK_IP, Constants.NETWORK_PORT)
                socket = Socket().apply {
                    connect(socketAddress, Constants.NETWORK_TIMEOUT)
                }

                reader = BufferedReader(InputStreamReader(socket.getInputStream()))
                writer = OutputStreamWriter(socket.getOutputStream())

                val messageToSend = "Connect"
                sendMessage(writer, messageToSend)

                while(true) {
                    val receivedData = reader.readLine()
                    if(receivedData != null) {
                        Log.e("eleutheria", "receivedData : $receivedData")

                        processReceivedData(receivedData)
                    } else {
                        Log.e("eleutheria", "Connection closed by server")
                        break
                    }
                }

            } catch (e : Exception) {
                Log.e("eleutheria", "Exception : ${e.message}")
            } finally {
                writer?.close()
                reader?.close()
                socket?.close()
            }
        }
    }

    private fun sendMessage(writer: OutputStreamWriter?, message: String) {
        try {
            writer?.apply {
                write(message)
                flush()
            }
        } catch (e: Exception) {
            Log.e("eleutheria", "Failed to send Message : ${e.message}")
        }
    }

    private fun processReceivedData(data: String) {
        Log.e("eleutheria", "processReceivedData : $data")

        val segments = data.split('!')
        val values = mutableMapOf<String, String>()

        for(segment in segments) {
            val keyValue = segment.split(':')
//            if(keyValue.size == 2) {
//                val key = keyValue[0]
//                val value = keyValue[1]
//                values[key] = value
//            }
            if(keyValue.size == 2) {
                val key = keyValue[0]
                if(key == "G") {
                    val value = keyValue[1]
                    values[key] = value
                } else {
                    val arValue = keyValue[1].split(',')
                    if (arValue.size == 4) {
                        val value = arValue[0]
                        values[key] = value
                    }
                }
            }
        }

        val createddate = commonUtils.getCurrentDateTime()
        val nEmergency = values["G"]?.toIntOrNull()
        val s01 = values["S1"]?.toFloatOrNull()
        val s02 = values["S2"]?.toFloatOrNull()
        val s03 = values["S3"]?.toFloatOrNull()
        val s04 = values["S4"]?.toFloatOrNull()
        val s05 = values["S5"]?.toFloatOrNull()
        val s06 = values["S6"]?.toFloatOrNull()
        val s07 = values["S7"]?.toFloatOrNull()
        val s08 = values["S8"]?.toFloatOrNull()
        val s09 = values["S9"]?.toFloatOrNull()
        val s10 = values["S10"]?.toFloatOrNull()
        val s11 = values["S11"]?.toFloatOrNull()
        val s12 = values["S12"]?.toFloatOrNull()
        val s13 = values["S13"]?.toFloatOrNull()
        val s14 = values["S14"]?.toFloatOrNull()
        val s15 = values["S15"]?.toFloatOrNull()
//        val s16 = values["S16"]?.toFloatOrNull()
        val s16 = 16.124f

        Log.e("eleutheria", "createddate : $createddate, strEmergency : $nEmergency, s01 : $s01, s02 : $s02, s03 : $s03, s04 : $s04, s05 : $s05, s06 : $s06, s07 : $s07, s08 : $s08, s09 : $s09, s10 : $s10")
//
        if(nEmergency == 0) {
            Constants.fSelIndex.clear()
            Constants.fSelIndex.add(s01!!)
            Constants.fSelIndex.add(s02!!)
            Constants.fSelIndex.add(s03!!)
            Constants.fSelIndex.add(s04!!)
            Constants.fSelIndex.add(s05!!)
            Constants.fSelIndex.add(s06!!)
            Constants.fSelIndex.add(s07!!)
            Constants.fSelIndex.add(s08!!)
            Constants.fSelIndex.add(s09!!)
            Constants.fSelIndex.add(s10!!)
            Constants.fSelIndex.add(s11!!)
            Constants.fSelIndex.add(s12!!)
            Constants.fSelIndex.add(s13!!)
            Constants.fSelIndex.add(s14!!)
            Constants.fSelIndex.add(s15!!)
            Constants.fSelIndex.add(s16!!)
        } else {
            Thread {
                leisureDao.insertTodo(
                    LeisureEntity(
                        null,
                        createddate,
                        nEmergency!!,
                        s01!!,
                        s02!!,
                        s03!!,
                        s04!!,
                        s05!!,
                        s06!!,
                        s07!!,
                        s08!!,
                        s09!!,
                        s10!!,
                        s11!!,
                        s12!!,
                        s13!!,
                        s14!!,
                        s15!!,
                        s16!!,
                    )
                )
            }.start()
        }
    }

    private fun showCustomDialog(index : Int) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_custom_degree, null)
        val dialogRoot = dialogView.findViewById<ConstraintLayout>(R.id.clDialogDegree)

        var strDegree = "0.0"
        var strPhysical = "Head"

        when(index) {
            Constants.POINT_AREA_S01 -> {
                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S01 - 1]
                strDegree = dataItem.toString()
                strPhysical = Constants.PHYSICAL_S01
                // Change the background image
                dialogRoot.setBackgroundResource(R.drawable.popup)
            }
            Constants.POINT_AREA_S02 -> {
                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S02 - 1]
                strDegree = dataItem.toString()
                strPhysical = Constants.PHYSICAL_S02
                // Change the background image
                dialogRoot.setBackgroundResource(R.drawable.popup)
            }
            Constants.POINT_AREA_S03 -> {
                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S03 - 1]
                strDegree = dataItem.toString()
                strPhysical = Constants.PHYSICAL_S03
                // Change the background image
                dialogRoot.setBackgroundResource(R.drawable.popup)
            }
            Constants.POINT_AREA_S04 -> {
                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S04 - 1]
                strDegree = dataItem.toString()
                strPhysical = Constants.PHYSICAL_S04
                // Change the background image
                dialogRoot.setBackgroundResource(R.drawable.popup)
            }
            Constants.POINT_AREA_S05 -> {
                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S05 - 1]
                strDegree = dataItem.toString()
                strPhysical = Constants.PHYSICAL_S05
                // Change the background image
                dialogRoot.setBackgroundResource(R.drawable.popup)
            }
            Constants.POINT_AREA_S06 -> {
                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S06 - 1]
                strDegree = dataItem.toString()
                strPhysical = Constants.PHYSICAL_S06
                // Change the background image
                dialogRoot.setBackgroundResource(R.drawable.popup)
            }
            Constants.POINT_AREA_S07 -> {
                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S07 - 1]
                strDegree = dataItem.toString()
                strPhysical = Constants.PHYSICAL_S07
                // Change the background image
                dialogRoot.setBackgroundResource(R.drawable.popup)
            }
            Constants.POINT_AREA_S08 -> {
                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S08 - 1]
                strDegree = dataItem.toString()
                strPhysical = Constants.PHYSICAL_S08
                // Change the background image
                dialogRoot.setBackgroundResource(R.drawable.popup)
            }
            Constants.POINT_AREA_S09 -> {
                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S09 - 1]
                strDegree = dataItem.toString()
                strPhysical = Constants.PHYSICAL_S09
                // Change the background image
                dialogRoot.setBackgroundResource(R.drawable.popup)
            }
            Constants.POINT_AREA_S10 -> {
                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S10 - 1]
                strDegree = dataItem.toString()
                strPhysical = Constants.PHYSICAL_S10
                // Change the background image
                dialogRoot.setBackgroundResource(R.drawable.popup)
            }
            Constants.POINT_AREA_S11 -> {
                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S11 - 1]
                strDegree = dataItem.toString()
                strPhysical = Constants.PHYSICAL_S11
                // Change the background image
                dialogRoot.setBackgroundResource(R.drawable.popup)
            }
            Constants.POINT_AREA_S12 -> {
                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S12 - 1]
                strDegree = dataItem.toString()
                strPhysical = Constants.PHYSICAL_S12
                // Change the background image
                dialogRoot.setBackgroundResource(R.drawable.popup)
            }
            Constants.POINT_AREA_S13 -> {
                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S13 - 1]
                strDegree = dataItem.toString()
                strPhysical = Constants.PHYSICAL_S13
                // Change the background image
                dialogRoot.setBackgroundResource(R.drawable.popup)
            }
            Constants.POINT_AREA_S14 -> {
                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S14 - 1]
                strDegree = dataItem.toString()
                strPhysical = Constants.PHYSICAL_S14
                // Change the background image
                dialogRoot.setBackgroundResource(R.drawable.popup)
            }
            Constants.POINT_AREA_S15 -> {
                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S15 - 1]
                strDegree = dataItem.toString()
                strPhysical = Constants.PHYSICAL_S15
                // Change the background image
                dialogRoot.setBackgroundResource(R.drawable.popup)
            }
            Constants.POINT_AREA_S16 -> {
                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S16 - 1]
                strDegree = dataItem.toString()
                strPhysical = Constants.PHYSICAL_S16
                // Change the background image
                dialogRoot.setBackgroundResource(R.drawable.popup)
            }
        }

        // Create the dialog
        val dialog = Dialog(this)
        dialog.setContentView(dialogView)
        dialog.setCancelable(true) // Set to false if you don't want it to be dismissible

        // Access TextViews if you need to set text dynamically
        val tvNumber = dialogView.findViewById<TextView>(R.id.tvNumber)
        val tvDegree = dialogView.findViewById<TextView>(R.id.tvDegree)

        tvNumber.text = strPhysical
        val strDegreeD = strDegree + getString(R.string.app_common_degree)
        tvDegree.text = strDegreeD

        // Show the dialog
        dialog.show()
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