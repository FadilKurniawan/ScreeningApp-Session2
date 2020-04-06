package com.devfk.ma.screeningapp.data.Interface

import com.devfk.ma.screeningapp.data.Model.Guest

interface IGuest {
    fun onGuestList(listGuest: ArrayList<Guest>)
    fun onDataError(throwable: Throwable)
}