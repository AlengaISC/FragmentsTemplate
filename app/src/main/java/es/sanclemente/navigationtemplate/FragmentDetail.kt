package es.sanclemente.navigationtemplate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import es.sanclemente.navigationtemplate.databinding.FragmentDetalleBinding

// FragmentDetail: displays the message received from InicioFragment.
// Also provides a button to return to the previous screen.
class FragmentDetail : Fragment() {

    // Safe Args retrieves the arguments passed through the navigation graph.
    // FragmentDetail is generated automatically from nav_graph.xml.
    private val args: FragmentDetailArgs by navArgs()

    // Fragment View Binding. Nullable because the Fragment can outlive its
    // View, so it must be cleared when onDestroyView() is called.
    private var _binding: FragmentDetalleBinding? = null

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

        // Inflates fragment_detalle.xml and returns its root View.
        // attachToRoot = false because the FragmentManager handles attachment.
        _binding = FragmentDetalleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Displays the message received through Safe Args.
        // If the message is empty or contains only whitespace, displays
        // the default text defined in strings.xml instead.
        binding.textMessage.text = args.message.ifBlank {
            getString(R.string.message_empty)
        }

        // Returns to the previous navigation destination.
        // Equivalent to pressing the Toolbar's Up button or system Back.
        binding.btVolver.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // Clears the binding reference when the Fragment's View is destroyed.
        // Prevents memory leaks caused by keeping references to obsolete Views.
        _binding = null
    }
}