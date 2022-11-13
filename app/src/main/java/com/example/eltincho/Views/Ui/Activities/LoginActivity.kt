package com.example.eltincho.Views.Ui.Activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.eltincho.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListener: AuthStateListener
    @SuppressLint("MissingInflateId")
    lateinit var registrobutton:TextView
    lateinit var iniciobutton:Button
    lateinit var recuperarbutton: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val email:EditText=findViewById(R.id.loginEmail)
        val password=findViewById<EditText>(R.id.loginPassword)
        firebaseAuth=Firebase.auth
        iniciobutton.setOnClickListener{
            login(email.text.toString(),password.text.toString())
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
                    Toast.makeText(baseContext,"error",Toast.LENGTH_SHORT).show()
                }
            }
    }
}