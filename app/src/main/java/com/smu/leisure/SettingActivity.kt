package com.smu.leisure

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.smu.leisure.base.Constants
import com.smu.leisure.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {
    private  lateinit var binding : ActivitySettingBinding
    private var settings: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settings = getSharedPreferences(Constants.SHARED_PREF_SEUPDATA, Context.MODE_PRIVATE)

        binding.tv119.text = settings!!.getString(Constants.PREF_EMERGENCY_CALL_NUMBER, Constants.strEmergencyNumber)

        Log.e("eleutheria", "Setting Next str119Number : ${Constants.strEmergencyNumber}, MODULE_ADDRESS_WIFI_IP : ${Constants.MODULE_ADDRESS_WIFI_IP}, MODULE_ADDRESS_WIFI_PORT : ${Constants.MODULE_ADDRESS_WIFI_PORT}")

        val strWifiData : String? = settings!!.getString(Constants.PREF_WIFI_DEVICE, Constants.MODULE_ADDRESS_WIFI_IP)
        val strWifiPortData : String? = settings!!.getString(Constants.PREF_WIFIPORT_DEVICE, Constants.MODULE_ADDRESS_WIFI_PORT)
        val strTimeData : String? = settings!!.getString(Constants.PREF_ELAPSED_TIME, Constants.MODULE_ELAPSED_TIME.toString())

        binding.btContact.setOnClickListener {
            val contactIntent = Intent(Intent.ACTION_PICK)
            contactIntent.data = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            startActivityForResult(contactIntent, 0)
        }

        binding.btSetting119.setOnClickListener {
            binding.tv119.text = getString(R.string.strNumber119)
            Constants.strEmergencyNumber = getString(R.string.strNumber119)
        }

        binding.btIPOK.setOnClickListener {
            val strWifiAddress = binding.etIP1.text.toString() + "." + binding.etIP2.text.toString() + "." + binding.etIP3.text.toString() + "." + binding.etIP4.text.toString()

            Constants.MODULE_ADDRESS_WIFI_IP = strWifiAddress

            Log.e("eleutheria", "strWifiAddress : $strWifiAddress")
            val editor = settings!!.edit()
            editor.putString(Constants.PREF_WIFI_DEVICE, strWifiAddress)
            editor.apply()

            val strPortAddress = binding.etPort.text.toString()

            Constants.MODULE_ADDRESS_WIFI_PORT = strPortAddress

            Log.e("eleutheria", "strPortAddress : $strPortAddress")
            val ipEditor = settings!!.edit()
            ipEditor.putString(Constants.PREF_WIFIPORT_DEVICE , strPortAddress)
            ipEditor.apply()

            Toast.makeText(this, "PortAddress : $strPortAddress", Toast.LENGTH_SHORT).show()
        }

        binding.btElapsedTime.setOnClickListener {
            val strElapsedTime = binding.etElapsedTime.text.toString()

            Constants.MODULE_ELAPSED_TIME = strElapsedTime.toLong()

            Log.e("eleutheria", "strElapsedTime : $strElapsedTime")
            val editor = settings!!.edit()
            editor.putString(Constants.PREF_ELAPSED_TIME, strElapsedTime)
            editor.apply()

            Toast.makeText(this, "ElapsedTime : $strElapsedTime", Toast.LENGTH_SHORT).show()
        }

        binding.btReset.setOnClickListener {

            val strWifiAddress = Constants.default_wifi_ip

            Log.e("eleutheria", "strWifiAddress : $strWifiAddress")
            val editorWifi = settings!!.edit()
            editorWifi.putString(Constants.PREF_WIFI_DEVICE, strWifiAddress)
            editorWifi.apply()

            val strWifiPortAddress = Constants.default_wifi_port

            Log.e("eleutheria", "strWifiPortAddress : $strWifiPortAddress")
            val editorPort = settings!!.edit()
            editorPort.putString(Constants.PREF_WIFIPORT_DEVICE, strWifiPortAddress)
            editorPort.apply()

            val strElapsedTime = Constants.default_elapsedTime.toString()

            Log.e("eleutheria", "strElapsedTime : $strElapsedTime")
            val editorTime = settings!!.edit()
            editorTime.putString(Constants.PREF_ELAPSED_TIME, strElapsedTime)
            editorTime.apply()

            if (strWifiAddress != null)
            {
                val arWifiData = strWifiAddress.split(".")

                if(arWifiData.size > 3) {
                    binding.etIP1.setText(arWifiData[0])
                    binding.etIP2.setText(arWifiData[1])
                    binding.etIP3.setText(arWifiData[2])
                    binding.etIP4.setText(arWifiData[3])
                }
            }

            if (strWifiPortAddress != null)
            {
                binding.etPort.setText(strWifiPortAddress)
            }

            if (strElapsedTime != null)
            {
                binding.etElapsedTime.setText(strElapsedTime)
            }
        }

        if (strWifiData != null)
        {
            val arWifiData = strWifiData.split(".")

            if(arWifiData.size > 3) {
                binding.etIP1.setText(arWifiData[0])
                binding.etIP2.setText(arWifiData[1])
                binding.etIP3.setText(arWifiData[2])
                binding.etIP4.setText(arWifiData[3])
            }
        }

        if (strWifiPortData != null)
        {
            binding.etPort.setText(strWifiPortData)
        }

        if (strTimeData != null)
        {
            binding.etElapsedTime.setText(strTimeData)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK) {

            val cursor = contentResolver.query(
                data!!.data!!,
                arrayOf(
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                    ContactsContract.CommonDataKinds.Phone.NUMBER
                ), null, null, null
            )
            cursor!!.moveToFirst()
            val name = cursor.getString(0)        //이름 얻어오기
            val number = cursor.getString(1)

            if(requestCode == 0) {
                Constants.strEmergencyNumber = number
                binding.tv119.text = Constants.strEmergencyNumber

                val editor = settings!!.edit()
                editor.putString(Constants.PREF_EMERGENCY_CALL_NUMBER, Constants.strEmergencyNumber)
                editor.apply()
            }
        }
    }
}