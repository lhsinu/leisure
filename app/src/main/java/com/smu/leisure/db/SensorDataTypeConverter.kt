package com.smu.leisure.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.smu.leisure.base.SensorData

class SensorDataTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromSensorData(sensordata: SensorData): String {
        return gson.toJson(sensordata)
    }

    @TypeConverter
    fun toSensorData(sensordataString: String): SensorData {
        val type = object : TypeToken<SensorData>() {}.type
        return gson.fromJson(sensordataString, type)
    }
}