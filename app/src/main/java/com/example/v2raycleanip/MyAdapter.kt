package com.example.v2raycleanip

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class MyAdapter(val context:Context,val nameList:MutableList<DataModel>):RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView:View):ViewHolder(itemView){
        val tv = itemView.findViewById<TextView>(R.id.nametv)

    }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
         val v:View = LayoutInflater.from(context).inflate(R.layout.item,parent,false)

         return MyViewHolder(v)
        }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tv.text = nameList[position].name_en
    }

    override fun getItemCount(): Int {
      return nameList.size
   }
}