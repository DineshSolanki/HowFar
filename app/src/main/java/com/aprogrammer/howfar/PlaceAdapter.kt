package com.aprogrammer.howfar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView


class PlaceAdapter(
    private val context: Context,
    private val placeModelArrayList: ArrayList<Place>
) :
    BaseAdapter() {
    override fun getViewTypeCount(): Int {
        return count
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getCount(): Int {
        return placeModelArrayList.size
    }

    override fun getItem(position: Int): Any {
        return placeModelArrayList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView1: View? = convertView
        val holder: ViewHolder
        if (convertView1 == null) {
            holder = ViewHolder()
            val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView1 = inflater.inflate(R.layout.list_item, parent, false)
            holder.placeName = convertView1.findViewById(R.id.placeName)
            holder.placeDistance = convertView1.findViewById(R.id.placeDistance)
            convertView1.tag = holder
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = convertView1.tag as ViewHolder
        }
        holder.placeName!!.text = placeModelArrayList[position].Name
        holder.placeDistance!!.text = placeModelArrayList[position].distance.toString()
        return convertView1!!
    }

    private class ViewHolder {
        var placeName: TextView? = null
        var placeDistance: TextView? = null
    }

}