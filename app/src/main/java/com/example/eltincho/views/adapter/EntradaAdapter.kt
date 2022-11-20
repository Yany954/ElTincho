package com.example.eltincho.views.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eltincho.R
import com.example.eltincho.models.entradas
import com.squareup.picasso.Picasso

class EntradaAdapter(private val context: android.content.Context, var clickLitener: OnEntradaItemClickLitener):RecyclerView.Adapter<EntradaAdapter.ViewHolder>(){
    private var entradaslist= mutableListOf<entradas>()
    //funcion que actualiza la base de datos
    fun setListData(data:MutableList<entradas>){
        entradaslist=data
    }
    override fun onCreateViewHolder(viewGroup:ViewGroup, i:Int): ViewHolder {
        val v=LayoutInflater.from(viewGroup.context).inflate(R.layout.card_view_entradas, viewGroup, false)
        return ViewHolder(v)
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        var entrada=entradaslist[i]
        viewHolder.binWew(entrada,clickLitener)
    }

    override fun getItemCount(): Int {
        return entradaslist.size
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun binWew(entrada: entradas, action:OnEntradaItemClickLitener){
            Picasso.with(context).load(entrada.imagen).into(itemView.findViewById<ImageView>(R.id.image))
            itemView.findViewById<TextView>(R.id.title).text=entrada.title
            itemView.findViewById<TextView>(R.id.precio).text= entrada.price
            val btncarrito=itemView.findViewById<ImageButton>(R.id.carritoItem)
            val btnfavorito=itemView.findViewById<ImageButton>(R.id.favoritosItem)
            btncarrito.setOnClickListener{
                action.onItemClick(entrada,adapterPosition)
            }
            btnfavorito.setOnClickListener{
                action.onDeseosClick(entrada,adapterPosition)
            }
        }

    }
    interface OnEntradaItemClickLitener {
        fun onItemClick(entrada: entradas,position: Int)
        fun onDeseosClick(entrada: entradas,position:Int)
    }





}