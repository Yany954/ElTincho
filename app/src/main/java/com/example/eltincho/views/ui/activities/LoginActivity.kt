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
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

@Suppress("DEPRECATION")
class LoginActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var firebaseAuth2: FirebaseAuth
    private lateinit var dbReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    //private lateinit var authStateListener: AuthStateListener
    @SuppressLint("MissingInflateId")
    lateinit var registrobutton:TextView
    lateinit var iniciobutton:Button
    lateinit var recuperarbutton: TextView
    lateinit var pruebabutton:Button
    lateinit var googlebutton:Button
    lateinit var mGoogleSignInClient: GoogleSignInClient
    val Req_Code: Int = 123
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        FirebaseApp.initializeApp(this)

        val email:EditText=findViewById(R.id.loginEmail)
        val password=findViewById<EditText>(R.id.loginPassword)
        iniciobutton=findViewById(R.id.BotonInicio)
        recuperarbutton=findViewById(R.id.BotonRecuperar)
        registrobutton=findViewById(R.id.BotonRegistroLogin)
        pruebabutton=findViewById(R.id.masterKey)
        googlebutton=findViewById(R.id.Signin)
        firebaseAuth=Firebase.auth
        database=FirebaseDatabase.getInstance()
        dbReference=database.reference.child("User")


        iniciobutton.setOnClickListener{
            login(email.text.toString(),password.text.toString())
        }
        recuperarbutton.setOnClickListener{
            startActivity(Intent(this,RecuperarActivity::class.java))
        }
        registrobutton.setOnClickListener{
            startActivity(Intent(this,RegistroActivity::class.java ))
        }
        pruebabutton.setOnClickListener{
            startActivity(Intent(this,HomeActivity::class.java))
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        firebaseAuth2 = FirebaseAuth.getInstance()

        googlebutton.setOnClickListener { view: View? ->
            Toast.makeText(this, "Logging In", Toast.LENGTH_SHORT).show()
            signInGoogle()
        }

    }
    private fun signInGoogle() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, Req_Code)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Req_Code) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult(task)
        }
    }
    private fun handleResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            if (account != null) {
                UpdateUI(account)
            }
        } catch (e: ApiException) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }
    private fun UpdateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user=firebaseAuth2.currentUser
                val userdb=dbReference.child(user?.uid.toString())
                userdb.child("email").setValue(this, account.email.toString())
                userdb.child("name").setValue(this, account.displayName.toString())
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
    override fun onStart() {
        super.onStart()
        if (GoogleSignIn.getLastSignedInAccount(this) != null) {
            startActivity( Intent(this, HomeActivity::class.java  ))
            finish()
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