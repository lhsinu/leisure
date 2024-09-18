package com.smu.leisure

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.smu.leisure.base.ClickableArea
import com.smu.leisure.base.CommonUtils
import com.smu.leisure.base.Constants
import com.smu.leisure.base.CustomDialog
import com.smu.leisure.base.NetworkCallback
import com.smu.leisure.base.SensorData
import com.smu.leisure.databinding.ActivityMainBinding
import com.smu.leisure.db.AppDatabase
import com.smu.leisure.db.LeisureDao
import com.smu.leisure.db.LeisureEntity
import kotlin.random.Random


class MainActivity : AppCompatActivity(), View.OnClickListener, NetworkCallback {
    private lateinit var binding: ActivityMainBinding
    private var commonUtils = CommonUtils()
    private lateinit var networkClient: ObNetworkClient

    lateinit var db : AppDatabase
    lateinit var leisureDao: LeisureDao
    private val drawnViews = mutableSetOf<Int>()
    private lateinit var customDialog : CustomDialog

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(applicationContext)!!
        leisureDao = db.getLeisureDao()

        networkClient = ObNetworkClient(this)

        binding.main
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

            Constants.llConnectWidth = binding.llConnect.width
            Constants.llConnectHeight = binding.llConnect.height

            Constants.ivEffectCenterWidth = commonUtils.calculateRatioX(this, 200.0f).toInt()
            Constants.ivEffectCenterHeight = commonUtils.calculateRatioY(this, 200.0f).toInt()
            Constants.ivEffectSideWidth = commonUtils.calculateRatioX(this, 200.0f).toInt()
            Constants.ivEffectSideHeight = commonUtils.calculateRatioY(this, 200.0f).toInt()

            settingAreas()
        }

        binding.ivHuman.post {
            // Now you can get the width and height
            Constants.ivHumanWidth = binding.ivHuman.width
            Constants.ivHumanHeight = binding.ivHuman.height

            settingAreas()
        }

        resources.displayMetrics.widthPixels

        binding.btConnect.setOnClickListener(this)
        binding.btStart.setOnClickListener(this)
        binding.btData.setOnClickListener(this)
        binding.btSetting.setOnClickListener(this)


        // Temp Test Data
        Constants.fSelIndex.add(1.1)
        Constants.fSelIndex.add(2.2)
        Constants.fSelIndex.add(3.3)
        Constants.fSelIndex.add(4.4)
        Constants.fSelIndex.add(5.5)
        Constants.fSelIndex.add(6.6)
        Constants.fSelIndex.add(7.7)
        Constants.fSelIndex.add(8.8)
        Constants.fSelIndex.add(9.9)
        Constants.fSelIndex.add(10.0)
        Constants.fSelIndex.add(-11.1)
        Constants.fSelIndex.add(12.2)
        Constants.fSelIndex.add(13.3)
        Constants.fSelIndex.add(14.4)

        customDialog = CustomDialog(this, 1)
        Constants.old_Time = System.currentTimeMillis()
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
        Constants.arClickArea.add(
            ClickableArea(
                commonUtils.calculateRatioX(this, Constants.S11_LEFT_DP),
                commonUtils.calculateRatioY(this, Constants.S11_TOP_DP),
                commonUtils.calculateRatioX(this, Constants.S11_RIGHT_DP),
                commonUtils.calculateRatioY(this, Constants.S11_BOTTOM_DP),
                ::s11Click)
        )
        Constants.arClickArea.add(
            ClickableArea(
                commonUtils.calculateRatioX(this, Constants.S12_LEFT_DP),
                commonUtils.calculateRatioY(this, Constants.S12_TOP_DP),
                commonUtils.calculateRatioX(this, Constants.S12_RIGHT_DP),
                commonUtils.calculateRatioY(this, Constants.S12_BOTTOM_DP),
                ::s12Click)
        )
        Constants.arClickArea.add(
            ClickableArea(
                commonUtils.calculateRatioX(this, Constants.S13_LEFT_DP),
                commonUtils.calculateRatioY(this, Constants.S13_TOP_DP),
                commonUtils.calculateRatioX(this, Constants.S13_RIGHT_DP),
                commonUtils.calculateRatioY(this, Constants.S13_BOTTOM_DP),
                ::s13Click)
        )
        Constants.arClickArea.add(
            ClickableArea(
                commonUtils.calculateRatioX(this, Constants.S14_LEFT_DP),
                commonUtils.calculateRatioY(this, Constants.S14_TOP_DP),
                commonUtils.calculateRatioX(this, Constants.S14_RIGHT_DP),
                commonUtils.calculateRatioY(this, Constants.S14_BOTTOM_DP),
                ::s14Click)
        )
