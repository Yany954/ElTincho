package com.example.eltincho.Views.Ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.example.eltincho.R

class menuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ayuda, container, false)
    }
    override fun onViewCreated(view:View,  savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        val cardPlatillo=view.findViewById<ImageView>(R.id.cardPlatillos)
        cardPlatillo.setOnClickListener{
            findNavController().navigate(R.id.action_menuFragment_to_comidaFragment)
        }
        val cardAyudas=view.findViewById<ImageView>(R.id.cardAyuda)
        cardAyudas.setOnClickListener{
            findNavController().navigate(R.id.action_menuFragment_to_ayudaFragment)
        }
        val cardCompra=view.findViewById<ImageView>(R.id.cardPedido)
        cardCompra.setOnClickListener{
            findNavController().navigate(R.id.action_menuFragment_to_pedidosFragment)
        }
        val cardFav=view.findViewById<ImageView>(R.id.cardFavoritos)
        cardFav.setOnClickListener{
            findNavController().navigate(R.id.action_menuFragment_to_favoritosFragment)
        }
        val cardPerfil=view.findViewById<ImageView>(R.id.cardMiCuenta)
        cardPerfil.setOnClickListener(){
            findNavController().navigate(R.id.action_menuFragment_to_perfilFragment)
        }
        val cardRutas=view.findViewById<ImageView>(R.id.cardContactános)
        cardRutas.setOnClickListener(){
            findNavController().navigate(R.id.action_menuFragment_to_rutaFragment)
        }
    }
}