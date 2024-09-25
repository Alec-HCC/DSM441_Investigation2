package www.planets.com

import com.google.gson.annotations.SerializedName

// Definición de la clase PlanetsResponse que representa la respuesta de la API de planetas
class PlanetsResponse {

    // Campo que representa la lista de URLs de imágenes de planetas
    @SerializedName("message")
    private var images: List<String?>? = null

    // Método getter para obtener la lista de URLs de imágenes de planetas
    fun getImages(): List<String?>? {
        return images
    }

    // Método setter para establecer la lista de URLs de imágenes de planetas
    fun setImages(images: List<String?>?) {
        this.images = images
    }
}