//        Constants.arClickArea.add(
//            ClickableArea(
//                commonUtils.calculateRatioX(this, Constants.S15_LEFT_DP),
//                commonUtils.calculateRatioY(this, Constants.S15_TOP_DP),
//                commonUtils.calculateRatioX(this, Constants.S15_RIGHT_DP),
//                commonUtils.calculateRatioY(this, Constants.S15_BOTTOM_DP),
//                ::s15Click)
//        )
//        Constants.arClickArea.add(
//            ClickableArea(
//                commonUtils.calculateRatioX(this, Constants.S16_LEFT_DP),
//                commonUtils.calculateRatioY(this, Constants.S16_TOP_DP),
//                commonUtils.calculateRatioX(this, Constants.S16_RIGHT_DP),
//                commonUtils.calculateRatioY(this, Constants.S16_BOTTOM_DP),
//
//                ::s16Click)
//        )
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
        networkClient.disconnect()
    }

    override fun onClick(p0: View?) {
        when(p0!!.id) {
            R.id.btConnect -> {
//                Toast.makeText(this, "Clicked Connect", Toast.LENGTH_SHORT).show()
                Log.e("eleutheria", "MODULE_ADDRESS_WIFI_IP : ${Constants.MODULE_ADDRESS_WIFI_IP}, MODULE_ADDRESS_WIFI_PORT : ${Constants.MODULE_ADDRESS_WIFI_PORT}")
                val localIP = "joint.local"
                Thread {
                    networkClient.connect(Constants.MODULE_ADDRESS_WIFI_IP, Constants.MODULE_ADDRESS_WIFI_PORT.toInt(), applicationContext)
//                    networkClient.connect(localIP, Constants.MODULE_ADDRESS_WIFI_PORT.toInt(), applicationContext)
                }.start()
//                insertTestData()
            }

            R.id.btStart -> {
//                Toast.makeText(this, "Clicked Start", Toast.LENGTH_SHORT).show()
                Thread {
                    val response = networkClient.sendString(Constants.STR_START)
//                    val response = "S01:1.11,1.22,1.33;S02:2.11,2.22,2.33;S03:3.11,3.22,3.33;S04:4.11,4.22,4.33;S05:5.11,5.22,5.33;S06:6.11,6.22,6.33;S07:7.11,7.22,7.33;S08:8.11,8.22,8.33;S09:9.11,9.22,9.33;S10:10.11,10.22,10.33;S11:11.11,11.22,11.33;S12:12.11,12.22,12.33;S13:13.11,13.22,13.33;S14:14.11,14.22,14.33!"

                }.start()
//                val response = "S01:1.11,1.22,1.33;C02:2.11,2.22,2.33;S03:3.11,3.22,3.33;S04:4.11,4.22,4.33;S05:5.11,5.22,5.33;S06:6.11,6.22,6.33;S07:7.11,7.22,7.33;S08:8.11,8.22,8.33;S09:9.11,9.22,9.33;S10:10.11,10.22,10.33;S11:11.11,11.22,11.33;S12:12.11,12.22,12.33;S13:13.11,13.22,13.33;S14:14.11,14.22,14.33!"
//                val response = "S01:010,145,302;S02:067,183,209;S03:035,129,245;D04:072,091,312;S05:023,132,280;S06:054,101,360;S07:008,075,190;S08:098,126,210;S09:048,117,230;S10:012,091,273;S11:034,154,193;D12:078,115,237;S13:021,092,199;S14:047,134,278!"
//                onMessageReceived(response)
//                insertTestData()

//                drawEffect(binding.main, "01")
//                drawEffect(binding.main, "02")
//                drawEffect(binding.main, "03")
//                drawEffect(binding.main, "04")
//                drawEffect(binding.main, "05")
//                drawEffect(binding.main, "06")
//                drawEffect(binding.main, "07")
//                drawEffect(binding.main, "08")
//                drawEffect(binding.main, "09")
//                drawEffect(binding.main, "10")
//                drawEffect(binding.main, "11")
//                drawEffect(binding.main, "12")
//                drawEffect(binding.main, "13")
//                drawEffect(binding.main, "14")

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

//    private suspend fun handleSocketCommunication() {
//        withContext(Dispatchers.IO) {
//            var socket: Socket? = null
//            var reader: BufferedReader? = null
//            var writer: OutputStreamWriter? = null
//
//            try {
//                val socketAddress: SocketAddress = InetSocketAddress(Constants.MODULE_ADDRESS_WIFI_IP, Constants.MODULE_ADDRESS_WIFI_PORT.toInt())
//                socket = Socket().apply {
//                    connect(socketAddress, Constants.NETWORK_TIMEOUT)
//                }
//
//                reader = BufferedReader(InputStreamReader(socket.getInputStream()))
//                writer = OutputStreamWriter(socket.getOutputStream())
//
//                val messageToSend = "Connect"
//                sendMessage(writer, messageToSend)
//
//                while(true) {
//                    val receivedData = reader.readLine()
//                    if(receivedData != null) {
//                        Log.e("eleutheria", "receivedData : $receivedData")
//
//                        processReceivedData(receivedData)
//                    } else {
//                        Log.e("eleutheria", "Connection closed by server")
//                        break
//                    }
//                }
//
//            } catch (e : Exception) {
//                Log.e("eleutheria", "Exception : ${e.message}")
//            } finally {
//                writer?.close()
//                reader?.close()
//                socket?.close()
//            }
//        }
//    }

//    private fun sendMessage(writer: OutputStreamWriter?, message: String) {
//        try {
//            writer?.apply {
//                write(message)
//                flush()
//            }
//        } catch (e: Exception) {
//            Log.e("eleutheria", "Failed to send Message : ${e.message}")
//        }
//    }

//    private fun processReceivedData(data: String) {
//        Log.e("eleutheria", "processReceivedData : $data")
//        return
//    }

    private fun showCustomDialog(index : Int) {

        customDialog.setCancelable(true)
        customDialog.updateValue(index)
        customDialog.show()

//        val dialogView = layoutInflater.inflate(R.layout.dialog_custom_degree, null)
//        val dialogRoot = dialogView.findViewById<ConstraintLayout>(R.id.clDialogDegree)
//
//        var strDegree = "0.0"
//        var strPhysical = "Head"
//        var bIsMinus = false
//
//        when(index) {
//            Constants.POINT_AREA_S01 -> {
//                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S01 - 1]
//                strDegree = dataItem.toString()
//                strPhysical = Constants.PHYSICAL_S01
//
//                if(dataItem < 0) {
//                    bIsMinus = true
//                }
//                // Change the background image
//                dialogRoot.setBackgroundResource(R.drawable.popup)
//            }
//            Constants.POINT_AREA_S02 -> {
//                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S02 - 1]
//                strDegree = dataItem.toString()
//                strPhysical = Constants.PHYSICAL_S02
//
//                if(dataItem < 0) {
//                    bIsMinus = true
//                }
//                // Change the background image
//                dialogRoot.setBackgroundResource(R.drawable.popup)
//            }
//            Constants.POINT_AREA_S03 -> {
//                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S03 - 1]
//                strDegree = dataItem.toString()
//                strPhysical = Constants.PHYSICAL_S03
//
//                if(dataItem < 0) {
//                    bIsMinus = true
//                }
//                // Change the background image
//                dialogRoot.setBackgroundResource(R.drawable.popup)
//            }
//            Constants.POINT_AREA_S04 -> {
//                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S04 - 1]
//                strDegree = dataItem.toString()
//                strPhysical = Constants.PHYSICAL_S04
//
//                if(dataItem < 0) {
//                    bIsMinus = true
//                }
//                // Change the background image
//                dialogRoot.setBackgroundResource(R.drawable.popup)
//            }
//            Constants.POINT_AREA_S05 -> {
//                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S05 - 1]
//                strDegree = dataItem.toString()
//                strPhysical = Constants.PHYSICAL_S05
//
//                if(dataItem < 0) {
//                    bIsMinus = true
//                }
//                // Change the background image
//                dialogRoot.setBackgroundResource(R.drawable.popup)
//            }
//            Constants.POINT_AREA_S06 -> {
//                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S06 - 1]
//                strDegree = dataItem.toString()
//                strPhysical = Constants.PHYSICAL_S06
//
//                if(dataItem < 0) {
//                    bIsMinus = true
//                }
//                // Change the background image
//                dialogRoot.setBackgroundResource(R.drawable.popup)
//            }
//            Constants.POINT_AREA_S07 -> {
//                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S07 - 1]
//                strDegree = dataItem.toString()
//                strPhysical = Constants.PHYSICAL_S07
//
//                if(dataItem < 0) {
//                    bIsMinus = true
//                }
//                // Change the background image
//                dialogRoot.setBackgroundResource(R.drawable.popup)
//            }
//            Constants.POINT_AREA_S08 -> {
//                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S08 - 1]
//                strDegree = dataItem.toString()
//                strPhysical = Constants.PHYSICAL_S08
//
//                if(dataItem < 0) {
//                    bIsMinus = true
//                }
//                // Change the background image
//                dialogRoot.setBackgroundResource(R.drawable.popup)
//            }
//            Constants.POINT_AREA_S09 -> {
//                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S09 - 1]
//                strDegree = dataItem.toString()
//                strPhysical = Constants.PHYSICAL_S09
//
//                if(dataItem < 0) {
//                    bIsMinus = true
//                }
//                // Change the background image
//                dialogRoot.setBackgroundResource(R.drawable.popup)
//            }
//            Constants.POINT_AREA_S10 -> {
//                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S10 - 1]
//                strDegree = dataItem.toString()
//                strPhysical = Constants.PHYSICAL_S10
//
//                if(dataItem < 0) {
//                    bIsMinus = true
//                }
//                // Change the background image
//                dialogRoot.setBackgroundResource(R.drawable.popup)
//            }
//            Constants.POINT_AREA_S11 -> {
//                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S11 - 1]
//                strDegree = dataItem.toString()
//                strPhysical = Constants.PHYSICAL_S11
//
//                if(dataItem < 0) {
//                    bIsMinus = true
//                }
//                // Change the background image
//                dialogRoot.setBackgroundResource(R.drawable.popup)
//            }
//            Constants.POINT_AREA_S12 -> {
//                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S12 - 1]
//                strDegree = dataItem.toString()
//                strPhysical = Constants.PHYSICAL_S12
//
//                if(dataItem < 0) {
//                    bIsMinus = true
//                }
//                // Change the background image
//                dialogRoot.setBackgroundResource(R.drawable.popup)
//            }
//            Constants.POINT_AREA_S13 -> {
//                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S13 - 1]
//                strDegree = dataItem.toString()
//                strPhysical = Constants.PHYSICAL_S13
//
//                if(dataItem < 0) {
//                    bIsMinus = true
//                }
//                // Change the background image
//                dialogRoot.setBackgroundResource(R.drawable.popup)
//            }
//            Constants.POINT_AREA_S14 -> {
//                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S14 - 1]
//                strDegree = dataItem.toString()
//                strPhysical = Constants.PHYSICAL_S14
//
//                if(dataItem < 0) {
//                    bIsMinus = true
//                }
//                // Change the background image
//                dialogRoot.setBackgroundResource(R.drawable.popup)
//            }


//            Constants.POINT_AREA_S15 -> {
//                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S15 - 1]
//                strDegree = dataItem.toString()
//                strPhysical = Constants.PHYSICAL_S15
//                // Change the background image
//                dialogRoot.setBackgroundResource(R.drawable.popup)
//            }
//            Constants.POINT_AREA_S16 -> {
//                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S16 - 1]
//                strDegree = dataItem.toString()
//                strPhysical = Constants.PHYSICAL_S16
//                // Change the background image
//                dialogRoot.setBackgroundResource(R.drawable.popup)
//            }
//        }


        // Create the dialog
//        val dialog = Dialog(this)
//        dialog.setContentView(dialogView)
//        dialog.setCancelable(true) // Set to false if you don't want it to be dismissible
//
//        // Access TextViews if you need to set text dynamically
//        val tvNumber = dialogView.findViewById<TextView>(R.id.tvNumber)
//        val tvDegree = dialogView.findViewById<TextView>(R.id.tvDegree)
//
//        if(bIsMinus) {
//            tvDegree.setTextColor(getColor(R.color.red))
//        } else {
//            tvDegree.setTextColor(getColor(R.color.black))
//        }
//
//        tvNumber.text = strPhysical
//        val strDegreeD = strDegree + getString(R.string.app_common_degree)
//        tvDegree.text = strDegreeD
//
//        // Show the dialog
//        dialog.show()
    }

//    private fun connectClient() {
//        Thread {
//            val timeoutMillis = 5000
//
//            val socket = Socket()
//
//            try {
//                val socketAddress = InetSocketAddress(Constants.MODULE_ADDRESS_WIFI_IP, Constants.MODULE_ADDRESS_WIFI_PORT.toInt())
//                socket.connect(socketAddress, timeoutMillis)
//
//                val writer = BufferedWriter(OutputStreamWriter(socket.getOutputStream()))
//                writer.write("app")
//                writer.flush()
//
//                while(true) {
//                    // Receive data from the socket
//                    val inputStream = socket.getInputStream()
//                    val buffer = ByteArray(1024)
//
//                    val bytesRead = inputStream.read(buffer)
//                    val byteData = if (bytesRead != buffer.size) buffer.copyOfRange(0, bytesRead) else buffer
//                    val data = String(buffer, 0, bytesRead)
//                }
//
//            } catch (e: Exception) {
//                println("Error during connect or transmission: ${e.message}")
//            } finally {
//                socket.close()
//            }
//        }.start()
//    }

    private fun s01Click() {
        showCustomDialog(Constants.POINT_AREA_S01)
        Constants.nSelectionIndex = Constants.POINT_AREA_S01
    }

    private fun s02Click() {
        showCustomDialog(Constants.POINT_AREA_S02)
        Constants.nSelectionIndex = Constants.POINT_AREA_S02
    }

    private fun s03Click() {
        showCustomDialog(Constants.POINT_AREA_S03)
        Constants.nSelectionIndex = Constants.POINT_AREA_S03
    }

    private fun s04Click() {
        showCustomDialog(Constants.POINT_AREA_S04)
        Constants.nSelectionIndex = Constants.POINT_AREA_S04
    }

    private fun s05Click() {
        showCustomDialog(Constants.POINT_AREA_S05)
        Constants.nSelectionIndex = Constants.POINT_AREA_S05
    }

    private fun s06Click() {
        showCustomDialog(Constants.POINT_AREA_S06)
        Constants.nSelectionIndex = Constants.POINT_AREA_S06
    }

    private fun s07Click() {
        showCustomDialog(Constants.POINT_AREA_S07)
        Constants.nSelectionIndex = Constants.POINT_AREA_S07
    }

    private fun s08Click() {
        showCustomDialog(Constants.POINT_AREA_S08)
        Constants.nSelectionIndex = Constants.POINT_AREA_S08
    }

    private fun s09Click() {
        showCustomDialog(Constants.POINT_AREA_S09)
        Constants.nSelectionIndex = Constants.POINT_AREA_S09
    }

    private fun s10Click() {
        showCustomDialog(Constants.POINT_AREA_S10)
        Constants.nSelectionIndex = Constants.POINT_AREA_S10
    }

    private fun s11Click() {
        showCustomDialog(Constants.POINT_AREA_S11)
        Constants.nSelectionIndex = Constants.POINT_AREA_S11
    }

    private fun s12Click() {
        showCustomDialog(Constants.POINT_AREA_S12)
        Constants.nSelectionIndex = Constants.POINT_AREA_S12
    }

    private fun s13Click() {
        showCustomDialog(Constants.POINT_AREA_S13)
        Constants.nSelectionIndex = Constants.POINT_AREA_S13
    }

    private fun s14Click() {
        showCustomDialog(Constants.POINT_AREA_S14)
        Constants.nSelectionIndex = Constants.POINT_AREA_S14
    }
//
//    private fun s15Click() {
//        showCustomDialog(Constants.POINT_AREA_S15)
//    }
//
//    private fun s16Click() {
//        showCustomDialog(Constants.POINT_AREA_S16)
//    }

    fun drawEffect(rootLayout: ConstraintLayout, number: String) {
        var imageResId: Int = 0
        var x: Int = 0
        var y: Int = 0

//        Constants.nListIndex++
//        val strNumber = Constants.fNumberIndex[Constants.nListIndex]


        when(number) {
            "01" -> {
                imageResId = R.drawable.effect_center
                x = commonUtils.calculateScreenRatioX(this, Constants.SENSOR_X_POINT[0]).toInt()
                y = commonUtils.calculateScreenRatioY(this, Constants.SENSOR_Y_POINT[0]).toInt()
            }

            "02" -> {
                imageResId = R.drawable.effect_center
                x = commonUtils.calculateScreenRatioX(this, Constants.SENSOR_X_POINT[1]).toInt()
                y = commonUtils.calculateScreenRatioY(this, Constants.SENSOR_Y_POINT[1]).toInt()
            }

            "03" -> {
                imageResId = R.drawable.effect_right
                x = commonUtils.calculateScreenRatioX(this, Constants.SENSOR_X_POINT[2]).toInt()
                y = commonUtils.calculateScreenRatioY(this, Constants.SENSOR_Y_POINT[2]).toInt()
            }

            "04" -> {
                imageResId = R.drawable.effect_left
                x = commonUtils.calculateScreenRatioX(this, Constants.SENSOR_X_POINT[3]).toInt()
                y = commonUtils.calculateScreenRatioY(this, Constants.SENSOR_Y_POINT[3]).toInt()
            }

            "05" -> {
                imageResId = R.drawable.effect_right
                x = commonUtils.calculateScreenRatioX(this, Constants.SENSOR_X_POINT[4]).toInt()
                y = commonUtils.calculateScreenRatioY(this, Constants.SENSOR_Y_POINT[4]).toInt()
            }

            "06" -> {
                imageResId = R.drawable.effect_right
                x = commonUtils.calculateScreenRatioX(this, Constants.SENSOR_X_POINT[5]).toInt()
                y = commonUtils.calculateScreenRatioY(this, Constants.SENSOR_Y_POINT[5]).toInt()
            }

            "07" -> {
                imageResId = R.drawable.effect_right
                x = commonUtils.calculateScreenRatioX(this, Constants.SENSOR_X_POINT[6]).toInt()
                y = commonUtils.calculateScreenRatioY(this, Constants.SENSOR_Y_POINT[6]).toInt()
            }

            "08" -> {
                imageResId = R.drawable.effect_left
                x = commonUtils.calculateScreenRatioX(this, Constants.SENSOR_X_POINT[7]).toInt()
                y = commonUtils.calculateScreenRatioY(this, Constants.SENSOR_Y_POINT[7]).toInt()
            }

            "09" -> {
                imageResId = R.drawable.effect_left
                x = commonUtils.calculateScreenRatioX(this, Constants.SENSOR_X_POINT[8]).toInt()
                y = commonUtils.calculateScreenRatioY(this, Constants.SENSOR_Y_POINT[8]).toInt()
            }

            "10" -> {
                imageResId = R.drawable.effect_left
                x = commonUtils.calculateScreenRatioX(this, Constants.SENSOR_X_POINT[9]).toInt()
                y = commonUtils.calculateScreenRatioY(this, Constants.SENSOR_Y_POINT[9]).toInt()
            }

            "11" -> {
                imageResId = R.drawable.effect_right
                x = commonUtils.calculateScreenRatioX(this, Constants.SENSOR_X_POINT[10]).toInt()
                y = commonUtils.calculateScreenRatioY(this, Constants.SENSOR_Y_POINT[10]).toInt()
            }

            "12" -> {
                imageResId = R.drawable.effect_right
                x = commonUtils.calculateScreenRatioX(this, Constants.SENSOR_X_POINT[11]).toInt()
                y = commonUtils.calculateScreenRatioY(this, Constants.SENSOR_Y_POINT[11]).toInt()
            }

            "13" -> {
                imageResId = R.drawable.effect_left
                x = commonUtils.calculateScreenRatioX(this, Constants.SENSOR_X_POINT[12]).toInt()
                y = commonUtils.calculateScreenRatioY(this, Constants.SENSOR_Y_POINT[12]).toInt()
            }

            "14" -> {
                imageResId = R.drawable.effect_left
                x = commonUtils.calculateScreenRatioX(this, Constants.SENSOR_X_POINT[13]).toInt()
                y = commonUtils.calculateScreenRatioY(this, Constants.SENSOR_Y_POINT[13]).toInt()
            }


        }
        // Create an ImageView dynamically
        val dynamicImageView = ImageView(this).apply {
            // Set the image resource
            setImageResource(imageResId)
            // Set layout parameters with specified size
            layoutParams = ConstraintLayout.LayoutParams(
                250, 250,
//                ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                // Set margins (position on screen)
                startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                topToTop = ConstraintLayout.LayoutParams.PARENT_ID
                leftMargin = x
                topMargin = y
            }
        }

//        for(dView in drawnViews) {
//            Log.e("eleutheria", "count : ${drawnViews.count()}, dView : $dView")
//        }
        if(!drawnViews.contains(number.toInt())) {
//            Log.e("eleutheria", "number : $number.toInt()")
            drawnViews.add(number.toInt())
            rootLayout.addView(dynamicImageView)
        }

        // Use a Handler to remove the ImageView after 3 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            // Remove the ImageView from the layout
            rootLayout.removeView(dynamicImageView)
            drawnViews.remove(number.toInt())
//            Log.e("eleutheria", "delete count : ${drawnViews.count()}, number : ${number.toInt()}")
        }, Constants.MILLISECOND_DRAW_EFFECT)
    }

    fun insertTestData() {

        val createddate = commonUtils.getCurrentDateTime()
        val s01 = randomSensorData()
        val s02 = randomSensorData()
        val s03 = randomSensorData()
        val s04 = randomSensorData()
        val s05 = randomSensorData()
        val s06 = randomSensorData()
        val s07 = randomSensorData()
        val s08 = randomSensorData()
        val s09 = randomSensorData()
        val s10 = randomSensorData()
        val s11 = randomSensorData()
        val s12 = randomSensorData()
        val s13 = randomSensorData()
        val s14 = randomSensorData()

        val sensors = listOf(
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
        )
        val strEmergency = checkEmergency(sensors)

        Log.e("eleutheria", "check strEmergency : $strEmergency")
        Thread {
            leisureDao.insertTodo(
                LeisureEntity(
                    null,
                    createddate,
                    strEmergency,
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
                )
            )
        }.start()
    }

    private fun randomSensorData(): SensorData {
        val options = listOf("S", "D", "C")
        val strEmergency = options.random()

        val returnData = SensorData(strEmergency, Random.nextDouble() * (10.0 - 0.1) + 0.1, Random.nextDouble() * (10.0 - 0.1) + 0.1, Random.nextDouble() * (10.0 - 0.1) + 0.1)

//        Log.e("eleutheria", "strEmergency : $strEmergency")
        return  returnData
    }

    private fun checkEmergency(sensors: List<SensorData>): String {
        sensors.any {
            when (it.strState) {
                "C" -> {
                    return "C"
                }
                "D" -> {
                    return "C"
                }
                else -> {
                    return "S"
                }
            }
        }
        return "S"
    }

    override fun onMessageReceived(message: String) {
        if(!message.contains("!")) {
            Log.e("eleutheria", "message return")

            return
        }
        var arPart = ArrayList<String>()
        val strData = message.substringBefore("!")

        val sensorsData = strData.split(';')
        val regex = Regex("([SDC])(\\d{2})")
//        val values = mutableMapOf<String, String>()

        var strEmergency = "S"
        var s01 = SensorData("S", 0.00, 0.00, 0.00)
        var s02 = SensorData("S", 0.00, 0.00, 0.00)
        var s03 = SensorData("S", 0.00, 0.00, 0.00)
        var s04 = SensorData("S", 0.00, 0.00, 0.00)
        var s05 = SensorData("S", 0.00, 0.00, 0.00)
        var s06 = SensorData("S", 0.00, 0.00, 0.00)
        var s07 = SensorData("S", 0.00, 0.00, 0.00)
        var s08 = SensorData("S", 0.00, 0.00, 0.00)
        var s09 = SensorData("S", 0.00, 0.00, 0.00)
        var s10 = SensorData("S", 0.00, 0.00, 0.00)
        var s11 = SensorData("S", 0.00, 0.00, 0.00)
        var s12 = SensorData("S", 0.00, 0.00, 0.00)
        var s13 = SensorData("S", 0.00, 0.00, 0.00)
        var s14 = SensorData("S", 0.00, 0.00, 0.00)

        for (sensorData in sensorsData) {
            // Split by colon to separate the sensor ID from the values
//            Log.e("eleutheria", "sensorData : $sensorData")
            val parts = sensorData.split(":")

            if(parts.size < 2) return

//            Log.e("eleutheria", "sensorId : ${parts[0]}, sensorValues : ${parts[1]}")

            val sensorId = parts[0]
            val sensorValues = parts[1].split(",").map { it.toDouble() }  // Convert the values to a list of Doubles

            val matchResult = regex.find(sensorId)
            if (matchResult != null) {
                val prefix = matchResult.groupValues[1]
                val number = matchResult.groupValues[2]

//                Log.e("eleutheria", "prefix : $prefix, number : $number")

                strEmergency = when (prefix) {
                    "C" -> "C"
                    "D" -> if (strEmergency != "C") "D" else strEmergency
                    else -> strEmergency
                }

                if(prefix == "D") {
                    arPart.add(number)
                }

                when (number) {
                    "01" -> s01 =
                        SensorData(prefix, sensorValues[0] - 180, sensorValues[1] - 180, sensorValues[2] - 180)

                    "02" -> s02 =
                        SensorData(prefix, sensorValues[0] - 180, sensorValues[1] - 180, sensorValues[2] - 180)

                    "03" -> s03 =
                        SensorData(prefix, sensorValues[0] - 180, sensorValues[1] - 180, sensorValues[2] - 180)

                    "04" -> s04 =
                        SensorData(prefix, sensorValues[0] - 180, sensorValues[1] - 180, sensorValues[2] - 180)

                    "05" -> s05 =
                        SensorData(prefix, sensorValues[0] - 180, sensorValues[1] - 180, sensorValues[2] - 180)

                    "06" -> s06 =
                        SensorData(prefix, sensorValues[0] - 180, sensorValues[1] - 180, sensorValues[2] - 180)

                    "07" -> s07 =
                        SensorData(prefix, sensorValues[0] - 180, sensorValues[1] - 180, sensorValues[2] - 180)

                    "08" -> s08 =
                        SensorData(prefix, sensorValues[0] - 180, sensorValues[1] - 180, sensorValues[2] - 180)

                    "09" -> s09 =
                        SensorData(prefix, sensorValues[0] - 180, sensorValues[1] - 180, sensorValues[2] - 180)

                    "10" -> s10 =
                        SensorData(prefix, sensorValues[0] - 180, sensorValues[1] - 180, sensorValues[2] - 180)

                    "11" -> s11 =
                        SensorData(prefix, sensorValues[0] - 180, sensorValues[1] - 180, sensorValues[2] - 180)

                    "12" -> s12 =
                        SensorData(prefix, sensorValues[0] - 180, sensorValues[1] - 180, sensorValues[2] - 180)

                    "13" -> s13 =
                        SensorData(prefix, sensorValues[0] - 180, sensorValues[1] - 180, sensorValues[2] - 180)

                    "14" -> s14 =
                        SensorData(prefix, sensorValues[0] - 180, sensorValues[1] - 180, sensorValues[2] - 180)
                }
                if(prefix != "S") {
                    Log.e("eleutheria", "strEmergency: $strEmergency")
                    runOnUiThread {
                        drawEffect(
                            binding.main,
                            number
                        )
                    }
                }
            }
        }
        val createddate = commonUtils.getCurrentDateTime()

        Constants.fSelIndex.clear()
        Constants.fSelIndex.add(s01.dYaw)
        Constants.fSelIndex.add(s02.dYaw)
        Constants.fSelIndex.add(s03.dYaw)
        Constants.fSelIndex.add(s04.dYaw)
        Constants.fSelIndex.add(s05.dYaw)
        Constants.fSelIndex.add(s06.dYaw)
        Constants.fSelIndex.add(s07.dYaw)
        Constants.fSelIndex.add(s08.dYaw)
        Constants.fSelIndex.add(s09.dYaw)
        Constants.fSelIndex.add(s10.dYaw)
        Constants.fSelIndex.add(s11.dYaw)
        Constants.fSelIndex.add(s12.dYaw)
        Constants.fSelIndex.add(s13.dYaw)
        Constants.fSelIndex.add(s14.dYaw)

        Constants.new_Time = System.currentTimeMillis()

        if(Constants.new_Time - Constants.old_Time > Constants.MODULE_ELAPSED_TIME) {
            customDialog.updateValue(Constants.nSelectionIndex)
            Constants.old_Time = Constants.new_Time
        }

        Log.e("eleutheria", "strEmergency: $strEmergency")

        if(strEmergency != "S") {
            Log.e("eleutheria", "not strEmergency: $strEmergency")
            Thread {
                leisureDao.insertTodo(
                    LeisureEntity(
                        null,
                        createddate,
                        strEmergency,
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
                    )
                )
            }.start()
        }

        if(!Constants.bEmergency) {
            when (strEmergency) {
                "D", "C" -> {
                    Constants.bEmergency = true

                    commonUtils.sendCall()
                    if(arPart.isEmpty()) {
                        commonUtils.sendSMS()
                    } else {
                        commonUtils.sendSMS(arPart)
                    }
//                    runOnUiThread {
//                        Toast.makeText(this, "Send Call, SMS", Toast.LENGTH_SHORT).show()
//                    }

//                    Handler(Looper.getMainLooper()).postDelayed({
//                        Constants.bEmergency = false
//                    }, Constants.MILLISECOND_DRAW_EFFECT)
                }
//                else -> Log.e("eleutheria", "Unknown sensor type: $strEmergency")
            }
        }
    }

    override fun onConnectionEstablished() {
        Log.e("eleutheria", "onConnectionEstablished")
        runOnUiThread {
            Toast.makeText(this, "Connect Success", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onConnectionClosed() {
        Log.e("eleutheria", "onConnectionClosed")
        runOnUiThread {
            Toast.makeText(this, "Disconnect", Toast.LENGTH_SHORT).show()
        }
    }
}