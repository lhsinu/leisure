package com.smu.leisure.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [LeisureEntity::class], version = 1)
@TypeConverters(MeasurementTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getLeisureDao() : LeisureDao

    companion object {
        val databaseName = "db_leisure"
        var appDatabase : AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase? {
            if(appDatabase == null) {
                appDatabase = Room.databaseBuilder(context, AppDatabase::class.java, databaseName).build()
            }
            return appDatabase
        }
    }
}