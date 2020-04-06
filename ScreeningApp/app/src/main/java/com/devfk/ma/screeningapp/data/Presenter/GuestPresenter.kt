package com.devfk.ma.screeningapp.data.Presenter

import android.content.Context
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.devfk.ma.screeningapp.data.Interface.IGuest
import com.devfk.ma.screeningapp.data.Model.Guest

class GuestPresenter (context:Context){
    val GuestView = context as IGuest
    val BASE_URL =  "http://www.mocky.io/v2/596dec7f0f000023032b8017"
    fun getDataGuest(){
        AndroidNetworking.get(BASE_URL)
            .setPriority(Priority.HIGH)
            .build()
            .getAsObjectList(Guest::class.java, object :
                ParsedRequestListener<ArrayList<Guest>> {
                override fun onResponse(response: ArrayList<Guest>) {
                    GuestView.onGuestList(response)
                }
                override fun onError(error: ANError) { // handle error
                    GuestView.onDataError(error)
                }
            })
    }
}