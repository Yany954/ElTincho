package com.example.eltincho.views.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.eltincho.R
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

@Suppress("DEPRECATION")
class LoginActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var dbReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    //private lateinit var authStateListener: AuthStateListener
    @SuppressLint("MissingInflateId")
    lateinit var registrobutton:TextView
    lateinit var iniciobutton:Button
    lateinit var recuperarbutton: TextView
    lateinit var pruebabutton:Button
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        FirebaseApp.initializeApp(this)

        val email: EditText = findViewById(R.id.loginEmail)
        val password = findViewById<EditText>(R.id.loginPassword)
        iniciobutton = findViewById(R.id.BotonInicio)
        recuperarbutton = findViewById(R.id.BotonRecuperar)
        registrobutton = findViewById(R.id.BotonRegistroLogin)
        pruebabutton = findViewById(R.id.masterKey)
        firebaseAuth = Firebase.auth
        database = FirebaseDatabase.getInstance()
        dbReference = database.reference.child("User")


        iniciobutton.setOnClickListener {
            login(email.text.toString(), password.text.toString())
        }
        recuperarbutton.setOnClickListener {
            startActivity(Intent(this, RecuperarActivity::class.java))
        }
        registrobutton.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }
        pruebabutton.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

    private fun login(email:String,password:String){
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){ task->
                if(task.isSuccessful){
                    val user=firebaseAuth.currentUser
                    Toast.makeText(baseContext,user?.uid.toString(),Toast.LENGTH_SHORT).show()
                    val i=Intent(this, HomeActivity::class.java)
                    startActivity(i)
                } else{
                    Toast.makeText(baseContext,"Revise que los datos sean correctos",Toast.LENGTH_SHORT).show()
                }
            }
    }
}