package com.smu.leisure.base

import com.smu.leisure.db.LeisureEntity

class Constants {
    companion object {
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
            '1',
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

        var recentCreateddate = ""
        var emergency : Char = '1'
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