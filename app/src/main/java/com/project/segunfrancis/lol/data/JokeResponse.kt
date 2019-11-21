package com.project.segunfrancis.lol.data

import com.google.gson.annotations.SerializedName

data class
JokeResponse(
    @SerializedName(value = "category") val category: String,
    @SerializedName(value = "type") val type: String,
    @SerializedName(value = "joke") val joke: String,
    @SerializedName(value = "setup") val setup: String,
    @SerializedName(value = "delivery") val delivery: String,
    @SerializedName(value = "id") val id: Int
) {
    constructor() : this("", "", "", "", "", -1)
}