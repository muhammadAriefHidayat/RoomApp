package com.apps.roomapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.apps.roomapp.model.Pengeluaran
import com.apps.roomapp.room.PengeluaranDatabase
import kotlinx.android.synthetic.main.activity_add_pengeluaran.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class EditPengeluaranActivity : AppCompatActivity() {

    var mDb: PengeluaranDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_pengeluaran)

        mDb = PengeluaranDatabase.getInstance(this)

        val objectPengeluaran = intent.getParcelableExtra<Pengeluaran>(Extra)

        edt_barang.setText(objectPengeluaran?.barang)
        edt_harga.setText(objectPengeluaran?.harga.toString())
        btn_tanggal.text = objectPengeluaran?.tanggal.toString()

        btn_simpan.setOnClickListener {
            objectPengeluaran?.barang = edt_barang.text.toString()
            objectPengeluaran?.harga = edt_harga.text.toString().toInt()

            GlobalScope.async {
                val result = mDb?.pengeluaranDao()?.updatePengeluaran(objectPengeluaran!!)

                runOnUiThread {
                    if(result!=0){
                        Toast.makeText(this@EditPengeluaranActivity,"Sukses mengubah ${objectPengeluaran?.barang}", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this@EditPengeluaranActivity,"Gagal mengubah ${objectPengeluaran?.harga}", Toast.LENGTH_LONG).show()
                    }

                    finish()
                }
            }
        }
    }
}