package com.example.eltincho.Views.Ui.Fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eltincho.R
import com.example.eltincho.Views.Adapter.LibraryAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class comidaFragment : Fragment() {
    lateinit var recyclerEntrada:RecyclerView
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_comida, container, false)
        recyclerEntrada=view.findViewById(R.id.recycler_entrada)
        val adapter=LibraryAdapter()
        recyclerEntrada.layoutManager=LinearLayoutManager(context)
        recyclerEntrada.adapter=adapter
        return  view
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_navigation_toolbar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem):Boolean{

        return when (item.itemId){
            R.id.pedidos->{
                findNavController().navigate(R.id.action_comidaFragment_to_pedidosFragment)
                true
            }
            R.id.ayuda->{
                findNavController().navigate(R.id.action_comidaFragment_to_ayudaFragment)
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
    }
    override fun onViewCreated(view:View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        val button=view.findViewById<BottomNavigationView>(R.id.buttonNavigationMenu)
        button.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.home->findNavController().navigate(R.id.action_comidaFragment_to_menuFragment)
                R.id.contactanos->findNavController().navigate(R.id.action_comidaFragment_to_rutaFragment)
                R.id.perfil->findNavController().navigate(R.id.action_comidaFragment_to_perfilFragment)
                R.id.favoritos->findNavController().navigate(R.id.action_comidaFragment_to_favoritosFragment)
            }
        }
    }

}