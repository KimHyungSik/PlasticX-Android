package com.example.plasticx.model

import java.io.Serializable

data class TumblerItem(
    val imageUrl : String,
    val tumblerName : String,
    val tumblerStartDay : String,
    val tumblerEndDay : String,
    val tumblerFrom : String?,
    val status : Boolean
):Serializable
