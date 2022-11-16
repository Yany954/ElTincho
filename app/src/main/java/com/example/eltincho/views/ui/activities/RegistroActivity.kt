package com.example.eltincho.views.ui.activities

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

class RegistroActivity : AppCompatActivity() {
    lateinit var buttonregistro: Button
    lateinit var buttonlogin: Button
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        firebaseAuth= Firebase.auth
        buttonregistro=findViewById(R.id.registro)
        buttonlogin=findViewById(R.id.loginRegistro)
        val name=findViewById<EditText>(R.id.signUpName)
        val correo=findViewById<EditText>(R.id.signUpEmail)
        val celular=findViewById<EditText>(R.id.signUpPhone)
        val password=findViewById<EditText>(R.id.signUpPassword)
        buttonregistro.setOnClickListener{
            createUser(correo.text.toString(),password.text.toString())
        }
        buttonlogin.setOnClickListener{
            startActivity(Intent(this,LoginActivity::class.java))
        }

    }
    private fun createUser(email:String, password:String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                    Task ->if (Task.isSuccessful) {
                    startActivity(Intent(this, HomeActivity::class.java))
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