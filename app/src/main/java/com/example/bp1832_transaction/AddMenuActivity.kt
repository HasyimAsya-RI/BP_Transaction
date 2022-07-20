package com.example.bp1832_transaction

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.example.bp1832_transaction.model.MenuModel

class AddMenuActivity : AppCompatActivity() {
    lateinit var image: ImageView
    companion object {
        val IMAGE_REQUEST_CODE = 100
    }

    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate( savedInstanceState )
        setContentView( R.layout.activity_add_menu )

        // Hide Title Bar
        getSupportActionBar()?.hide()

        // Instance
        image                     = findViewById( R.id.imageMenu )
        val textId:      EditText = findViewById( R.id.menuId )
        val textName:    EditText = findViewById( R.id.menuName )
        val textPrice:   EditText = findViewById( R.id.menuPrice )
        val btnAddImage: Button   = findViewById( R.id.buttonAddImage )
        val btnSaveMenu: Button   = findViewById( R.id.buttonSaveMenu )

        // Event Saat Button Add (+) Diklik
        btnAddImage.setOnClickListener {
            pickImageGalery()
        }

        // Event Saat Button Save Diklik
        btnSaveMenu.setOnClickListener {
            // Object Class DatabaseHelper
            val databaseHelper = DatabaseHelper( this )

            val id:             Int            = textId.text.toString().toInt()
            val name:           String         = textName.text.toString().trim()
            val price:          Int            = textPrice.text.toString().toInt()
            val bitmapDrawable: BitmapDrawable = image.drawable as BitmapDrawable
            val bitmap:         Bitmap         = bitmapDrawable.bitmap

            val menuModel = MenuModel( id, name, price, bitmap )
            databaseHelper.addMenu( menuModel )

            val intent    = Intent( this, MainActivity::class.java )
            startActivity( intent )
        }
    }

    private fun pickImageGalery() {
        val intent  = Intent( Intent.ACTION_PICK )
        intent.type = "image/"
        startActivityForResult( intent, IMAGE_REQUEST_CODE )
    }

    override fun onActivityResult( requestCode:Int, resultCode:Int, data:Intent? ) {
        super.onActivityResult( requestCode, resultCode, data )
        if( requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK ) {
            image.setImageURI( data?.data )
        }
    }
}