package com.example.eltincho.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.eltincho.R
import com.example.eltincho.models.favoritos
import com.squareup.picasso.Picasso

class FavoritosAdapter(private val context: Context, var clickListenerDeseosAdapter: OnDeseosItemClickLitener):RecyclerView.Adapter<FavoritosAdapter.ViewHolder>() {
    private var favoritoslist= mutableListOf<favoritos>()
    //funcion que actualiza la base de datos
    fun setListData(data:MutableList<favoritos>){
        if(favoritoslist.size<1){
            Toast.makeText(context,"No has agregado nada a tu lista de favoritos", Toast.LENGTH_LONG).show()
        }
        favoritoslist=data
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, i:Int): ViewHolder {
        val v= LayoutInflater.from(viewGroup.context).inflate(R.layout.cardv_view_favoritos, viewGroup, false)
        return ViewHolder(v)
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        var favorito=favoritoslist[i]
        viewHolder.binWew(favorito,clickListenerDeseosAdapter)
    }

    override fun getItemCount(): Int {
        return favoritoslist.size
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun binWew(favorito: favoritos, action:OnDeseosItemClickLitener){
            Picasso.with(context).load(favorito.image).into(itemView.findViewById<ImageView>(R.id.image))
            itemView.findViewById<TextView>(R.id.title).text=favorito.titulo
            itemView.findViewById<TextView>(R.id.precio).text= favorito.precio
            val btneliminar=itemView.findViewById<ImageButton>(R.id.eliminarFav)
            btneliminar.setOnClickListener{
                action.onItemClick(favorito,adapterPosition)
            }
        }

    }
    interface OnDeseosItemClickLitener {
        fun onItemClick(favorito: favoritos, position: Int)
    }
}