package com.example.contactappfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ItemAdapter(val items: ArrayList<ItemModel>): BaseAdapter() {
    override fun getCount(): Int = items.size

    override fun getItem(pos: Int) = items[pos]

    override fun getItemId(pos: Int) = pos.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemView: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            itemView = LayoutInflater.from(parent?.context).inflate(R.layout.layout_item, parent, false)
            viewHolder = ViewHolder(itemView)
            itemView.tag = viewHolder
        } else {
            itemView = convertView
            viewHolder = itemView.tag as ViewHolder
        }

        viewHolder.avatar.text = items[position].name?.get(0)?.toString()
        viewHolder.name.text = items[position].name

        return itemView
    }
}

class ViewHolder(val itemView: View) {
    val avatar: TextView
    val name: TextView

    init {
        avatar = itemView.findViewById(R.id.avatar)
        name = itemView.findViewById(R.id.name)
    }
}