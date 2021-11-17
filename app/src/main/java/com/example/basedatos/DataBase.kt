package com.example.basedatos

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DataBase( context : Context , name : String , factory: SQLiteDatabase.CursorFactory? , version: Int) :
SQLiteOpenHelper(context,name,factory, version){

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table personas(codigo int primary key, nombre text, descripcion text)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("Drop table if exists personas")
    }

}