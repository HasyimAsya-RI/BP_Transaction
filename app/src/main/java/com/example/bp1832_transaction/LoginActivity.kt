package com.example.bp1832_transaction

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate( savedInstanceState )
        setContentView( R.layout.activity_login )

        // Hide Title Bar
        getSupportActionBar()?.hide()

        // Instance Text
        val txtUsername: EditText = findViewById( R.id.editTextEmail )
        val txtPassword: EditText = findViewById( R.id.editTextPassword )

        // Instance Button Login
        val btnLogin:    Button   = findViewById( R.id.buttonLogin )

        // Event Button Login
        btnLogin.setOnClickListener {
            // Object Class DatabaseHelper
            val databaseHelper = DatabaseHelper( this )

//            // Check Data
//            val data: String = databaseHelper.checkData( "hasyim@students.amikom.ac.id" )
//            Toast.makeText( this@LoginActivity, "Hasil: " + data, Toast.LENGTH_SHORT ).show()
//
//            // Insert Data
//            databaseHelper.addAccount(
//                "hasyim@students.amikom.ac.id",
//                "Hasyim Asy'ari",
//                "Cashier",
//                "hasy1m"
//            )

            val email    = txtUsername.text.toString().trim()
            val password = txtPassword.text.toString().trim()

            // Check Login
            val result: Boolean = databaseHelper.checkLogin( email, password )
            if( result == true ) {
                Toast.makeText( this@LoginActivity, "Login berhasil.", Toast.LENGTH_SHORT ).show()
                val intentLogin = Intent( this@LoginActivity, MainActivity::class.java )
                startActivity( intentLogin )
            }
            else {
                Toast.makeText( this@LoginActivity, "Login gagal! Silakan coba lagi.", Toast.LENGTH_SHORT ).show()
            }
        }
    }

    fun register( view: View ) {
        val intent = android.content.Intent( this, RegisterActivity::class.java )
        startActivity( intent )
    }
}