package com.devfk.ma.screeningapp.ui.Activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.devfk.ma.screeningapp.R
import com.devfk.ma.screeningapp.data.Interface.IGuest
import com.devfk.ma.screeningapp.data.Model.Guest
import com.devfk.ma.screeningapp.data.Presenter.GuestPresenter
import com.devfk.ma.screeningapp.ui.Adapter.GuestAdapter
import kotlinx.android.synthetic.main.activity_guest.*
import kotlinx.android.synthetic.main.app_bar.*

class GuestActivity : AppCompatActivity(), AdapterView.OnItemClickListener ,IGuest,View.OnClickListener{
    var resultName:String =""
    var resultAge:Int =0
    var resultAgeMonth:Int =0
    lateinit var swipe: SwipeRefreshLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest)
        initialization()
        RefreshListener()
    }

    private fun RefreshListener() {
        swipe.setOnRefreshListener {
            GuestPresenter(this).getDataGuest()
        }
    }

    private fun initialization() {
        headerText.text = resources.getString(R.string.appBar_guest)
        GuestPresenter(this).getDataGuest()
        loadview.visibility = View.VISIBLE
        gridView.onItemClickListener = this
        btn_back.setOnClickListener(this)
        btn_media.visibility = View.GONE
        swipe = findViewById<SwipeRefreshLayout>(R.id.swipeContainer)
    }
    override fun onBackPressed() {
        if(resultName.isNotEmpty()){
            sendDataBack()
        }
        super.onBackPressed()
    }

    private fun sendDataBack() {
        val intent = Intent().apply {
            putExtra("guestName",resultName)
            putExtra("guestAge",resultAge.toString())
            putExtra("guestAgeMonth",resultAgeMonth.toString())
        }
        setResult(Activity.RESULT_OK,intent)
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var item: Guest = parent?.getItemAtPosition(position) as Guest
        resultName = item.name
        resultAge = item.birthdate.substring(item.birthdate.length-2,item.birthdate.length).toInt()
        resultAgeMonth = item.birthdate.substring(6,7).toInt()
        onBackPressed()
    }

    override fun onGuestList(listGuest: ArrayList<Guest>) {
        if(swipe.isRefreshing){
            swipe.isRefreshing = false
        }
        gridView.adapter = GuestAdapter(listGuest)
        loadview.visibility = View.GONE
    }

    override fun onDataError(throwable: Throwable) {
        if(swipe.isRefreshing){
            swipe.isRefreshing = false
        }
        Toast.makeText(this,"Error: $throwable",Toast.LENGTH_LONG).show()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_back -> onBackPressed()
        }
    }
}
