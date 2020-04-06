package com.devfk.ma.screeningapp.ui.Activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.devfk.ma.screeningapp.R
import com.devfk.ma.screeningapp.data.Model.Event
import com.devfk.ma.screeningapp.ui.Adapter.EventAdapter
import kotlinx.android.synthetic.main.activity_event.*
import kotlinx.android.synthetic.main.app_bar.*

class EventActivity : AppCompatActivity() ,AdapterView.OnItemClickListener,View.OnClickListener{
    var result:String =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)
        initialization()
    }

    private fun initialization() {
        headerText.text = resources.getString(R.string.appBar_event)

        var eventList:ArrayList<Event> = ArrayList<Event>()
        eventList.add(Event("Pekan #KreatifDisaatSulit", "April 04 2020", R.drawable.event1))
        eventList.add(Event("Saturasi Volume 1", "April 03  2020", R.drawable.event3))
        eventList.add(Event("Charity Selection Night", "March 23 2020", R.drawable.event2))
        eventList.add(Event("ANGKLUNG MUSIC CONCERT", " March 20 2020", R.drawable.event4))

        lv_event.adapter = EventAdapter(eventList)
        lv_event.onItemClickListener = this

        btn_back.setOnClickListener(this)
        btn_media.visibility = View.VISIBLE
    }

    override fun onBackPressed() {
        if(result.isNotEmpty()){
            sendDataBack()
        }
        super.onBackPressed()
    }

    private fun sendDataBack() {
        val intent = Intent().apply {
            putExtra("event",result)
        }
        setResult(Activity.RESULT_OK,intent)
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var item:Event = parent?.getItemAtPosition(position) as Event
        result = item.name
        onBackPressed()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_back -> onBackPressed()
            R.id.btn_media -> onMediaClick()
        }
    }

    private fun onMediaClick() {

    }
}
