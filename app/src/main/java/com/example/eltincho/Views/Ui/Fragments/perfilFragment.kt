package com.example.eltincho.Views.Ui.Fragments

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.eltincho.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth


class perfilFragment : Fragment() {
    lateinit var firebaseAuth:FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_perfil, container, false)
        val  nombreCompleto=view.findViewById<EditText>(R.id.nombrecompleto)
        val correoCompleto=view.findViewById<EditText>(R.id.correoelectronico)
        val celular=view.findViewById<EditText>(R.id.celularcompleto)
        val btneditnombre=view.findViewById<ImageButton>(R.id.nombreedit)
        val btneditcorrreo=view.findViewById<ImageButton>(R.id.correoedit)
        val btneditcelular=view.findViewById<ImageButton>(R.id.celularedit)
        nombreCompleto.isEnabled=false
        correoCompleto.isEnabled=false
        celular.isEnabled=false
        btneditnombre.setOnClickListener{
            if(nombreCompleto.isEnabled==false){
                nombreCompleto.isEnabled=true
            }else if(nombreCompleto.isEnabled==true){
                nombreCompleto.isEnabled=false
            }
        }
        btneditcorrreo.setOnClickListener{
            if(correoCompleto.isEnabled==false){
                correoCompleto.isEnabled=true
            }else if(correoCompleto.isEnabled==true){
                correoCompleto.isEnabled=false
            }
        }
        btneditcelular.setOnClickListener{
            if(celular.isEnabled==false){
                celular.isEnabled=true
            }else if(celular.isEnabled==true){
                celular.isEnabled=false
            }
        }
        return inflater.inflate(R.layout.fragment_perfil, container, false)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_navigation_toolbar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem):Boolean{

        return when (item.itemId){
            R.id.ayuda->{
                findNavController().navigate(R.id.action_perfilFragment_to_ayudaFragment)
                true
            }
            R.id.pedidos->{
                findNavController().navigate(R.id.action_perfilFragment_to_pedidosFragment)
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
                R.id.home->findNavController().navigate(R.id.action_perfilFragment_to_menuFragment)
                R.id.platillos->findNavController().navigate(R.id.action_perfilFragment_to_comidaFragment)
                R.id.contactanos->findNavController().navigate(R.id.action_perfilFragment_to_rutaFragment)
                R.id.favoritos->findNavController().navigate(R.id.action_perfilFragment_to_favoritosFragment)
            }
        }
        val btmcamara=view.findViewById<ImageButton>(R.id.btncamara)
        btmcamara.setOnClickListener{
            val intent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 123)
        }
        val btmgaleria=view.findViewById<ImageButton>(R.id.btngaleria)
        btmgaleria.setOnClickListener{
            val intent=Intent(Intent.ACTION_PICK)
            intent.type="image/*"
            startActivityForResult(intent, 456)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode:Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        val imageView=view?.findViewById<ImageView>(R.id.fotoperfil)
        if(requestCode==123){
            var bitmap=data?.extras?.get("data") as Bitmap
            imageView?.setImageBitmap(bitmap)
        } else if (requestCode==456){
            imageView?.setImageURI(data?.data)
        }
    }

}