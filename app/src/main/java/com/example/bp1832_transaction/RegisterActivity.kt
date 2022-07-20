package com.example.bp1832_transaction

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {
    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate( savedInstanceState )
        setContentView( R.layout.activity_register )

        // Hide Title Bar
        getSupportActionBar()?.hide()

        // Instance Text
        val txtEmail:    EditText = findViewById( R.id.registerEmail )
        val txtName:     EditText = findViewById( R.id.registerPersonName )
        val txtLevel:    EditText = findViewById( R.id.registerLevel )
        val txtPassword: EditText = findViewById( R.id.registerPassword )

        // Instance Button Register
        val btnRegister: Button   = findViewById( R.id.buttonRegisterAccount )

        // Event Button Register
        btnRegister.setOnClickListener {
            // Object Class DatabaseHelper
            val databaseHelper = DatabaseHelper( this )

            // Declare Data
            val email:    String = txtEmail.text.toString().trim()
            val name:     String = txtName.text.toString().trim()
            val level:    String = txtLevel.text.toString().trim()
            val password: String = txtPassword.text.toString().trim()

            // Check Data (Email sudah terdaftar atau belum)
            val data: String = databaseHelper.checkData( email )
            // Jika Belum Terdaftar
            if( data == "" ) {
                // Insert Data
                databaseHelper.addAccount( email, name, level, password )

                // Show LoginActivity
                val intentLogin = Intent( this@RegisterActivity, LoginActivity::class.java )
                startActivity( intentLogin )
            }
            // Jika Email Telah Terdaftar
            else {
                Toast.makeText( this@RegisterActivity, "Register gagal! " + "Email Anda sudah terdaftar.", Toast.LENGTH_SHORT ).show()
            }
        }
    }
}