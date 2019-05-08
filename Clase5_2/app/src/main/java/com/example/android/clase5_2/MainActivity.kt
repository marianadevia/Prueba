package com.example.android.clase5_2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_layout.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var lista = ArrayList<Persona>()
        lista.add(Persona("Vivi", "Chipilipi"))
        lista.add(Persona("Cristina", "Cares"))
        lista.add(Persona("Armando", "Casas"))
        lista.add(Persona("Guardian", "De Las Mercedes"))
        lista.add(Persona("Maggie", "De Las Mercedes"))

        var adaptador = PersonaAdapter(this, lista)
        rvListado.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        rvListado.adapter = adaptador

    }
}

class PersonaAdapter(val contexto: Context, val lista: ArrayList<Persona>) :
    RecyclerView.Adapter<PersonaAdapter.CustomViewHolder>() {

    /**
     * Insertar elemento al grupo de la vista
     */
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {
        val view: View = LayoutInflater.from(contexto).inflate(R.layout.item_layout, p0, false)
        view.setOnClickListener {
            var nombre = view.findViewById<TextView>(R.id.lbNombre)
            var apellido = view.findViewById<TextView>(R.id.lbApellido)
            var mensaje = "${nombre.text} ${apellido.text}"

            var intento = Intent(contexto, ResultadoActivity::class.java)
            intento.putExtra("msg", mensaje)
            contexto.startActivity(intento)
        }
        return CustomViewHolder(view)
    }

    /**
     * Cuantos objetos va a generar la lista en total
     */
    override fun getItemCount(): Int {
        return lista.size
    }

    /**
     * Completar datos, en que posicion (cual datos carga)
     */
    override fun onBindViewHolder(customViewHolder: CustomViewHolder, position: Int) {
        customViewHolder.bindData(lista[position])
    }

    class CustomViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        /**
         * En cambio del inflate completo, sostiene elementos de la lista
         * Agrega los datos al elemento en el momento en que los va a agregar a la vista
         */
        fun bindData(persona: Persona) {
            view.lbNombre.text = persona.nombre
            view.lbApellido.text = persona.apellido
        }
    }
}