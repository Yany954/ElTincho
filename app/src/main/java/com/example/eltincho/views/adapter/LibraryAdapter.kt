package com.example.eltincho.views.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eltincho.R

class LibraryAdapter:RecyclerView.Adapter<LibraryAdapter.ViewHolder>(){
    override fun onCreateViewHolder(viewGroup:ViewGroup, i:Int): ViewHolder {
        val v=LayoutInflater.from(viewGroup.context).inflate(R.layout.card_view_entradas, viewGroup, false)
        return ViewHolder(v)
    }
    inner class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView){
        var itemImage: ImageView
        var itemTitle: TextView
        var itemPrecio: TextView
        init{
            itemImage=itemView.findViewById(R.id.image)
            itemTitle=itemView.findViewById(R.id.title)
            itemPrecio=itemView.findViewById(R.id.precio)
        }
    }

    val titles= arrayOf("Empanada de Carne","Empanada de Pollo", "Empanada Capresse","Empanada Carne Dulce","Empanada Carne y Aceitunas")
    val precio= arrayOf("6k","6k","6k","7k","7k")
    val image= arrayOf(R.drawable.home_icon,R.drawable.home_icon,R.drawable.home_icon,R.drawable.home_icon,R.drawable.home_icon)

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text=titles[i]
        viewHolder.itemPrecio.text=precio[i]
        viewHolder.itemImage.setImageResource(image[i])
    }
    override fun getItemCount(): Int {
        return titles.size
    }

}