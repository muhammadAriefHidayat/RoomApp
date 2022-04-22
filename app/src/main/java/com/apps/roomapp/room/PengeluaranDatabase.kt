package com.apps.roomapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.apps.roomapp.model.Pengeluaran

@Database(entities = [Pengeluaran::class],version = 1)
abstract class PengeluaranDatabase: RoomDatabase() {
    abstract fun pengeluaranDao(): PengeluaranDao

    companion object{
        private var INSTANCE: PengeluaranDatabase? = null

        fun getInstance(context: Context): PengeluaranDatabase? {
            if(INSTANCE == null){
                synchronized(PengeluaranDatabase::class){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        PengeluaranDatabase::class.java, "Pengeluaran.db"
                    ).build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}