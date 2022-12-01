package com.utxicotepec.amacenamiento

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteConexion(context:Context, name:String, factory: SQLiteDatabase.CursorFactory?, version: Int): SQLiteOpenHelper(context,name,factory,version) {
    private val CREAR_TABLA_DOCENTE=("CREATE TABLE docentes(id INTEGER PRIMARY KEY AUTOINCREMENT" +
                                    ", nombre TEXT, apellidos TEXT,direccion TEXT, telefono TEXT," +
                                     " correo TEXT, contrasena TEXT)")
   private val ELIMINAR_TABLA_DOCENTE="DROP TABLE IF EXISTS docentes"



    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREAR_TABLA_DOCENTE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(ELIMINAR_TABLA_DOCENTE)
    }
}