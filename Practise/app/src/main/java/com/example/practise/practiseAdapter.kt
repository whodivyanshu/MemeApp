package com.example.practise

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PractiseAdapter(val items: ArrayList<String>): RecyclerView.Adapter<ViwHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViwHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)

        return ViwHolder(view)
    }

    override fun onBindViewHolder(holder: ViwHolder, position: Int) {
        val currentItem = items[position]
        holder.titleView.text = currentItem
    }

    override fun getItemCount(): Int {
        return items.size
    }


}



class ViwHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleView: TextView = itemView.findViewById(R.id.title)

}