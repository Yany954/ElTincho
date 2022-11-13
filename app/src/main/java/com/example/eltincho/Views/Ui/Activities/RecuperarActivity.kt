package com.example.eltincho.Views.Ui.Activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.eltincho.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RecuperarActivity : AppCompatActivity() {
    lateinit var restaurarbutton: Button
    private lateinit var firebaseAuth: FirebaseAuth
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar)
        firebaseAuth= Firebase.auth
    }
    private fun cambiocontrasena(email:String){
        firebaseAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener(this){
                task->if(task.isSuccessful){
                    Toast.makeText(baseContext,"Correo de cambio de contraseña enviado",
                        Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,LoginActivity::class.java))
                }else{
                    Toast.makeText(baseContext,"Error, no se completo cambio",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}