package com.project.segunfrancis.lol.data

import com.google.gson.annotations.SerializedName

data class MiscellaneousJokeResponse(
    @SerializedName(value = "id") val id: String,
    @SerializedName(value = "joke") val joke: String,
    @SerializedName(value = "status") val status: String
) {
    constructor() : this("", "", "")
}