package com.project.segunfrancis.lol.data

import com.google.gson.annotations.SerializedName

data class AlternateJokeResponse(
    @SerializedName(value = "id") val id: Int,
    @SerializedName(value = "type") val type: String,
    @SerializedName(value = "setup") val setup: String,
    @SerializedName(value = "punchline") val punchline: String
) {
    constructor() : this(-1, "", "", "")
}