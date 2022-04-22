package com.apps.roomapp.room

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.apps.roomapp.model.Pengeluaran

@Dao
interface PengeluaranDao {
    @Query("SELECT * from Pengeluaran")
    fun getAllPengeluaran(): List<Pengeluaran>

    @Insert(onConflict = REPLACE)
    fun insertPengeluaran(pengeluaran: Pengeluaran):Long

    @Update
    fun updatePengeluaran(pengeluaran: Pengeluaran):Int

    @Delete
    fun deletePengeluaran(pengeluaran: Pengeluaran):Int
}