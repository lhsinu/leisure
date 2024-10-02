package com.smu.leisure

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.smu.leisure.base.Constants
import com.smu.leisure.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntroBinding

    val PERMISSIONS_REQUEST_CODE = 100

    private var settings: SharedPreferences? = null

    var REQUIRED_PERMISSIONS = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.SEND_SMS,
            Manifest.permission.READ_SMS,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.INTERNET
        )
    } else {
        TODO("VERSION.SDK_INT < S")
        arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.SEND_SMS,
            Manifest.permission.READ_SMS,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.INTERNET
        )
    }

    lateinit var getGPSPermissionLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settings = getSharedPreferences(Constants.SHARED_PREF_SEUPDATA, Context.MODE_PRIVATE)

        Constants.strEmergencyNumber =
            settings!!.getString(Constants.PREF_EMERGENCY_CALL_NUMBER, Constants.strEmergencyNumber).toString()

        Constants.MODULE_ADDRESS_WIFI_IP =
            settings!!.getString(Constants.PREF_WIFI_DEVICE, Constants.default_wifi_ip).toString()
        Constants.MODULE_ADDRESS_WIFI_PORT =
            settings!!.getString(Constants.PREF_WIFIPORT_DEVICE, Constants.default_wifi_port).toString()

        Constants.MODULE_ELAPSED_TIME =
            (settings!!.getString(Constants.PREF_ELAPSED_TIME, (Constants.default_elapsedTime).toString()))!!.toLong()

        Constants.bEmergency = false
        checkAllPermissions()
    }

    private fun checkAllPermissions() {
        if(!isLocationServicesAvailable()) {
            showDialogForLocationServiceSetting()
        } else {
            isRunTimePermissionsGranted()
        }
    }

    private fun isLocationServicesAvailable(): Boolean {
        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager

        return (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER))
    }

    private fun isRunTimePermissionsGranted() {
        val hasFineLocationPermission = ContextCompat.checkSelfPermission(this@IntroActivity, Manifest.permission.ACCESS_FINE_LOCATION)

        val hasCoarseLocationPermission = ContextCompat.checkSelfPermission(this@IntroActivity, Manifest.permission.ACCESS_COARSE_LOCATION)

        if(hasFineLocationPermission != PackageManager.PERMISSION_GRANTED || hasCoarseLocationPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this@IntroActivity, REQUIRED_PERMISSIONS, PERMISSIONS_REQUEST_CODE)
        } else {
            val intent = Intent(this@IntroActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == PERMISSIONS_REQUEST_CODE && grantResults.size == REQUIRED_PERMISSIONS.size) {
            var checkResult = true

            for(result in grantResults) {
                if(result != PackageManager.PERMISSION_GRANTED) {
                    checkResult = false
                    break
                }
            }

            if(checkResult) {
                val intent = Intent(this@IntroActivity, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this@IntroActivity, "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요.", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun showDialogForLocationServiceSetting() {
        getGPSPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
                result ->
            if(result.resultCode == Activity.RESULT_OK) {
                if(isLocationServicesAvailable()) {
                    isRunTimePermissionsGranted()
                } else {
                    Toast.makeText(this@IntroActivity, "위치 서비스를 사용할 수 없습니다.", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }

        val builder : AlertDialog.Builder = AlertDialog.Builder(this@IntroActivity)
        builder.setTitle("위치 서비스 비활성화")
        builder.setMessage("위치 서비스가 꺼져 있습니다. 설정해야 앱을 사용할 수 있습니다.")
        builder.setCancelable(true)
        builder.setPositiveButton("설정") { _, _ ->
            val callGPSSettingIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            getGPSPermissionLauncher.launch(callGPSSettingIntent)
        }
        builder.setNegativeButton("취소") { dialog, _ ->
            dialog.cancel()
            Toast.makeText(this@IntroActivity, "기기에서 위치서비스(GPS) 설정 후 사용 해 주세요.", Toast.LENGTH_SHORT)
                .show()
            finish()
        }
        builder.create().show()
    }
}