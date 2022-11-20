package com.example.eltincho.views.ui.Fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eltincho.R
import com.example.eltincho.models.entradas
import com.example.eltincho.viewModels.EntradaViewModel
import com.example.eltincho.views.adapter.EntradaAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.example.eltincho.views.adapter.EntradaAdapter.OnEntradaItemClickLitener


@Suppress("DEPRECATION")
class comidaFragment : Fragment(), OnEntradaItemClickLitener {
    val database:FirebaseFirestore=FirebaseFirestore.getInstance()
    lateinit var recyclerEntrada:RecyclerView
    lateinit var adapter: EntradaAdapter
    private val viewModel by lazy{ ViewModelProvider(this).get(EntradaViewModel::class.java)}
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_comida, container, false)
        recyclerEntrada=view.findViewById(R.id.recycler_entrada)
        adapter=EntradaAdapter(requireContext(),this)
        recyclerEntrada.layoutManager=LinearLayoutManager(context)
        recyclerEntrada.adapter=adapter
        observeData()
        return  view
    }
    fun observeData(){
        viewModel.fetchEntrada().observe(viewLifecycleOwner,Observer{
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
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
                findNavController().navigate(R.id.action_rutaFragment_to_loginActivity)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    override  fun onCreate (savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        firebaseAuth= Firebase.auth
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
    override fun onItemClick(entrada: entradas,position: Int){
        val titulo:String=entrada.title
        val precio:String=entrada.price
        val image:String?=entrada.imagen
        val dato= hashMapOf(
            "title" to titulo,
            "price" to precio,
            "imagen" to image
        )
        database.collection("compras")
            .document(titulo)
            .set(dato)
            .addOnSuccessListener {
                Toast.makeText(context,"Entrada añadida al carrito",Toast.LENGTH_SHORT).show()
            }
    }

    override fun onDeseosClick(entrada: entradas, position: Int) {
        val titulo:String=entrada.title
        val precio:String=entrada.price
        val image:String?=entrada.imagen
        val dato= hashMapOf(
            "title" to titulo,
            "price" to precio,
            "imagen" to image
        )
        database.collection("favoritos")
            .document(titulo)
            .set(dato)
            .addOnSuccessListener {
                Toast.makeText(context,"Entrada añadida al carrito",Toast.LENGTH_SHORT).show()
            }
    }

}