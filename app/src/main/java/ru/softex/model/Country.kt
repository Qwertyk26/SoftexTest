package ru.softex.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Country(
    @SerializedName("Id")
    @field:PrimaryKey
    val id: String,
    @SerializedName("Time")
    val time: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Image")
    val image: String? = null
)
