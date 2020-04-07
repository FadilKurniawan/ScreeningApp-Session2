package com.devfk.ma.screeningapp.data.Presenter

import android.content.Context
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.devfk.ma.screeningapp.data.Interface.IGuest
import com.devfk.ma.screeningapp.data.Model.Guest
import com.devfk.ma.screeningapp.ui.Component.Constant

class GuestPresenter (context:Context){
    val GuestView = context as IGuest

    fun getDataGuest(){
        AndroidNetworking.get(Constant.BASE_URL)
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