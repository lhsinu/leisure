package com.smu.leisure

import android.app.Dialog
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.gson.Gson
import com.google.gson.JsonParser
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
import kotlin.random.Random

class MainActivity : AppCompatActivity(), View.OnClickListener{
    private lateinit var binding: ActivityMainBinding
    private var commonUtils = CommonUtils()

    lateinit var db : AppDatabase
    lateinit var leisureDao: LeisureDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(applicationContext)!!
        leisureDao = db.getLeisureDao()

        binding.ivHuman.setOnTouchListener { view, motionEvent ->
            if(motionEvent.action == MotionEvent.ACTION_DOWN) {
                val touchX = motionEvent.x
                val touchY = motionEvent.y

                val chestArea = Rect(420, 310, 720, 680) // Example coordinates
                val handArea = Rect(700, 910, 830, 1060) // Example coordinates
                val wristArea = Rect(750, 620, 850, 850) // Example coordinates
                val stomachArea = Rect(420, 690, 720, 900) // Example coordinates
                val headArea = Rect(490, 50, 680, 250) // Example coordinates

                if (chestArea.contains(touchX.toInt(), touchY.toInt())) {
                    showCustomDialog(Constants.POINT_AREA_S01)
                } else if (handArea.contains(touchX.toInt(), touchY.toInt())) {
                    showCustomDialog(Constants.POINT_AREA_S02)
                } else if (wristArea.contains(touchX.toInt(), touchY.toInt())) {
                    showCustomDialog(Constants.POINT_AREA_S03)
                } else if (stomachArea.contains(touchX.toInt(), touchY.toInt())) {
                    showCustomDialog(Constants.POINT_AREA_S04)
                } else if (headArea.contains(touchX.toInt(), touchY.toInt())) {
                    showCustomDialog(Constants.POINT_AREA_S05)
                }
            }
            true
        }

        binding.btConnect.setOnClickListener(this)
        binding.btData.setOnClickListener(this)

        Constants.fSelIndex.add(12.3f)
        Constants.fSelIndex.add(4.7f)
        Constants.fSelIndex.add(6.3f)
        Constants.fSelIndex.add(11.8f)
        Constants.fSelIndex.add(9.4f)
    }
    override fun onDestroy() {
        super.onDestroy()
        ObNetworkClient.disconnect()
    }

    override fun onClick(p0: View?) {
        when(p0!!.id) {
            R.id.btConnect -> {
                Thread {
                    ObNetworkClient.connect(Constants.NETWORK_IP, Constants.NETWORK_PORT, applicationContext)
                }.start()
            }
            R.id.btData -> {
                val intent = Intent(this@MainActivity, DataActivity::class.java)
                startActivity(intent)

//                insertTestData()
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
                dialogRoot.setBackgroundResource(R.drawable.popup_1)
            }
            Constants.POINT_AREA_S02 -> {
                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S02 - 1]
                strDegree = dataItem.toString()
                // Change the background image
                dialogRoot.setBackgroundResource(R.drawable.popup_2)
            }
            Constants.POINT_AREA_S03 -> {
                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S03 - 1]
                strDegree = dataItem.toString()
                // Change the background image
                dialogRoot.setBackgroundResource(R.drawable.popup_3)
            }
            Constants.POINT_AREA_S04 -> {
                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S04 - 1]
                strDegree = dataItem.toString()
                // Change the background image
                dialogRoot.setBackgroundResource(R.drawable.popup_4)
            }
            Constants.POINT_AREA_S05 -> {
                val dataItem = Constants.fSelIndex[Constants.POINT_AREA_S05 - 1]
                strDegree = dataItem.toString()
                // Change the background image
                dialogRoot.setBackgroundResource(R.drawable.popup_5)
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
        Constants.emergency = '1'
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

    fun insertTestData() {

        val createddate = commonUtils.getCurrentDateTime()
        val strEmergency = Random.nextInt(1, 3).toChar()
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
                    s15,
                    s16,
                )
            )
        }.start()
    }
}