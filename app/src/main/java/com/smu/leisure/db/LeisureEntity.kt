package com.smu.leisure.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.smu.leisure.base.SensorData

@Entity
data class LeisureEntity(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name="createddate") val createddate: String,
    @ColumnInfo(name="emergency") val emergency: String,
    @ColumnInfo(name="s01") val s01: SensorData,
    @ColumnInfo(name="s02") val s02: SensorData,
    @ColumnInfo(name="s03") val s03: SensorData,
    @ColumnInfo(name="s04") val s04: SensorData,
    @ColumnInfo(name="s05") val s05: SensorData,
    @ColumnInfo(name="s06") val s06: SensorData,
    @ColumnInfo(name="s07") val s07: SensorData,
    @ColumnInfo(name="s08") val s08: SensorData,
    @ColumnInfo(name="s09") val s09: SensorData,
    @ColumnInfo(name="s10") val s10: SensorData,
    @ColumnInfo(name="s11") val s11: SensorData,
    @ColumnInfo(name="s12") val s12: SensorData,
    @ColumnInfo(name="s13") val s13: SensorData,
    @ColumnInfo(name="s14") val s14: SensorData,
)
