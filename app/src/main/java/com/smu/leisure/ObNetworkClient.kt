package com.smu.leisure

import android.content.Context
import android.util.Log
import com.smu.leisure.base.CommonUtils
import com.smu.leisure.base.Constants
import com.smu.leisure.base.NetworkCallback
import com.smu.leisure.db.AppDatabase
import com.smu.leisure.db.LeisureDao
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.IOException
import java.io.OutputStreamWriter
import java.net.InetSocketAddress
import java.net.Socket

class ObNetworkClient(private val callback: NetworkCallback) {
    private var socket: Socket? = null
    private var output: BufferedWriter? = null
    private var input: BufferedReader? = null
    private var isConnected = false

    private var commonUtils = CommonUtils()


    lateinit var db : AppDatabase
    lateinit var swingDao: LeisureDao

    fun connect(ipAddress: String, portNumber: Int, context: Context) : Boolean {

        db = AppDatabase.getInstance(context)!!
        swingDao = db.getLeisureDao()

        val timeoutMillis = 5000 // Timeout in milliseconds (5 seconds)

        socket = Socket()
        return try {
            if (!isConnected) {
                val socketAddress = InetSocketAddress(ipAddress, portNumber)
                socket!!.connect(socketAddress, timeoutMillis)
                output = BufferedWriter(OutputStreamWriter(socket!!.getOutputStream()))
                output!!.write(Constants.STR_CONNECT)
                output!!.flush()

                isConnected = true
                callback.onConnectionEstablished()
            }
            true
        } catch (e: IOException) {
            e.printStackTrace()
            // Handle connection error
            false
        }
    }

    fun sendString(data: String) {
        if (isConnected) {
            output!!.write(data)
            output!!.flush()
        }

        while(true) {
            // Receive data from the socket
            val inputStream = socket!!.getInputStream()
            val buffer = ByteArray(1024)

            val bytesRead = inputStream.read(buffer)
            val bufferData = String(buffer, 0, bytesRead)

            Log.e("eleutheria", "bufferData : $bufferData")

            callback.onMessageReceived(bufferData)
/*
            S01:1.11,1.22,1.33;S02:2.11,2.22,2.33;S03:3.11,3.22,3.33;S04:4.11,4.22,4.33;S05:5.11,5.22,5.33;S06:6.11,6.22,6.33;S07:7.11,7.22,7.33;S08:8.11,8.22,8.33;S09:9.11,9.22,9.33;S10:10.11,10.22,10.33;S11:11.11,11.22,11.33;S12:12.11,12.22,12.33;S13:13.11,13.22,13.33;S14:14.11,14.22,14.33!

 */

        }
    }

    fun receiveString() : String? {
        val strResult = input?.readLine()

        if (isConnected) {
            return strResult
        } else {
            return null
        }
    }

    fun disconnect() {
        try {
            input?.close()
            output?.close()
            socket?.close()
            isConnected = false
            callback.onConnectionClosed()
        } catch (e: IOException) {
            e.printStackTrace()
            // Handle disconnection error
        }
    }
}