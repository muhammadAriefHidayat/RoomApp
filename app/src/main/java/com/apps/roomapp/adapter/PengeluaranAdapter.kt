package com.apps.roomapp.adapter

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.apps.roomapp.*
import com.apps.roomapp.model.Pengeluaran
import com.apps.roomapp.room.PengeluaranDatabase
import kotlinx.android.synthetic.main.pengeluaran_item.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class PengeluaranAdapter(val listPengeluaran : List<Pengeluaran>) : RecyclerView.Adapter<PengeluaranAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.pengeluaran_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listPengeluaran.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tv_id.text = listPengeluaran[position].id.toString()
        holder.itemView.tv_nama_barang.text = listPengeluaran[position].barang
        holder.itemView.tv_harga.text = listPengeluaran[position].harga.toString()
        holder.itemView.tv_tanggal.text = listPengeluaran[position].tanggal.toString()

        holder.itemView.ivEdit.setOnClickListener {
            val intentKeEditActivity = Intent(it.context,
                EditPengeluaranActivity::class.java)

            intentKeEditActivity.putExtra(Extra,listPengeluaran[position])
            it.context.startActivity(intentKeEditActivity)
        }

        holder.itemView.ivDelete.setOnClickListener {
            AlertDialog.Builder(it.context).setPositiveButton("Ya") { p0, p1 ->
                val mDb = PengeluaranDatabase.getInstance(holder.itemView.context)

                GlobalScope.async {
                    val result = mDb?.pengeluaranDao()?.deletePengeluaran(listPengeluaran[position])

                    (holder.itemView.context as MainActivity).runOnUiThread {
                        if (result!=0){
                            Toast.makeText(it.context,"Data ${listPengeluaran[position].barang} berhasil dihapus",Toast.LENGTH_LONG).show()
                        }else{
                            Toast.makeText(it.context,"Data ${listPengeluaran[position].barang} Gagal dihapus",Toast.LENGTH_LONG).show()
                        }
                    }

                    (holder.itemView.context as MainActivity).fetchData()
                }
            }.setNegativeButton("Tidak"
            ) { p0, p1 ->
                p0.dismiss()
            }
                .setMessage("Apakah Anda Yakin ingin menghapus data ${listPengeluaran[position].barang}").setTitle("Konfirmasi Hapus").create().show()
        }
    }
}