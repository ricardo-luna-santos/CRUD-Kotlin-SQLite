package com.utxicotepec.amacenamiento

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val nombre=findViewById<EditText>(R.id.txtnombre)
        val apellidos=findViewById<EditText>(R.id.txtapellidos)
        val direccion=findViewById<EditText>(R.id.txtdireccion)
        val telefono=findViewById<EditText>(R.id.txttelefono)
        val correo=findViewById<EditText>(R.id.txtcorreo)
        val contrasena=findViewById<EditText>(R.id.txtcontrasena)
        val contrasena2=findViewById<EditText>(R.id.txtcontrasena2)

        val guardar=findViewById<ImageView>(R.id.imgguardar)
        val buscar=findViewById<ImageView>(R.id.imgbuscar)
        val editar=findViewById<ImageView>(R.id.imgeditar)
        val eliminar=findViewById<ImageView>(R.id.imgeliminar)
        val reporte=findViewById<ImageView>(R.id.imgreporte)

        reporte.setOnClickListener {
            val reportedocente= Intent(this,ReporteDocente::class.java)
            startActivity(reportedocente)
        }
        guardar.setOnClickListener {
            if(nombre.text.isEmpty() || apellidos.text.isEmpty() || direccion.text.isEmpty() ||
                telefono.text.isEmpty() || correo.text.isEmpty() || contrasena.text.isEmpty() ||
                contrasena2.text.isEmpty()){

                Toast.makeText(this,"Algun campo no fue llenado correctamente", Toast.LENGTH_LONG).show()

            }else{
                if(contrasena.getText().toString().equals(contrasena2.getText().toString()))
                {
                    val admin = SQLiteConexion(this,"cursos",null,1)
                    val bd=admin.writableDatabase
                    val registro=ContentValues()
                    registro.put("nombre",nombre.getText().toString())
                    registro.put("apellidos",apellidos.getText().toString())
                    registro.put("direccion",direccion.getText().toString())
                    registro.put("telefono",telefono.getText().toString())
                    registro.put("correo",correo.getText().toString())
                    registro.put("contrasena",contrasena.getText().toString())
                    bd.insert("docentes",null,registro)
                    bd.close()
                    //LIMPIAR CAMOS
                    nombre.setText("")
                    apellidos.setText("")
                    direccion.setText("")
                    telefono.setText("")
                    correo.setText("")
                    contrasena.setText("")
                    contrasena2.setText("")
                    Toast.makeText(this,"Datos guardar correctamente", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this,"Las contraseñas no coinciden", Toast.LENGTH_LONG).show()
                }
            }
        }
        buscar.setOnClickListener {
            val admin = SQLiteConexion(this, "cursos", null, 1)
            val bd = admin.writableDatabase
            val fila = bd.rawQuery("select nombre,apellidos,direccion,telefono,correo from" +
                    " docentes where nombre='${nombre.text.toString()}' and apellidos='${apellidos.text.toString()}'", null)
            if (fila.moveToFirst()) {
                nombre.setText(fila.getString(0))
                apellidos.setText(fila.getString(1))
                direccion.setText(fila.getString(2))
                telefono.setText(fila.getString(3))
                correo.setText(fila.getString(4))
            } else
                Toast.makeText(this, "No existe este profesor chafa",  Toast.LENGTH_SHORT).show()
            bd.close()
        }
        editar.setOnClickListener {
            val admin = SQLiteConexion(this, "cursos", null, 1)
            val bd = admin.writableDatabase
            val registro = ContentValues()
            registro.put("nombre",nombre.getText().toString())
            registro.put("apellidos",apellidos.getText().toString())
            registro.put("direccion",direccion.getText().toString())
            registro.put("telefono",telefono.getText().toString())
            registro.put("correo",correo.getText().toString())
            registro.put("contrasena",contrasena.getText().toString())
            val cant = bd.update("docentes", registro, "correo='${correo.text.toString()}'", null)
            bd.close()
            if (cant == 1)
                Toast.makeText(this, "se modificaron los datos", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "no existe el docente", Toast.LENGTH_SHORT).show()

        }
        eliminar.setOnClickListener {
            val admin = SQLiteConexion(this, "cursos", null, 1)
            val bd = admin.writableDatabase
            val cant = bd.delete("docentes", "correo='${correo.text.toString()}'", null)
            bd.close()
            nombre.setText("")
            apellidos.setText("")
            direccion.setText("")
            telefono.setText("")
            correo.setText("")
            contrasena.setText("")
            contrasena2.setText("")
            if (cant == 1) {
                Toast.makeText(this, "Se borró el docente", Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(this, "No existe el docente", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }
}