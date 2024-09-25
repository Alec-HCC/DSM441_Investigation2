package www.planets.com

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import www.planets.com.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    // Declaración de variables
    var binding: ActivityMainBinding? = null
    var planetAdapter: PlanetAdapter? = null
    var images: MutableList<String> = ArrayList()

    // Método que se llama cuando se crea la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflar el diseño de la actividad y establecerlo como el contenido de la vista
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        // Inicializar el RecyclerView y el adaptador
        initRecyclerView()
        // Configurar el listener de búsqueda en el SearchView
        binding!!.searchPlanets.setOnQueryTextListener(this as SearchView.OnQueryTextListener)
    }

    // Método privado para inicializar el RecyclerView
    private fun initRecyclerView() {
        // Crear una instancia del adaptador de planetas con la lista de imágenes vacía
        planetAdapter = PlanetAdapter(images)
        // Establecer un LinearLayoutManager en el RecyclerView
        binding!!.listPlanets.layoutManager = LinearLayoutManager(this)
        // Establecer el adaptador en el RecyclerView
        binding!!.listPlanets.adapter = planetAdapter
    }

    // Método para obtener una instancia del servicio de la API mediante Retrofit
    private val apiService: ApiService
        private get() {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/Alec-HCC/DSM441_Investigation2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }

    // Método para realizar la búsqueda de imágenes de planetas por raza
    private fun searchByName(id: String) {
        // Llamar al método correspondiente en el servicio de la API para obtener la lista de imágenes
        val batch: Call<PlanetsResponse?>? = apiService.getPlanetsByBreed(id)
        batch?.enqueue(object : Callback<PlanetsResponse?> {
            // Manejar la respuesta exitosa
            override fun onResponse(
                @Nullable call: Call<PlanetsResponse?>?,
                @Nullable response: Response<PlanetsResponse?>?
            ) {
                if (response != null && response.body() != null) {
                    // Obtener la lista de imágenes de la respuesta
                    val responseImages: List<String> = response.body()!!.getImages() as List<String>
                    // Limpiar la lista actual de imágenes y agregar las nuevas
                    images.clear()
                    images.addAll(responseImages)
                    // Notificar al adaptador que los datos han cambiado
                    planetAdapter!!.notifyDataSetChanged()
                }
            }

            // Manejar la respuesta fallida
            override fun onFailure(@Nullable call: Call<PlanetsResponse?>?, @Nullable t: Throwable?) {
                if (t != null) {
                    // Mostrar un mensaje de error en caso de falla
                    showError()
                }
            }
        })
    }

    // Método para mostrar un mensaje de error
    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

    // Método que se llama cuando se envía un texto de búsqueda
    override fun onQueryTextSubmit(query: String): Boolean {
        if (!query.isEmpty()) {
            // Convertir la consulta a minúsculas y realizar la búsqueda
            searchByName(query.lowercase(Locale.getDefault()))
        }
        return true
    }

    // Método que se llama cuando cambia el texto de búsqueda (no utilizado en este caso)
    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}