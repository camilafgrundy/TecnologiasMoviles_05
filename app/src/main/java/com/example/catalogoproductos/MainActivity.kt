package com.example.catalogoproductos

import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerProductos: RecyclerView
    private lateinit var btnAgregar: Button
    private lateinit var spProducto: Spinner
    private lateinit var etCantidad: EditText
    private lateinit var etPrecio: EditText
    private lateinit var adapter: ProductoAdapter

    private var listaProductos = mutableListOf<Producto>()

    private val productosDisponibles = listOf(
        "Blusa Verde",
        "Blusa Rosa",
        "Blusa Roja",
        "Blusa Negra",
        "Blusa Morada",
        "Blusa Celeste",
        "Blusa Café",
        "Blusa Blanca"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerProductos = findViewById(R.id.recyclerProductos)
        btnAgregar = findViewById(R.id.btnAgregar)
        spProducto = findViewById(R.id.spProducto)
        etCantidad = findViewById(R.id.etCantidad)
        etPrecio = findViewById(R.id.etPrecio)

        val spinnerAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            productosDisponibles
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spProducto.adapter = spinnerAdapter

        recyclerProductos.layoutManager = LinearLayoutManager(this)

        listaProductos = cargarProductos().toMutableList()
        adapter = ProductoAdapter(listaProductos)
        recyclerProductos.adapter = adapter

        btnAgregar.setOnClickListener {
            agregarProducto()
        }
    }

    private fun agregarProducto() {
        val nombre = spProducto.selectedItem.toString()
        val cantidadTexto = etCantidad.text.toString().trim()
        val precioTexto = etPrecio.text.toString().trim()

        if (cantidadTexto.isEmpty() || precioTexto.isEmpty()) {
            Toast.makeText(this, "Complete cantidad y precio", Toast.LENGTH_SHORT).show()
            return
        }

        val cantidad = cantidadTexto.toIntOrNull()
        val precio = precioTexto.toDoubleOrNull()

        if (cantidad == null || precio == null) {
            Toast.makeText(this, "Cantidad o precio inválidos", Toast.LENGTH_SHORT).show()
            return
        }

        val imagenProducto = obtenerImagenPorNombre(nombre)

        val producto = Producto(
            nombre = nombre,
            cantidad = cantidad,
            precio = precio,
            imagen = imagenProducto
        )

        listaProductos.add(producto)
        guardarProductos(listaProductos)
        adapter.notifyItemInserted(listaProductos.size - 1)

        etCantidad.text.clear()
        etPrecio.text.clear()
    }

    private fun obtenerImagenPorNombre(nombre: String): Int {
        return when (nombre) {
            "Blusa Verde" -> R.drawable.blusaverde
            "Blusa Rosa" -> R.drawable.blusarosa
            "Blusa Roja" -> R.drawable.blusaroja
            "Blusa Negra" -> R.drawable.blusanegra
            "Blusa Morada" -> R.drawable.blusamorada
            "Blusa Celeste" -> R.drawable.blusaceleste
            "Blusa Café" -> R.drawable.blusacafe
            "Blusa Blanca" -> R.drawable.blusablanca
            else -> R.drawable.blusablanca
        }
    }

    private fun guardarProductos(lista: List<Producto>) {
        val sharedPreferences = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val gson = Gson()
        val json = gson.toJson(lista)

        editor.putString("productos", json)
        editor.apply()
    }

    private fun cargarProductos(): List<Producto> {
        val sharedPreferences = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("productos", null)

        return if (json != null) {
            val type = object : TypeToken<List<Producto>>() {}.type
            gson.fromJson(json, type)
        } else {
            emptyList()
        }
    }
}