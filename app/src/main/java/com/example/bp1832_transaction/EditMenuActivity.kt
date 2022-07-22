package com.example.bp1832_transaction

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.bp1832_transaction.model.MenuModel

class EditMenuActivity : AppCompatActivity() {
    lateinit var image: ImageView
    companion object {
        val IMAGE_REQUEST_CODE = 100
        // Set Variabel untuk Menampung Data yang Terpilih
        var idMakanan    = 1
        var namaMakanan  = "tes"
        var hargaMakanan = 90000
        //        var gambarMakanan = R.drawable.logo_pizza
        lateinit var gambarMakanan: Bitmap
    }

    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate( savedInstanceState )
        setContentView( R.layout.activity_edit_menu )

        // Hide Title Bar
        getSupportActionBar()?.hide()

        // Instance
        image                       = findViewById( R.id.editImageMenu )
        val textId:        TextView = findViewById( R.id.editId )
        val textName:      EditText = findViewById( R.id.editName )
        val textPrice:     EditText = findViewById( R.id.editPrice )
        val btnAddImage:   Button   = findViewById( R.id.buttonEditImage )
        val btnUpdateMenu: Button   = findViewById( R.id.buttonSaveEditMenu )

        // Input Data
        textId.text = idMakanan.toString()
        textName.setText( namaMakanan )
        textPrice.setText( hargaMakanan.toString() )
        image.setImageBitmap( gambarMakanan )

        // Event Saat Button Add (+) Diklik
        btnAddImage.setOnClickListener {
            pickImageGalery()
        }

        // Event Saat Button Save Diklik
        btnUpdateMenu.setOnClickListener {
            // Object Class DatabaseHelper
            val databaseHelper = DatabaseHelper( this )

            val id:             Int            = textId.text.toString().toInt()
            val name:           String         = textName.text.toString().trim()
            val price:          Int            = textPrice.text.toString().toInt()
            val bitmapDrawable: BitmapDrawable = image.drawable as BitmapDrawable
            val bitmap:         Bitmap         = bitmapDrawable.bitmap

            val menuModel = MenuModel( id, name, price, bitmap )
            databaseHelper.updateMenu( menuModel )

            val intent    = Intent( this, MainActivity::class.java )
            startActivity( intent )
        }
    }

    private fun pickImageGalery() {
        val intent  = Intent( Intent.ACTION_PICK )
        intent.type = "image/"
        startActivityForResult( intent, IMAGE_REQUEST_CODE )
    }

    override fun onActivityResult( requestCode:Int, resultCode:Int, data: Intent? ) {
        super.onActivityResult( requestCode, resultCode, data )
        if( requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK ) {
            image.setImageURI( data?.data )
        }
    }
}
