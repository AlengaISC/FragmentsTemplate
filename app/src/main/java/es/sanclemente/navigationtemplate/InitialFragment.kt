package es.sanclemente.navigationtemplate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import es.sanclemente.navigationtemplate.databinding.FragmentInitialBinding

// InitialFragment: the initial screen of the application.
// Allows the user to enter a message and navigate to the detail screen,
// passing that message as an argument.
class InitialFragment : Fragment() {

    // Fragment View Binding. Nullable because the Fragment can outlive its
    // View, so it must be cleared when onDestroyView() is called.
    private var _binding: FragmentInitialBinding? = null

    // Non-null accessor for the binding. Throws an exception if accessed
    // outside the View's lifecycle, helping prevent invalid references.
    private val binding
        get() = checkNotNull(_binding) {
            "No se puede acceder al binding porque es nulo. ¿Está visible la vista?"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflates fragment_inicio.xml and returns its root View.
        // attachToRoot = false because the FragmentManager handles attachment.
        _binding = FragmentInitialBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Sets a click listener on the button to read the input message
        // and navigate to the detail screen, passing the text as an argument.
        binding.buttonNext.setOnClickListener {
            val texto = binding.editMessage.text.toString()

            // Safe Args generates type-safe navigation directions from nav_graph.xml.
            // The generated action passes the message to the destination Fragment.
            val accion = InicioFragmentDirections.accionInicioADetalle(texto)
            findNavController().navigate(accion)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Clears the binding reference when the Fragment's View is destroyed.
        // Prevents memory leaks caused by keeping references to obsolete Views.
        _binding = null
    }
}
