package com.example.catalogoproductos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale
import android.widget.ImageView

class ProductoAdapter(private val listaProductos: List<Producto>) :
    RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {

    class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNombre: TextView = itemView.findViewById(R.id.txtNombre)
        val txtCantidad: TextView = itemView.findViewById(R.id.txtCantidad)
        val txtPrecio: TextView = itemView.findViewById(R.id.txtPrecio)

        val imgProducto: ImageView = itemView.findViewById(R.id.imgProducto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false)
        return ProductoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = listaProductos[position]
        holder.txtNombre.text = producto.nombre
        holder.txtCantidad.text = "Cantidad: ${producto.cantidad}"
        holder.txtPrecio.text = String.format(Locale.getDefault(), "Precio: S/ %.2f", producto.precio)
        holder.imgProducto.setImageResource(producto.imagen)
    }

    override fun getItemCount(): Int = listaProductos.size
}