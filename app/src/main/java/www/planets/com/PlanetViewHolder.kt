package www.planets.com

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import www.planets.com.databinding.ItemPlanetBinding
import com.squareup.picasso.Picasso

// Definición de la clase DogViewHolder que extiende RecyclerView.ViewHolder y toma una vista como parámetro en su constructor
class PlanetViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {

    // Declaración de una instancia de ItemDogBinding para acceder a las vistas en el diseño del elemento de planeta
    private val itemPlanetBinding: ItemPlanetBinding

    // Inicialización del ViewHolder
    init {
        // Vinculación de la vista a la clase de enlace generada para el diseño del elemento de planeta
        itemPlanetBinding = ItemPlanetBinding.bind(view!!)
    }

    // Método para vincular la URL de la imagen de planeta al ImageView en el diseño del elemento de planeta
    fun bind(imageUrl: String?) {
        // Carga la imagen desde la URL usando Picasso y la muestra en el ImageView ivPlaneta en el diseño del elemento de planeta
        Picasso.get().load(imageUrl).into(itemPlanetBinding.ivPlanet)
    }
}