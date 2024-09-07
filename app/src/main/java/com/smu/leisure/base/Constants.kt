package com.smu.leisure.base

import com.smu.leisure.db.LeisureEntity

class Constants {
    companion object {
        val MILLISECOND_DRAW_EFFECT : Long                          = 1000

        var bEmergency: Boolean                                     = false

        var ivHumanWidth = 0
        var ivHumanHeight = 0
        var ivHumanOriginalWidth = 507.0f
        var ivHumanOriginalHeight = 1748.0f

        var llConnectWidth = 0
        var llConnectHeight = 0

        var ivEffectCenterWidth = 0
        var ivEffectCenterHeight = 0
        var ivEffectSideWidth = 0
        var ivEffectSideHeight = 0

        var strEmergencyNumber: String = "01012345678"
        val NETWORK_TIMEOUT : Int = 5000

        val SHARED_PREF_SEUPDATA : String                           = "setupData"
        val PREF_EMERGENCY_CALL_NUMBER : String                     = "prefEmergencyCallNumber"
        val PREF_WIFI_DEVICE : String                               = "prefWifiDevice"
        val PREF_WIFIPORT_DEVICE : String                           = "prefWifiPortDevice"

        var default_wifi_ip                                             = "192.168.4.1"
        var default_wifi_port                                           = "8088"

        val STR_CONNECT                                                = "app"
        val STR_START                                                  = "start"

        var MODULE_ADDRESS_WIFI_IP                                     = default_wifi_ip       // "192.168.4.1"
        var MODULE_ADDRESS_WIFI_PORT                                    = default_wifi_port     // "80"

        var fNumberIndex                                            = listOf("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14")
        var nListIndex : Int                                        = -1

        const val EFFECT_CENTER_WIDTH = 132.0f
        const val EFFECT_CENTER_HEIGHT = 122.0f
        const val EFFECT_LEFT_WIDTH = 118.0f
        const val EFFECT_LEFT_HEIGHT = 116.0f
        const val EFFECT_RIGHT_WIDTH = 118.0f
        const val EFFECT_RIGHT_HEIGHT = 116.0f

//        const val PHYSICAL_S01 = "Head"
//        const val PHYSICAL_S02 = "Neck"
//        const val PHYSICAL_S03 = "Chest"
//        const val PHYSICAL_S04 = "Abdomen"
//        const val PHYSICAL_S05 = "Left\nBrachial"
//        const val PHYSICAL_S06 = "Right\nBrachial"
//        const val PHYSICAL_S07 = "Left\nAntebrachial"
//        const val PHYSICAL_S08 = "Right\nAntebrachial"
//        const val PHYSICAL_S09 = "Left Hand"
//        const val PHYSICAL_S10 = "Right Hand"
//        const val PHYSICAL_S11 = "Left Thigh"
//        const val PHYSICAL_S12 = "Right Thigh"
//        const val PHYSICAL_S13 = "Left Shank"
//        const val PHYSICAL_S14 = "Right Shank"
//        const val PHYSICAL_S15 = "Left Foot"
//        const val PHYSICAL_S16 = "Right Foot"

        // new 14 point
        const val PHYSICAL_S01 = "Neck"
        const val PHYSICAL_S02 = "Abdomen"
        const val PHYSICAL_S03 = "Left Thigh"
        const val PHYSICAL_S04 = "Right Thigh"
        const val PHYSICAL_S05 = "Left\nBrachial"
        const val PHYSICAL_S06 = "Left\nAntebrachial"
        const val PHYSICAL_S07 = "Left Hand"
        const val PHYSICAL_S08 = "Right\nBrachial"
        const val PHYSICAL_S09 = "Right\nAntebrachial"
        const val PHYSICAL_S10 = "Right Hand"
        const val PHYSICAL_S11 = "Left Shank"
        const val PHYSICAL_S12 = "Left Foot"
        const val PHYSICAL_S13 = "Right Shank"
        const val PHYSICAL_S14 = "Right Foot"

//        const val S01_LEFT_DP = 174.0f
//        const val S01_TOP_DP = 0.0f
//        const val S01_RIGHT_DP = 362.0f
//        const val S01_BOTTOM_DP = 174.0f
//        const val S02_LEFT_DP = 172.0f
//        const val S02_TOP_DP = 174.0f
//        const val S02_RIGHT_DP = 364.0f
//        const val S02_BOTTOM_DP = 244.0f
//        const val S03_LEFT_DP = 114.0f
//        const val S03_TOP_DP = 246.0f
//        const val S03_RIGHT_DP = 422.0f
//        const val S03_BOTTOM_DP = 562.0f
//        const val S04_LEFT_DP = 114.0f
//        const val S04_TOP_DP = 560.0f
//        const val S04_RIGHT_DP = 422.0f
//        const val S04_BOTTOM_DP = 674.0f
//        const val S05_LEFT_DP = 406.0f
//        const val S05_TOP_DP = 266.0f
//        const val S05_RIGHT_DP = 508.0f
//        const val S05_BOTTOM_DP = 444.0f
//        const val S06_LEFT_DP = 28.0f
//        const val S06_TOP_DP = 266.0f
//        const val S06_RIGHT_DP = 130.0f
//        const val S06_BOTTOM_DP = 444.0f
//        const val S07_LEFT_DP = 422.0f
//        const val S07_TOP_DP = 560.0f
//        const val S07_RIGHT_DP = 536.0f
//        const val S07_BOTTOM_DP = 646.0f
//        const val S08_LEFT_DP = 0.0f
//        const val S08_TOP_DP = 560.0f
//        const val S08_RIGHT_DP = 114.0f
//        const val S08_BOTTOM_DP = 646.0f
//        const val S09_LEFT_DP = 456.0f
//        const val S09_TOP_DP = 804.0f
//        const val S09_RIGHT_DP = 536.0f
//        const val S09_BOTTOM_DP = 876.0f
//        const val S10_LEFT_DP = 0.0f
//        const val S10_TOP_DP = 804.0f
//        const val S10_RIGHT_DP = 80.0f
//        const val S10_BOTTOM_DP = 876.0f
//        const val S11_LEFT_DP = 268.0f
//        const val S11_TOP_DP = 804.0f
//        const val S11_RIGHT_DP = 442.0f
//        const val S11_BOTTOM_DP = 888.0f
//        const val S12_LEFT_DP = 94.0f
//        const val S12_TOP_DP = 804.0f
//        const val S12_RIGHT_DP = 268.0f
//        const val S12_BOTTOM_DP = 888.0f
//        const val S13_LEFT_DP = 304.0f
//        const val S13_TOP_DP = 1150.0f
//        const val S13_RIGHT_DP = 442.0f
//        const val S13_BOTTOM_DP = 1286.0f
//        const val S14_LEFT_DP = 94.0f
//        const val S14_TOP_DP = 1150.0f
//        const val S14_RIGHT_DP = 232.0f
//        const val S14_BOTTOM_DP = 1286.0f
//        const val S15_LEFT_DP = 332.0f
//        const val S15_TOP_DP = 1588.0f
//        const val S15_RIGHT_DP = 444.0f
//        const val S15_BOTTOM_DP = 1676.0f
//        const val S16_LEFT_DP = 90.0f
//        const val S16_TOP_DP = 1588.0f
//        const val S16_RIGHT_DP = 202.0f
//        const val S16_BOTTOM_DP = 1676.0f

        // new 14 position
        const val S01_LEFT_DP = 172.0f
        const val S01_TOP_DP = 174.0f
        const val S01_RIGHT_DP = 364.0f
        const val S01_BOTTOM_DP = 244.0f
        const val S02_LEFT_DP = 114.0f
        const val S02_TOP_DP = 560.0f
        const val S02_RIGHT_DP = 422.0f
        const val S02_BOTTOM_DP = 674.0f
        const val S03_LEFT_DP = 94.0f
        const val S03_TOP_DP = 804.0f
        const val S03_RIGHT_DP = 268.0f
        const val S03_BOTTOM_DP = 888.0f
        const val S04_LEFT_DP = 268.0f
        const val S04_TOP_DP = 804.0f
        const val S04_RIGHT_DP = 442.0f
        const val S04_BOTTOM_DP = 888.0f
        const val S05_LEFT_DP = 28.0f
        const val S05_TOP_DP = 266.0f
        const val S05_RIGHT_DP = 130.0f
        const val S05_BOTTOM_DP = 444.0f
        const val S06_LEFT_DP = 0.0f
        const val S06_TOP_DP = 560.0f
        const val S06_RIGHT_DP = 114.0f
        const val S06_BOTTOM_DP = 646.0f
        const val S07_LEFT_DP = 0.0f
        const val S07_TOP_DP = 804.0f
        const val S07_RIGHT_DP = 80.0f
        const val S07_BOTTOM_DP = 876.0f
        const val S08_LEFT_DP = 406.0f
        const val S08_TOP_DP = 266.0f
        const val S08_RIGHT_DP = 508.0f
        const val S08_BOTTOM_DP = 444.0f
        const val S09_LEFT_DP = 422.0f
        const val S09_TOP_DP = 560.0f
        const val S09_RIGHT_DP = 536.0f
        const val S09_BOTTOM_DP = 646.0f
        const val S10_LEFT_DP = 456.0f
        const val S10_TOP_DP = 804.0f
        const val S10_RIGHT_DP = 536.0f
        const val S10_BOTTOM_DP = 876.0f
        const val S11_LEFT_DP = 94.0f
        const val S11_TOP_DP = 1150.0f
        const val S11_RIGHT_DP = 232.0f
        const val S11_BOTTOM_DP = 1286.0f
        const val S12_LEFT_DP = 90.0f
        const val S12_TOP_DP = 1588.0f
        const val S12_RIGHT_DP = 202.0f
        const val S12_BOTTOM_DP = 1676.0f
        const val S13_LEFT_DP = 304.0f
        const val S13_TOP_DP = 1150.0f
        const val S13_RIGHT_DP = 442.0f
        const val S13_BOTTOM_DP = 1286.0f
        const val S14_LEFT_DP = 332.0f
        const val S14_TOP_DP = 1588.0f
        const val S14_RIGHT_DP = 444.0f
        const val S14_BOTTOM_DP = 1676.0f

        const val S01_LEFT_POINT = 196.0f
        const val S01_TOP_POINT = 30.0f
        const val S02_LEFT_POINT = 196.0f
        const val S02_TOP_POINT = 124.0f
        const val S03_LEFT_POINT = 216.0f
        const val S03_TOP_POINT = 192.0f
        const val S04_LEFT_POINT = 216.0f
        const val S04_TOP_POINT = 298.0f
        const val S05_LEFT_POINT = 324.0f
        const val S05_TOP_POINT = 238.0f
        const val S06_LEFT_POINT = 104.0f
        const val S06_TOP_POINT = 120.0f
        const val S07_LEFT_POINT = 278.0f
        const val S07_TOP_POINT = 248.0f
        const val S08_LEFT_POINT = 100.0f
        const val S08_TOP_POINT = 248.0f
        const val S09_LEFT_POINT = 278.0f
        const val S09_TOP_POINT = 346.0f
        const val S10_LEFT_POINT = 98.0f
        const val S10_TOP_POINT = 346.0f
        const val S11_LEFT_POINT = 210.0f
        const val S11_TOP_POINT = 352.0f
        const val S12_LEFT_POINT = 136.0f
        const val S12_TOP_POINT = 352.0f
        const val S13_LEFT_POINT = 228.0f
        const val S13_TOP_POINT = 504.0f
        const val S14_LEFT_POINT = 134.0f
        const val S14_TOP_POINT = 690.0f
        const val S15_LEFT_POINT = 240.0f
        const val S15_TOP_POINT = 672.0f
        const val S16_LEFT_POINT = 124.0f
        const val S16_TOP_POINT = 672.0f

//        const val S01_X_POINT = 254.0f
//        const val S01_Y_POINT = 64.0f
//        const val S02_X_POINT = 254.0f
//        const val S02_Y_POINT = 210.0f
//        const val S03_X_POINT = 254.0f
//        const val S03_Y_POINT = 378.0f
//        const val S04_X_POINT = 254.0f
//        const val S04_Y_POINT = 646.0f
//        const val S05_X_POINT = 440.0f
//        const val S05_Y_POINT = 340.0f
//        const val S06_X_POINT = 72.0f
//        const val S06_Y_POINT = 340.0f
//        const val S07_X_POINT = 462.0f
//        const val S07_Y_POINT = 602.0f
//        const val S08_X_POINT = 48.0f
//        const val S08_Y_POINT = 602.0f
//        const val S09_X_POINT = 492.0f
//        const val S09_Y_POINT = 846.0f
//        const val S10_X_POINT = 18.0f
//        const val S10_Y_POINT = 846.0f
//        const val S11_X_POINT = 352.0f
//        const val S11_Y_POINT = 848.0f
//        const val S12_X_POINT = 160.0f
//        const val S12_Y_POINT = 848.0f
//        const val S13_X_POINT = 360.0f
//        const val S13_Y_POINT = 1220.0f
//        const val S14_X_POINT = 146.0f
//        const val S14_Y_POINT = 1220.0f
//        const val S15_X_POINT = 376.0f
//        const val S15_Y_POINT = 1638.0f
//        const val S16_X_POINT = 134.0f
//        const val S16_Y_POINT = 1638.0f


        // new 14 position

        val SENSOR_X_POINT = listOf(254.0f, 254.0f, 160.0f, 352.0f, 72.0f, 48.0f, 18.0f, 440.0f, 462.0f, 492.0f, 146.0f, 134.0f, 360.0f, 376.0f)

        val SENSOR_Y_POINT = listOf(210.0f, 646.0f, 848.0f, 848.0f, 340.0f, 602.0f, 846.0f, 340.0f, 602.0f, 846.0f, 1220.0f, 1638.0f, 1220.0f, 1638.0f)

        const val S01_X_POINT = 254.0f
        const val S01_Y_POINT = 210.0f
        const val S02_X_POINT = 254.0f
        const val S02_Y_POINT = 646.0f
        const val S03_X_POINT = 160.0f
        const val S03_Y_POINT = 848.0f
        const val S04_X_POINT = 352.0f
        const val S04_Y_POINT = 848.0f
        const val S05_X_POINT = 72.0f
        const val S05_Y_POINT = 340.0f
        const val S06_X_POINT = 48.0f
        const val S06_Y_POINT = 602.0f
        const val S07_X_POINT = 18.0f
        const val S07_Y_POINT = 846.0f
        const val S08_X_POINT = 440.0f
        const val S08_Y_POINT = 340.0f
        const val S09_X_POINT = 462.0f
        const val S09_Y_POINT = 602.0f
        const val S10_X_POINT = 492.0f
        const val S10_Y_POINT = 846.0f
        const val S11_X_POINT = 146.0f
        const val S11_Y_POINT = 1220.0f
        const val S12_X_POINT = 360.0f
        const val S12_Y_POINT = 1220.0f
        const val S13_X_POINT = 134.0f
        const val S13_Y_POINT = 1638.0f
        const val S14_X_POINT = 376.0f
        const val S14_Y_POINT = 1638.0f

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


        const val STATUS_INDEX_IDLE : Int = 0
        const val STATUS_INDEX_CONNECT : Int = 1
        const val STATUS_INDEX_WAITING : Int = 2
        const val STATUS_INDEX_SWING : Int = 3
        const val STATUS_INDEX_RECEIVE : Int = 4
        const val STATUS_INDEX_RAWDATA : Int = 5



        var LeisureData: LeisureEntity = LeisureEntity(
            1,
            "0",
            "S",
            SensorData("S", 0.00, 0.00, 0.00),
            SensorData("S", 0.00, 0.00, 0.00),
            SensorData("S", 0.00, 0.00, 0.00),
            SensorData("S", 0.00, 0.00, 0.00),
            SensorData("S", 0.00, 0.00, 0.00),
            SensorData("S", 0.00, 0.00, 0.00),
            SensorData("S", 0.00, 0.00, 0.00),
            SensorData("S", 0.00, 0.00, 0.00),
            SensorData("S", 0.00, 0.00, 0.00),
            SensorData("S", 0.00, 0.00, 0.00),
            SensorData("S", 0.00, 0.00, 0.00),
            SensorData("S", 0.00, 0.00, 0.00),
            SensorData("S", 0.00, 0.00, 0.00),
            SensorData("S", 0.00, 0.00, 0.00),)

        var nStatusIndex : Int = STATUS_INDEX_IDLE
        var strSaveFileName : String = ""
        var fSelIndex = ArrayList<Double>()
        var arClickArea = ArrayList<ClickableArea>()

        var recentCreateddate = ""
        var recentEmergency : Int = 1
        var recentS01 = 0.0
        var recentS02 = 0.0
        var recentS03 = 0.0
        var recentS04 = 0.0
        var recentS05 = 0.0
        var recentS06 = 0.0
        var recentS07 = 0.0
        var recentS08 = 0.0
        var recentS09 = 0.0
        var recentS10 = 0.0
        var recentS11 = 0.0
        var recentS12 = 0.0
        var recentS13 = 0.0
        var recentS14 = 0.0
        var recentS15 = 0.0
        var recentS16 = 0.0
    }
}