package es.sanclemente.navigationtemplate

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import es.sanclemente.navigationtemplate.databinding.ActivityMainBinding

// Main Activity: hosts the Toolbar and NavHostFragment.
// The Navigation component handles switching between screens, with nav_graph.xml defining the available destinations.
class MainActivity : AppCompatActivity() {

    // View Binding provides direct, type-safe access to XML views.
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inflates activity_main.xml and sets it as the Activity's UI.
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Applies system bar dimensions as padding to prevent UI overlap.
        // This keeps content visible while preserving edge-to-edge rendering.
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val barras = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(barras.left, barras.top, barras.right, barras.bottom)
            insets
        }

        // Retrieves the NavHostFragment, which hosts the navigation graph and provides the NavController responsible for screen navigation.
        val navHost = supportFragmentManager
            .findFragmentById(R.id.nav_host) as NavHostFragment

        // Connects the Toolbar to the NavController, automatically updating its title and navigation controls according to the current screen.
        binding.toolbar.setupWithNavController(navHost.navController)
    }
}