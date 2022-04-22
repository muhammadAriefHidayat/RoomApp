package com.apps.roomapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Pengeluaran(
    @PrimaryKey(autoGenerate = true) var id:Int?,
    @ColumnInfo(name = "tanggal") var tanggal:String,
    @ColumnInfo(name = "barang") var barang:String,
    @ColumnInfo(name = "harga") var harga:Int,
)