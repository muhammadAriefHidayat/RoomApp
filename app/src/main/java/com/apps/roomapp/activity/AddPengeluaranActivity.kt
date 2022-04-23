package com.apps.roomapp.activity

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.apps.roomapp.R
import com.apps.roomapp.model.Pengeluaran
import com.apps.roomapp.room.PengeluaranDatabase
import kotlinx.android.synthetic.main.activity_add_pengeluaran.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.text.SimpleDateFormat
import java.util.*

class AddPengeluaranActivity : AppCompatActivity() {
    var mDb :PengeluaranDatabase? = null
    var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_pengeluaran)

        mDb = PengeluaranDatabase.getInstance(this)

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "MM/dd/yyyy" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                btn_tanggal!!.text = sdf.format(cal.time)
            }


        btn_tanggal.setOnClickListener {
            DatePickerDialog(this,
                dateSetListener,
                // set DatePickerDialog to point to today's date when it loads up
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        btn_simpan.setOnClickListener {
            if (btn_tanggal.text != "Tanggal"){
                val objectPengeluaran = Pengeluaran(
                    null,
                    btn_tanggal.text.toString(),
                    edt_barang.text.toString(),
                    edt_harga.text.toString().toInt(),
                )

                GlobalScope.async {
                    val result = mDb?.pengeluaranDao()?.insertPengeluaran(objectPengeluaran)
                    runOnUiThread {
                        if(result != 0.toLong() ){
                            //sukses
                            Toast.makeText(this@AddPengeluaranActivity,"Sukses menambahkan ${objectPengeluaran.barang}",
                                Toast.LENGTH_LONG).show()
                        }else{
                            //gagal
                            Toast.makeText(this@AddPengeluaranActivity,"Gagal menambahkan ${objectPengeluaran.barang}",
                                Toast.LENGTH_LONG).show()
                        }
                        finish()
                    }
                }
            }
        }


    }
}