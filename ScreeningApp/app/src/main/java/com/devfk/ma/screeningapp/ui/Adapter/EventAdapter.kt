package com.devfk.ma.screeningapp.ui.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.devfk.ma.screeningapp.R
import com.devfk.ma.screeningapp.data.Model.Event


class EventAdapter(nameItem: ArrayList<Event>) : BaseAdapter(){
    private val item = nameItem

    @SuppressLint("ViewHolder")
    override fun getView(position:Int, convertView: View?, parent: ViewGroup?):View{
        // Inflate the custom view
        val inflater = parent?.context?.
            getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view    = inflater.inflate(R.layout.list_item,null)
        // Get the custom view widgets reference
        val title = view.findViewById<TextView>(R.id.txvNameEvent)
        val date = view.findViewById<TextView>(R.id.txvDateEvent)
//        val hashtag = view.findViewById<TextView>(R.id.txvNameEvent)
        val detail = view.findViewById<TextView>(R.id.txvDetailEvent)
        val image = view.findViewById<ImageView>(R.id.imgEvent)

        title.text = item[position].name
        date.text = item[position].date
        detail.text = item[position].detail

        image.setBackgroundResource(item[position].image)

        return view
    }

    override fun getItem(position: Int): Any? {
        return item[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    // Count the items
    override fun getCount(): Int {
        return item.size
    }

}