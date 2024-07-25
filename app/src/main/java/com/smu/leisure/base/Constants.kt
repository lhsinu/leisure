package com.smu.leisure.base

import com.smu.leisure.db.LeisureEntity

class Constants {
    companion object {
        var ivHumanWidth = 0
        var ivHumanHeight = 0

        var strEmergencyNumber: String = "01012345678"

        val SHARED_PREF_SEUPDATA : String                           = "setupData"
        val PREF_EMERGENCY_CALL_NUMBER : String                     = "prefEmergencyCallNumber"
        val PREF_WIFI_DEVICE : String                               = "prefWifiDevice"
        val PREF_WIFIPORT_DEVICE : String                           = "prefWifiPortDevice"

        var default_wifi_ip                                             = "192.168.4.1"
        var default_wifi_port                                           = "80"


        var MODULE_ADDRESS_WIFI_CAM                                     = default_wifi_ip       // "192.168.4.1"
        var MODULE_ADDRESS_WIFI_PORT                                    = default_wifi_port     // "80"

        const val S01_LEFT_DP = 176f
        const val S01_TOP_DP = 0f
        const val S01_RIGHT_DP = 261.0f
        const val S01_BOTTOM_DP = 79.0f
        const val S02_LEFT_DP = 176.0f
        const val S02_TOP_DP = 79.0f
        const val S02_RIGHT_DP = 261.0f
        const val S02_BOTTOM_DP = 102.0f
        const val S03_LEFT_DP = 152.0f
        const val S03_TOP_DP = 102.0f
        const val S03_RIGHT_DP = 285.0f
        const val S03_BOTTOM_DP = 283.0f
        const val S04_LEFT_DP = 148.0f
        const val S04_TOP_DP = 283.0f
        const val S04_RIGHT_DP = 291.0f
        const val S04_BOTTOM_DP = 371.0f
        const val S05_LEFT_DP = 285.0f
        const val S05_TOP_DP = 124.0f
        const val S05_RIGHT_DP = 320.0f
        const val S05_BOTTOM_DP = 255.0f
        const val S06_LEFT_DP = 115.0f
        const val S06_TOP_DP = 124.0f
        const val S06_RIGHT_DP = 152.0f
        const val S06_BOTTOM_DP = 251.0f
        const val S07_LEFT_DP = 290.0f
        const val S07_TOP_DP = 255.0f
        const val S07_RIGHT_DP = 326.0f
        const val S07_BOTTOM_DP = 354.0f
        const val S08_LEFT_DP = 105.0f
        const val S08_TOP_DP = 252.0f
        const val S08_RIGHT_DP = 143.0f
        const val S08_BOTTOM_DP = 320.0f
        const val S09_LEFT_DP = 278.0f
        const val S09_TOP_DP = 354.0f
        const val S09_RIGHT_DP = 318.0f
        const val S09_BOTTOM_DP = 443.0f
        const val S10_LEFT_DP = 96.0f
        const val S10_TOP_DP = 320.0f
        const val S10_RIGHT_DP = 139.0f
        const val S10_BOTTOM_DP = 377.0f
        const val S11_LEFT_DP = 224.0f
        const val S11_TOP_DP = 370.0f
        const val S11_RIGHT_DP = 298.0f
        const val S11_BOTTOM_DP = 525.0f
        const val S12_LEFT_DP = 150.0f
        const val S12_TOP_DP = 371.0f
        const val S12_RIGHT_DP = 217.0f
        const val S12_BOTTOM_DP = 523.0f
        const val S13_LEFT_DP = 246.0f
        const val S13_TOP_DP = 525.0f
        const val S13_RIGHT_DP = 302.0f
        const val S13_BOTTOM_DP = 688.0f
        const val S14_LEFT_DP = 151.0f
        const val S14_TOP_DP = 524.0f
        const val S14_RIGHT_DP = 201.0f
        const val S14_BOTTOM_DP = 683.0f
        const val S15_LEFT_DP = 255.0f
        const val S15_TOP_DP = 689.0f
        const val S15_RIGHT_DP = 308.0f
        const val S15_BOTTOM_DP = 721.0f
        const val S16_LEFT_DP = 128.0f
        const val S16_TOP_DP = 684.0f
        const val S16_RIGHT_DP = 198.0f
        const val S16_BOTTOM_DP = 721.0f



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
        const val NETWORK_PORT = 80 // Replace with the port number you want to connect to


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
        var emergency : Int = 1
        var s01 = 0.0f
        var s02 = 0.0f
        var s03 = 0.0f
        var s04 = 0.0f
        var s05 = 0.0f
        var s06 = 0.0f
        var s07 = 0.0f
        var s08 = 0.0f
        var s09 = 0.0f
        var s10 = 0.0f
        var s11 = 0.0f
        var s12 = 0.0f
        var s13 = 0.0f
        var s14 = 0.0f
        var s15 = 0.0f
        var s16 = 0.0f
    }
}