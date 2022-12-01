package com.utxicotepec.amacenamiento

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ReporteDocente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reporte_docente)
        val docentesList: RecyclerView= findViewById<RecyclerView>(R.id.docentesList)
        val docentesAdapter=DocentesAdapter()
        docentesList.adapter=docentesAdapter
        docentesAdapter.docente=verdatos()

        docentesList.layoutManager = LinearLayoutManager(this)

    }
  fun verdatos():MutableList<Docente>{
          var list: MutableList<Docente> = ArrayList()
     /*list.add(0, Docente(1,"Juan","Cruz Martinez","Priv de 17 de junio","764121212","juan@mari.con","1234",1))
      list.add(1, Docente(1,"Ramon","Cruz Martinez","Priv de 17 de junio","764121212","juan@mari.con","1234",1))
*/
      val admin = SQLiteConexion(this, "cursos", null, 1)
        val bd = admin.writableDatabase
        val query="select id,nombre,apellidos,direccion,telefono,correo,contrasena from docentes"
       val result = bd.rawQuery(query,null)
         var i=0;
        if(result !=null && result.moveToFirst()){
            do{
                list.add(i, Docente(result.getInt(0),result.getString(1),result.getString(2),
                result.getString(3),result.getString(4),result.getString(5),result.getString(6),1))
                i++
            }while (result.moveToNext())
        }
        result.close()
        bd.close()
        return list
    }
}