package com.smu.leisure.base

import com.smu.leisure.db.LeisureEntity

class Constants {
    companion object {
        var ivHumanWidth = 0
        var ivHumanHeight = 0

        var strEmergencyNumber: String = "01012345678"
        val NETWORK_TIMEOUT : Int = 5000

        val SHARED_PREF_SEUPDATA : String                           = "setupData"
        val PREF_EMERGENCY_CALL_NUMBER : String                     = "prefEmergencyCallNumber"
        val PREF_WIFI_DEVICE : String                               = "prefWifiDevice"
        val PREF_WIFIPORT_DEVICE : String                           = "prefWifiPortDevice"

        var default_wifi_ip                                             = "192.168.4.1"
        var default_wifi_port                                           = "8088"


        var MODULE_ADDRESS_WIFI_CAM                                     = default_wifi_ip       // "192.168.4.1"
        var MODULE_ADDRESS_WIFI_PORT                                    = default_wifi_port     // "80"

        const val PHYSICAL_S01 = "Head"
        const val PHYSICAL_S02 = "Neck"
        const val PHYSICAL_S03 = "Chest"
        const val PHYSICAL_S04 = "Abdomen"
        const val PHYSICAL_S05 = "Left\nBrachial"
        const val PHYSICAL_S06 = "Right\nBrachial"
        const val PHYSICAL_S07 = "Left\nAntebrachial"
        const val PHYSICAL_S08 = "Right\nAntebrachial"
        const val PHYSICAL_S09 = "Left Hand"
        const val PHYSICAL_S10 = "Right Hand"
        const val PHYSICAL_S11 = "Left Thigh"
        const val PHYSICAL_S12 = "Right Thigh"
        const val PHYSICAL_S13 = "Left Shank"
        const val PHYSICAL_S14 = "Right Shank"
        const val PHYSICAL_S15 = "Left Foot"
        const val PHYSICAL_S16 = "Right Foot"

        const val S01_LEFT_DP = 172.0f
        const val S01_TOP_DP = 0.0f
        const val S01_RIGHT_DP = 240.0f
        const val S01_BOTTOM_DP = 72.0f
        const val S02_LEFT_DP = 172.0f
        const val S02_TOP_DP = 72.0f
        const val S02_RIGHT_DP = 240.0f
        const val S02_BOTTOM_DP = 122.0f
        const val S03_LEFT_DP = 142.0f
        const val S03_TOP_DP = 122.0f
        const val S03_RIGHT_DP = 268.0f
        const val S03_BOTTOM_DP = 232.0f
        const val S04_LEFT_DP = 142.0f
        const val S04_TOP_DP = 234.0f
        const val S04_RIGHT_DP = 268.0f
        const val S04_BOTTOM_DP = 352.0f
        const val S05_LEFT_DP = 270.0f
        const val S05_TOP_DP = 120.0f
        const val S05_RIGHT_DP = 306.0f
        const val S05_BOTTOM_DP = 246.0f
        const val S06_LEFT_DP = 104.0f
        const val S06_TOP_DP = 120.0f
        const val S06_RIGHT_DP = 140.0f
        const val S06_BOTTOM_DP = 246.0f
        const val S07_LEFT_DP = 278.0f
        const val S07_TOP_DP = 248.0f
        const val S07_RIGHT_DP = 312.0f
        const val S07_BOTTOM_DP = 346.0f
        const val S08_LEFT_DP = 100.0f
        const val S08_TOP_DP = 248.0f
        const val S08_RIGHT_DP = 134.0f
        const val S08_BOTTOM_DP = 346.0f
        const val S09_LEFT_DP = 278.0f
        const val S09_TOP_DP = 346.0f
        const val S09_RIGHT_DP = 314.0f
        const val S09_BOTTOM_DP = 410.0f
        const val S10_LEFT_DP = 98.0f
        const val S10_TOP_DP = 346.0f
        const val S10_RIGHT_DP = 134.0f
        const val S10_BOTTOM_DP = 410.0f
        const val S11_LEFT_DP = 210.0f
        const val S11_TOP_DP = 352.0f
        const val S11_RIGHT_DP = 276.0f
        const val S11_BOTTOM_DP = 504.0f
        const val S12_LEFT_DP = 136.0f
        const val S12_TOP_DP = 352.0f
        const val S12_RIGHT_DP = 202.0f
        const val S12_BOTTOM_DP = 504.0f
        const val S13_LEFT_DP = 228.0f
        const val S13_TOP_DP = 504.0f
        const val S13_RIGHT_DP = 278.0f
        const val S13_BOTTOM_DP = 672.0f
        const val S14_LEFT_DP = 134.0f
        const val S14_TOP_DP = 504.0f
        const val S14_RIGHT_DP = 184.0f
        const val S14_BOTTOM_DP = 672.0f
        const val S15_LEFT_DP = 240.0f
        const val S15_TOP_DP = 672.0f
        const val S15_RIGHT_DP = 290.0f
        const val S15_BOTTOM_DP = 722.0f
        const val S16_LEFT_DP = 124.0f
        const val S16_TOP_DP = 672.0f
        const val S16_RIGHT_DP = 174.0f
        const val S16_BOTTOM_DP = 722.0f



        const val POINT_AREA_S01 : Int = 1
        const val POINT_AREA_S02 : Int = 2
        const val POINT_AREA_S03 : Int = 3
        const val POINT_AREA_S04 : Int = 4
        const val POINT_AREA_S05 : Int = 5
        const val POINT_AREA_S06 : Int = 6
        const val POINT_AREA_S07 : Int = 7
        const val POINT_AREA_S08 : Int = 8
        const val POINT_AREA_S09 : Int = 9
        const val POINT_AREA_S10 : Int = 10
        const val POINT_AREA_S11 : Int = 11
        const val POINT_AREA_S12 : Int = 12
        const val POINT_AREA_S13 : Int = 13
        const val POINT_AREA_S14 : Int = 14
        const val POINT_AREA_S15 : Int = 15
        const val POINT_AREA_S16 : Int = 16


        const val STATUS_INDEX_IDLE : Int = 0
        const val STATUS_INDEX_CONNECT : Int = 1
        const val STATUS_INDEX_WAITING : Int = 2
        const val STATUS_INDEX_SWING : Int = 3
        const val STATUS_INDEX_RECEIVE : Int = 4
        const val STATUS_INDEX_RAWDATA : Int = 5

        const val NETWORK_IP = "192.168.4.1" // Replace with the IP address you want to connect to
        const val NETWORK_PORT = 8088 // Replace with the port number you want to connect to


        var LeisureData: LeisureEntity = LeisureEntity(
            1,
            "0",
            1,
            0.0f,
            0.0f,
            0.0f,
            0.0f,
            0.0f,
            0.0f,
            0.0f,
            0.0f,
            0.0f,
            0.0f,
            0.0f,
            0.0f,
            0.0f,
            0.0f,
            0.0f,
            0.0f,)

        var nStatusIndex : Int = STATUS_INDEX_IDLE
        var strSaveFileName : String = ""
        var fSelIndex = ArrayList<Float>()
        var arClickArea = ArrayList<ClickableArea>()

        var recentCreateddate = ""
        var recentEmergency : Int = 1
        var recentS01 = 0.0f
        var recentS02 = 0.0f
        var recentS03 = 0.0f
        var recentS04 = 0.0f
        var recentS05 = 0.0f
        var recentS06 = 0.0f
        var recentS07 = 0.0f
        var recentS08 = 0.0f
        var recentS09 = 0.0f
        var recentS10 = 0.0f
        var recentS11 = 0.0f
        var recentS12 = 0.0f
        var recentS13 = 0.0f
        var recentS14 = 0.0f
        var recentS15 = 0.0f
        var recentS16 = 0.0f
    }
}