package com.example.basedatos

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //declaracion de variables
        val name = findViewById<EditText>(R.id.edt1)
        val description = findViewById<EditText>(R.id.edtm)
        val boton1 = findViewById<Button>(R.id.bt1)
        val boton2 = findViewById<Button>(R.id.bt2)
        val boton3 = findViewById<Button>(R.id.bt3)
        val boton4 = findViewById<Button>(R.id.bt4)
        //insercion
        boton1.setOnClickListener {
            val admin = DataBase(this,"prueba",null,1)
            val db = admin.writableDatabase
            val record = ContentValues()
           record.put("nombre",name.text.toString())
            record.put("descripcion",description.text.toString())
            db.insert("personas",null,record)
            db.close()
            name.setText("Nombre")
            description.setText("SN")
            Toast.makeText(this, "Se cargaron los datos de la persona", Toast.LENGTH_SHORT).show()
        }
    //consulta
        boton3.setOnClickListener {
            val admin = DataBase(this, "prueba", null, 1)
            val db = admin.writableDatabase
            if (db == null) {
                Toast.makeText(this, "No DB", Toast.LENGTH_SHORT).show()
            }
            else {
                  val sql : String = "select descripcion from personas where nombre ='${name.text.toString()}'"
                  val row = db.rawQuery(sql, null)
                if (row.moveToFirst()) {
                    val dsc = row.getString(row.getColumnIndex("descripcion"))
                    description.setText(dsc)
                }
                else {
                    Toast.makeText(this, "No hay registros...", Toast.LENGTH_SHORT).show()
                    db.close()
                }
            }
        }
        //Eliminar
        boton2.setOnClickListener {
            val admin = DataBase(this, "prueba", null, 1)
            val db = admin.writableDatabase
            if (db == null) {
                Toast.makeText(this, "No DB", Toast.LENGTH_SHORT).show()
            }
            else {
                val cnt = db.delete("personas","nombre ='${name.text.toString()}'", null)
                db.close()
                name.setText("Nombre")
                description.setText("SN")
                if( cnt == 1)
                    Toast.makeText(this, "Se borró el registro con éxito", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(this, "No existe el registro", Toast.LENGTH_SHORT).show()
            }

        }
        //Actualizar
        boton4.setOnClickListener {
            val admin = DataBase(this, "prueba", null, 1)
            val db = admin.writableDatabase
            val record = ContentValues()
            record.put("descripcion",description.text.toString())
            val cant = db.update("personas",record,"nombre ='${name.text.toString()}'", null)
            db.close()
            Toast.makeText(this,"${cant}",Toast.LENGTH_SHORT).show()
            if( cant == 1)
                Toast.makeText(this, "Se actualizó el registro con éxito", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "No existe el registro", Toast.LENGTH_SHORT).show()
        }
    }
}