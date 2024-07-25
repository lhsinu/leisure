package com.smu.leisure.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LeisureEntity(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name="createddate") val createddate: String,
    @ColumnInfo(name="emergency") val emergency: Int,
    @ColumnInfo(name="s01") val s01: Float,
    @ColumnInfo(name="s02") val s02: Float,
    @ColumnInfo(name="s03") val s03: Float,
    @ColumnInfo(name="s04") val s04: Float,
    @ColumnInfo(name="s05") val s05: Float,
    @ColumnInfo(name="s06") val s06: Float,
    @ColumnInfo(name="s07") val s07: Float,
    @ColumnInfo(name="s08") val s08: Float,
    @ColumnInfo(name="s09") val s09: Float,
    @ColumnInfo(name="s10") val s10: Float,
    @ColumnInfo(name="s11") val s11: Float,
    @ColumnInfo(name="s12") val s12: Float,
    @ColumnInfo(name="s13") val s13: Float,
    @ColumnInfo(name="s14") val s14: Float,
    @ColumnInfo(name="s15") val s15: Float,
    @ColumnInfo(name="s16") val s16: Float,
)
