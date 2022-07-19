package com.example.application_gestion_films

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = Firebase.auth
        val btnlogin = findViewById<Button>(R.id.btn_login)
        btnlogin.setOnClickListener { login(it) }
    }

    private fun login(view: View){
        val email = findViewById<EditText>(R.id.login_email).text.toString()
        val password = findViewById<EditText>(R.id.login_password).text.toString()

        if (email == "" && password == "") {
            Toast.makeText(this, "Veuillez remplir les différents champs pour vous connecter", Toast.LENGTH_SHORT).show()
        } else if (email == "" && password != "") {
            Toast.makeText(this, "Veuillez saisir votre adresse mail", Toast.LENGTH_SHORT).show()
        } else if (email != "" && password == "") {
            Toast.makeText(this, "Veuillez saisir un mot de passe", Toast.LENGTH_SHORT).show()
        } else {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, HomeActivity::class.java)
                    Toast.makeText(this, "Connecté avec succès !", Toast.LENGTH_SHORT).show()
                    startActivity(intent)
                    finish()
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(this, "Email ou mot de passe incorrect", Toast.LENGTH_SHORT).show()
            }
        }
    }


/*    fun goToLogin(view: View){
        val intent= Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }*/
}