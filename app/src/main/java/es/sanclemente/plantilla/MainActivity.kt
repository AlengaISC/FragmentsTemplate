package es.sanclemente.plantilla

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import es.sanclemente.plantilla.databinding.ActivityMainBinding

// La actividad solo pone el marco: barra superior + contenedor de fragmentos.
// Qué fragmento se ve en cada momento lo decide el grafo (res/navigation/nav_graph.xml).
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Que la barra de estado y la de navegación del sistema no tapen nada
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val barras = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(barras.left, barras.top, barras.right, barras.bottom)
            insets
        }

        // El NavController vive dentro del NavHostFragment del layout
        val navHost = supportFragmentManager
            .findFragmentById(R.id.nav_host) as NavHostFragment

        // Título de cada destino (android:label) y flecha de "atrás" en la barra
        binding.toolbar.setupWithNavController(navHost.navController)
    }
}
