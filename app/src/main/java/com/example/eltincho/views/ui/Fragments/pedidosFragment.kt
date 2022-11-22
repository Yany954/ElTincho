package com.example.eltincho.views.ui.Fragments

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eltincho.R
import com.example.eltincho.models.compras
import com.example.eltincho.viewModels.ComprasViewModel
import com.example.eltincho.views.adapter.ComprasAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.example.eltincho.views.adapter.ComprasAdapter.OnCompraItemClickLitener

@Suppress("DEPRECATION")
class pedidosFragment : Fragment(), OnCompraItemClickLitener {
    lateinit var recyclerComp: RecyclerView
    lateinit var adapter: ComprasAdapter
    val db:FirebaseFirestore=FirebaseFirestore.getInstance()
    lateinit var precioT:TextView
    lateinit var bottoncompra:Button
    private val viewModel by lazy{ViewModelProvider(this).get(ComprasViewModel::class.java)}
    lateinit var firebaseAuth:FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_pedidos, container, false)
        precioT=view.findViewById(R.id.preciototal)
        bottoncompra=view.findViewById(R.id.botoncomprar)
        recyclerComp=view.findViewById(R.id.recyclerviewcompras)
        adapter= ComprasAdapter(requireContext(),this)
        recyclerComp.layoutManager=LinearLayoutManager(context)
        recyclerComp.adapter=adapter
        observeData()
        preciototal()
        bottoncompra.setOnClickListener{
            realizarcompra()
        }
        return view
    }
    private  fun observeData(){
        viewModel.fetchComprasData().observe(viewLifecycleOwner,{
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }
    private fun preciototal(){
        db.collection("compras")
            .get()
            .addOnSuccessListener {
                result->
                val preciouni= mutableListOf<String>()
                for(document in result){
                    val precio=document["precio"].toString()
                    preciouni.add(precio)
                }
                val preciototal=preciouni.mapNotNull{it.toIntOrNull()}.sum()
                precioT.setText(Integer.toString(preciototal))
            }
    }
    private fun realizarcompra(){
        val builder=AlertDialog.Builder(requireContext())
        builder.setTitle("CompraElTincho")
        builder.setMessage("¿Desea realizar esta compra?")
        builder.setPositiveButton("Aceptar"){
            dialog,which->
            findNavController().navigate(R.id.action_pedidosFragment_to_menuFragment)
        }
        builder.setNegativeButton("Cancelar",null)
        builder.show()
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_navigation_toolbar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem):Boolean{

        return when (item.itemId){
            R.id.ayuda->{
                findNavController().navigate(R.id.action_pedidosFragment_to_ayudaFragment)
                true
            }
            R.id.cerrar->{
                firebaseAuth.signOut()
                findNavController().navigate(R.id.action_pedidosFragment_to_loginActivity)
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
                R.id.home->findNavController().navigate(R.id.action_pedidosFragment_to_menuFragment)
                R.id.platillos->findNavController().navigate(R.id.action_pedidosFragment_to_comidaFragment)
                R.id.perfil->findNavController().navigate(R.id.action_pedidosFragment_to_perfilFragment)
                R.id.favoritos->findNavController().navigate(R.id.action_pedidosFragment_to_favoritosFragment)
                R.id.contactanos->findNavController().navigate(R.id.action_pedidosFragment_to_rutaFragment)
            }
        }
        (activity as AppCompatActivity).setSupportActionBar(view?.findViewById(R.id.actionbartoolbar))
    }

    override fun onItemClick(compra: compras, position: Int) {
        db.collection("compras")
            .document(compra.titulo)
            .delete()
    }
}