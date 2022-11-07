package com.example.eltincho.Views.Ui.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.eltincho.R

class RecuperarActivity : AppCompatActivity() {
    lateinit var restaurarbutton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar)
        restaurarbutton=findViewById(R.id.Restaurarbutton)
        restaurarbutton.setOnClickListener{
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }
}