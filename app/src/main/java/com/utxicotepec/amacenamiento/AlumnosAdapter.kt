package com.utxicotepec.amacenamiento

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AlumnosAdapter: RecyclerView.Adapter<AlumnosAdapter.ViewHolder>() {
    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val nombre: TextView =view.findViewById(R.id.txtnombre)
        val contacto: TextView=view.findViewById(R.id.txtcontacto)
        val direccion:TextView=view.findViewById(R.id.txtdireccion)
        val imagen:ImageView=view.findViewById(R.id.imageView2)
        val eliminar:Button=view.findViewById(R.id.btnEliminar)
        val editar:Button=view.findViewById(R.id.btnEdit)
        fun bind(item: Docente){
            nombre.text=item.nombre.toString()+" "+item.apellidos.toString()
            contacto.text="Correo: "+item.correo.toString()+" Telefono: "+item.telefono.toString()
            direccion.text=item.direccion.toString()
            imagen.setImageResource(item.Imagen)
            //----PUEDO GENERAR UN EVENTO PARA UN BOTON O UNA IMAGEN-------
            eliminar.setOnClickListener {

            }
            editar.setOnClickListener {

            }
        }
    }
    var docente= listOf<Docente>()
        set(value){
            field=value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater
            .from(parent.context)
            .inflate(R.layout.vista_docente, parent, false)
        return AlumnosAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=docente[holder.adapterPosition]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return docente.size
    }

}