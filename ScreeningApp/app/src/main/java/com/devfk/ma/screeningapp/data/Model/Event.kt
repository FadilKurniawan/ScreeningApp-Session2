package com.devfk.ma.screeningapp.data.Model

import android.location.Location
import android.media.Image

data class Event(
    val name:String,
    val date:String,
    val image:Int,
    val hashtag:List<String>,
    val detail:String,
    val lat:String,
    val long: String
)