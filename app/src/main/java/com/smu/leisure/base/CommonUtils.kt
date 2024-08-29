package com.smu.leisure.base

import android.content.Context
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.net.Uri
import android.telephony.SmsManager
import android.util.DisplayMetrics
import android.util.Log
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
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

    fun convertDpToPxX(context: Context, dp: Float): Float {
        val xDpi = context.resources.displayMetrics.xdpi
        val densityX = xDpi / 160.0f
        return dp * densityX + 0.5f
    }

    fun convertDpToPxY(context: Context, dp: Float): Float {
        val yDpi = context.resources.displayMetrics.ydpi
        val densityY = yDpi / 160.0f
        return dp * densityY + 0.5f
    }

    fun convertDpToPx(context: Context, dp : Float) : Float {
        val displayMetrics : DisplayMetrics = context.resources.displayMetrics
        return (dp * displayMetrics.density + 0.5f)
    }

    fun convertPxToDp(context: Context, px : Float) : Float {
        val displayMetrics : DisplayMetrics = context.resources.displayMetrics
        return (px / displayMetrics.density + 0.5f)
    }

    fun calculateRatioX(context: Context, px : Float) : Float {
        val fRatio = px / Constants.ivHumanOriginalWidth
        return Constants.ivHumanWidth * fRatio
    }

    fun calculateRatioY(context: Context, px : Float) : Float {
        val fRatio = px / Constants.ivHumanOriginalHeight
        return Constants.ivHumanHeight * fRatio
    }

    fun calculateScreenRatioX(context: Context, px : Float) : Float {
        val fRatio = px / Constants.ivHumanOriginalWidth
        return (Constants.ivHumanWidth * fRatio) + ((context.resources.displayMetrics.widthPixels / 2) - (Constants.ivHumanWidth / 2) - (Constants.ivEffectCenterWidth / 2))
    }

    fun calculateScreenRatioY(context: Context, px : Float) : Float {
        val fRatio = px / Constants.ivHumanOriginalHeight
        return (Constants.ivHumanHeight * fRatio) + ((context.resources.displayMetrics.heightPixels / 2) - (Constants.ivHumanHeight / 2) - (Constants.llConnectHeight) - (Constants.ivEffectCenterHeight / 2))
    }

    fun setTextViewPosition(textView: TextView, x: Int, y: Int) {
        val layoutParams = textView.layoutParams as ConstraintLayout.LayoutParams
        layoutParams.leftMargin = x
        layoutParams.topMargin = y
        textView.layoutParams = layoutParams
    }

    fun sendSMS() {
        val str119Number = Constants.strEmergencyNumber

        val smsManager = SmsManager.getDefault()

        val locationProvider = LocationProvider(MyApplication.ApplicationContext())

        val latitude = locationProvider.getLocationLatitude()
        val longitude = locationProvider.getLocationLongitude()

        Locale.setDefault(Locale("en", "GB"))
        val newLocale = Locale.getDefault()
        val geocoder = Geocoder(MyApplication.ApplicationContext(), newLocale)

        val addresses: List<Address> = geocoder.getFromLocation(latitude, longitude, 1)!!
        var strAddress = MyApplication.ApplicationContext().getString(R.string.strAccidentAddress)

        if(addresses.isNotEmpty()) {
            strAddress = addresses[0].getAddressLine(0)
        }

        val message = "Emergency!! I need rescue!! track my location!! Location : $strAddress"
        val strFirstString = str119Number.substring(0, 1)

        if(strFirstString == "0") {
            Log.e("eleutheria", "str119Number : $str119Number, message : $message")
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