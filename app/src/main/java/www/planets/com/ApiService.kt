package www.planets.com

// Importaciones necesarias de Retrofit para trabajar con llamadas HTTP y anotaciones de métodos
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

// Definición de la interfaz ApiService, que define los endpoints de la API REST
interface ApiService {

    // Método para obtener imágenes de planetas por nombre
    // @GET indica que es una solicitud HTTP GET
    // @Path se utiliza para agregar el valor de la variable raza a la URL
    @GET("{name}")
    fun getPlanetsByBreed(@Path("name") name: String?): Call<PlanetsResponse?>?
}