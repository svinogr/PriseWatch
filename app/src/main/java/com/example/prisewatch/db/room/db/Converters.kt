package com.example.prisewatch.db.room.db

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun toDate(date: Long): Date {
        return Date(date)
    }

    @TypeConverter
    fun fromDate(date: Date): Long {
        return date.time
    }
}