# Selección de producto 

En esta primera etapa, la aplicación permite seleccionar un producto desde una lista desplegable (Spinner), evitando que el usuario escriba manualmente el nombre.

**Herramientas utilizadas:**

- Spinner (Android UI): permite mostrar una lista de opciones seleccionables.
- ArrayAdapter: se utiliza para cargar los datos (lista de productos) en el Spinner.
- List (Kotlin): estructura donde se almacenan los productos disponibles.

<p align="center">
<img width="373" height="806" alt="Captura de pantalla 2026-04-15 214954" src="https://github.com/user-attachments/assets/08418866-02fe-4bfc-b6bd-02182b1cc202" />
</p>

# Ingreso de cantidad y precio

Luego de seleccionar el producto, el usuario debe ingresar la cantidad y el precio mediante campos de texto.

Se valida que los datos ingresados sean correctos antes de permitir el registro del producto.

**Herramientas utilizadas:**

- EditText: para capturar datos del usuario.
- inputType:
    - number → cantidad
    - numberDecimal → precio
- Funciones de validación (Kotlin):
    - toIntOrNull()
    - toDoubleOrNull()
- Toast: muestra mensajes si los datos son inválidos.

<p align="center">
<img width="367" height="767" alt="Captura de pantalla 2026-04-15 215220" src="https://github.com/user-attachments/assets/7aa1ddec-e721-46f2-8f99-360586774e25" />
</p>

# Agregar productos al catálogo

Al presionar el botón "Agregar producto", el sistema:

1. Obtiene los datos ingresados
2. Crea un objeto Producto
3. Lo añade a la lista
4. Lo muestra en pantalla

**Herramientas utilizadas:**

- Button: ejecuta la acción de agregar.
- Clase data (Producto)
- MutableList: almacena los productos agregados dinámicamente.
- RecyclerView.Adapter:
    - notifyItemInserted() actualiza la lista en pantalla.

<p align="center">
<img width="367" height="762" alt="Captura de pantalla 2026-04-15 215119" src="https://github.com/user-attachments/assets/55c9a88e-4909-45d2-b5ab-c4d1256fb29b" />
</p>

# Visualización de productos (RecyclerView)

Los productos agregados se muestran en una lista dinámica mediante RecyclerView, donde cada elemento contiene:

- Imagen
- Nombre
- Cantidad
- Precio

**Herramientas utilizadas:**

- RecyclerView: componente principal para listas dinámicas.
- LinearLayoutManager: organiza los elementos verticalmente.
- ViewHolder: optimiza el rendimiento al reutilizar vistas.
- CardView: mejora la presentación visual de cada producto.
- ImageView: muestra la imagen asociada al producto.

<p align="center">
<img width="365" height="766" alt="Captura de pantalla 2026-04-15 215327" src="https://github.com/user-attachments/assets/d2291b91-c7b3-4d29-bb7e-f415b47dcc44" />
</p>

# Asignación automática de imágenes

Dependiendo del producto seleccionado, se asigna automáticamente una imagen correspondiente.

**Herramientas utilizadas:**

- when (Kotlin): estructura condicional para asignar imágenes.
- Drawable resources:
    - R.drawable.blusaverde
    - R.drawable.blusarosa
- ImageView.setImageResource(): muestra la imagen en pantalla.

<p align="center">
  <img width="360" height="306" alt="Captura de pantalla 2026-04-15 215408" src="https://github.com/user-attachments/assets/a99ffa5f-7936-4f57-bfb1-b5cb812b7313" />
</p>

# Almacenamiento con SharedPreferences

Cada vez que se agrega un producto, la lista completa se guarda en almacenamiento interno utilizando SharedPreferences.

Esto permite que los datos se mantengan incluso después de cerrar la aplicación.

**Herramientas utilizadas:**

- SharedPreferences:
    - getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)
- Editor
- apply(): guarda los cambios de forma asíncrona.

<p align="center">
  <img width="1096" height="185" alt="Captura de pantalla 2026-04-15 215703" src="https://github.com/user-attachments/assets/5651cd59-2d8c-4023-8c35-b56b430ea4d6" />
</p>

# Conversión de datos con Gson

Como SharedPreferences no permite guardar listas directamente, se convierte la lista de productos a formato JSON.

**Herramientas utilizadas:**

- Gson:
    - toJson() → convierte lista a texto
    - fromJson() → convierte texto a lista
- TypeToken: permite trabajar con listas de objetos.

# Recuperación de datos

Al iniciar la aplicación, se recuperan los productos almacenados y se muestran automáticamente en el RecyclerView.

**Herramientas utilizadas:**

- SharedPreferences.getString()
- Gson.fromJson()
- RecyclerView Adapter
