package com.smu.leisure.base

import android.content.Context
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.net.Uri
import android.telephony.SmsManager
import android.util.DisplayMetrics
import android.util.Log
import androidx.core.content.ContextCompat
import com.smu.leisure.R
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CommonUtils {
    fun getFileName(swingType : String) : String {
        val strCurrentTime : String = getCurrentDateTime()

        val strFileName = "$swingType$strCurrentTime"

        return strFileName
    }

    fun getCurrentDateTime(): String {
        val dateFormat = SimpleDateFormat("yy/MM/dd HH:mm:ss", Locale.getDefault())
        return dateFormat.format(Date())
    }

    fun writeTextToFile(strMessage : String, strFileName : String) {
        val filePath = MyApplication.ApplicationContext().filesDir.path + "/$strFileName.txt"
        val file = File(filePath)
        val fileWriter = FileWriter(file, true)
        val bufferedWriter = BufferedWriter(fileWriter)

        bufferedWriter.append(strMessage)
        bufferedWriter.newLine()
        bufferedWriter.close()
    }

    fun convertDpToPx(context: Context, dp : Float) : Float {
        val displayMetrics : DisplayMetrics = context.resources.displayMetrics
        return (dp * displayMetrics.density + 0.5f)
    }

    fun convertPxToDp(context: Context, px : Float) : Float {
        val displayMetrics : DisplayMetrics = context.resources.displayMetrics
        return (px / displayMetrics.density + 0.5f)
    }

    fun sendSMS() {
        val str119Number = Constants.strEmergencyNumber

        val smsManager = SmsManager.getDefault()

        val locationProvider = LocationProvider(MyApplication.ApplicationContext())

        val latitude = locationProvider.getLocationLatitude()
        val longitude = locationProvider.getLocationLongitude()

        Locale.setDefault(Locale("en", "GB"))
        val new_locale = Locale.getDefault()
        val geocoder = Geocoder(MyApplication.ApplicationContext(), new_locale)

        val addresses: List<Address> = geocoder.getFromLocation(latitude, longitude, 1)!!
        var strAddress = MyApplication.ApplicationContext().getString(R.string.strAccidentAddress)

        if(addresses.size > 0) {
            strAddress = addresses[0].getAddressLine(0)
        }

        val message = "Emergency!! I need rescue!! track my location!! Location : $strAddress"
        val strFirstString = str119Number.substring(0, 1)

        if(strFirstString.equals("0")) {
            Log.e("eleutheria", "str119Number : $str119Number")
            smsManager.sendTextMessage(str119Number, null, message, null, null)
        }
    }

    fun sendCall() {
        var strPhoneNumber = Constants.strEmergencyNumber

        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$strPhoneNumber"))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        ContextCompat.startActivity(MyApplication.ApplicationContext(), intent, null)
    }

}