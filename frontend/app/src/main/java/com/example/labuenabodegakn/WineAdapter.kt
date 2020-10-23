package com.example.labuenabodegakn

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.labuenabodegakn.models.Wine
import com.squareup.picasso.Picasso

class WineAdapter(var wineList: ArrayList<Wine>, val context: Context) : RecyclerView.Adapter<WineAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.wine_list_row, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(wineList[position], context)
    }

    override fun getItemCount(): Int {
        return wineList.size;
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(w: Wine, context: Context){
            val url = "http://192.168.103.25:8080/img/wine-"
            val txt_name: TextView = itemView.findViewById(R.id.textViewName)
            val txt_type: TextView = itemView.findViewById(R.id.textViewType)

            val img: ImageView = itemView.findViewById(R.id.imageViewWine)

            txt_name.text = w.name
            txt_type.text = w.type


            val imageUrl = url + w.id.toString() + ".png"
            Picasso.with(context).load(imageUrl).into(img);

            itemView.setOnClickListener {
                val intent = Intent(context, WineDetailActivity::class.java)
                intent.putExtra("wineId", w.id)
                intent.putExtra("state", "Showing")
                Log.v("hola caracola antes", w.id.toString())
                context.startActivity(intent)
            }
        }
    }
}