package com.example.eltincho.Views.Ui.Fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.eltincho.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class rutaFragment : Fragment() {
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ruta, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_navigation_toolbar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem):Boolean{

        return when (item.itemId){
            R.id.ayuda->{
                findNavController().navigate(R.id.action_rutaFragment_to_ayudaFragment)
                true
            }
            R.id.pedidos->{
                findNavController().navigate(R.id.action_rutaFragment_to_pedidosFragment)
                true
            }
            R.id.cerrar->{
                firebaseAuth.signOut()
                findNavController().navigate(R.id.action_rutaFragment_to_activity_login)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    override  fun onCreate (savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        firebaseAuth=Firebase.auth
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        val button= view.findViewById<BottomNavigationView>(R.id.buttonNavigationMenu)
        button.setOnNavigationItemReselectedListener {
            when (it.itemId){
                R.id.home->findNavController().navigate(R.id.action_rutaFragment_to_menuFragment)
                R.id.platillos->findNavController().navigate(R.id.action_rutaFragment_to_comidaFragment)
                R.id.perfil->findNavController().navigate(R.id.action_rutaFragment_to_perfilFragment)
                R.id.favoritos->findNavController().navigate(R.id.action_rutaFragment_to_favoritosFragment)
            }
        }
        (activity as AppCompatActivity).setSupportActionBar(view?.findViewById(R.id.actionbartoolbar))
    }
}