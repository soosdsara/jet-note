package com.example.jetnote.data.converter

import androidx.room.TypeConverter
import java.util.UUID

class UUIDConverter {

    @TypeConverter
    fun fromUUID(uuid: UUID): String? = uuid.toString()

    @TypeConverter
    fun uuidFromString(uuidString: String): UUID? = UUID.fromString(uuidString)
}