package com.example.bp1832_transaction

import android.content.Intent
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bp1832_transaction.model.MenuModel

class MakananAdapter( private val list: ArrayList<MenuModel> ):
    RecyclerView.Adapter<MakananAdapter.MakananViewHolder>() {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int ): MakananViewHolder {
        val layoutInflater = LayoutInflater.from( parent?.context )
        val cellForRow     = layoutInflater.inflate( R.layout.cardview_makanan, parent, false )

        return MakananViewHolder( cellForRow )
    }

    override fun onBindViewHolder( holder: MakananViewHolder, position: Int ) {
        holder.bind( list[position] )
    }

    /** Untuk Menentukan Palette yang Digunakan untuk Menampilkan Data **/
    inner class MakananViewHolder( v: View ): RecyclerView.ViewHolder( v ) {
        val textId:       TextView
        val textNama:     TextView
        val textHarga:    TextView
        val imageMenu:    ImageView
        val buttonEdit:   Button
        val buttonDelete: Button

        val context = v.context

        init {
            textId       = v.findViewById( R.id.textIdMakanan )
            textNama     = v.findViewById( R.id.textNamaMakanan )
            textHarga    = v.findViewById( R.id.textHargaMakanan )
            imageMenu    = v.findViewById( R.id.imageMakanan )
            buttonEdit   = v.findViewById( R.id.buttonEditMakanan )
            buttonDelete = v.findViewById( R.id.buttonHapusMakanan )

//            buttonEdit.setOnClickListener {
//                EditMenuActivity.idMakanan     = textId.text.toString().toInt()
//                Toast.makeText( context, EditMenuActivity.idMakanan.toString(), Toast.LENGTH_SHORT )
//                EditMenuActivity.namaMakanan   = textNama.text.toString()
//                EditMenuActivity.hargaMakanan  = textHarga.text.toString().toInt()
//                EditMenuActivity.gambarMakanan = imageMenu.drawable.toBitmap( 150, 150 )
//
//                val editIntent = Intent( context, EditMenuActivity::class.java )
//                context.startActivity( editIntent )

            buttonDelete.setOnClickListener {
                val databaseHelper = DatabaseHelper( context )
                databaseHelper.deleteMenu( textId.text.toString().toInt() )

                val intent = Intent( context, MainActivity::class.java )
                context.startActivity( intent )
            }
        }

        fun bind( data: MenuModel ) {
            val id:     Int    = data.id
            val nama:   String = data.name
            val harga:  Int    = data.price
            val gambar: Bitmap = data.image

            textId.text    = id.toString()
            textNama.text  = nama
            textHarga.text = harga.toString()
            imageMenu.setImageBitmap( gambar )
        }
    }
}