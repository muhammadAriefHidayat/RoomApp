package com.apps.roomapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.apps.roomapp.R
import com.apps.roomapp.adapter.PengeluaranAdapter
import com.apps.roomapp.room.PengeluaranDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var mDB : PengeluaranDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mDB = PengeluaranDatabase.getInstance(this)

        rv_main.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

        fetchData()
        
        fabAdd.setOnClickListener {
            val keActivityAdd = Intent(this, AddPengeluaranActivity::class.java)
            startActivity(keActivityAdd)
        }
    }


    override fun onResume() {
        super.onResume()
        fetchData()
    }

    fun fetchData(){
        GlobalScope.launch {
            val listPengeluaran = mDB?.pengeluaranDao()?.getAllPengeluaran()

            runOnUiThread{
                listPengeluaran?.let {
                    val adapter = PengeluaranAdapter(it)
                    rv_main.adapter = adapter
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        PengeluaranDatabase.destroyInstance()
    }
    
}