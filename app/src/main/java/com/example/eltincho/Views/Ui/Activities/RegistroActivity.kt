package com.example.eltincho.Views.Ui.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.eltincho.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.security.AuthProvider

class RegistroActivity : AppCompatActivity() {
    lateinit var buttonregistro: Button
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        firebaseAuth= Firebase.auth
        buttonregistro=findViewById(R.id.BotonRegistro)
        //val name=findViewById<EditText>(R.id.signUpName)
        val correo=findViewById<EditText>(R.id.signUpEmail)
        //val celular=findViewById<EditText>(R.id.signUpPhone)
        val password=findViewById<EditText>(R.id.signUpPassword)
        buttonregistro.setOnClickListener {
            createUser(correo.text.toString(),password.text.toString())
        }

    }
    private fun createUser(email:String, password:String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                    Task ->if (Task.isSuccessful) {
                    startActivity(Intent(this, LoginActivity::class.java))
                } else {
                    Toast.makeText(
                        applicationContext,
                        "No se puede registrar este usuario",
                        Toast.LENGTH_LONG
                    ).show()

                }
            }
    }
}