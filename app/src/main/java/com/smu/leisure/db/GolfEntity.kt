package com.smu.leisure.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.smu.leisure.base.Measurement

@Entity
data class GolfEntity(
    @PrimaryKey(autoGenerate = true) var id : Int? = null,
    @ColumnInfo(name="createddate") val createddate : String,
    @ColumnInfo(name="download") val download : Boolean,
    @ColumnInfo(name="type") val type : String,
    @ColumnInfo(name="chest") val chest : Measurement,
    @ColumnInfo(name="hand") val hand : Measurement,
    @ColumnInfo(name="wrist") val wrist : Measurement,
    @ColumnInfo(name="stomach") val stomach : Measurement,
    @ColumnInfo(name="head") val head : Measurement
)
