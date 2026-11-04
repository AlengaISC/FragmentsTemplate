package es.sanclemente.plantilla

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import es.sanclemente.plantilla.databinding.FragmentDetalleBinding

class DetalleFragment : Fragment() {

    // Safe Args: DetalleFragmentArgs se genera a partir de los <argument> del grafo
    private val args: DetalleFragmentArgs by navArgs()

    private var _binding: FragmentDetalleBinding? = null
    private val binding get() = checkNotNull(_binding) {
        "No se puede acceder al binding porque es nulo. ¿Está visible la vista?"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetalleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Si llega vacío, mostramos un texto por defecto
        binding.tvMensaxe.text = args.mensaxe.ifBlank { getString(R.string.sen_mensaxe) }

        // Volver: lo mismo que la flecha de la barra o el "atrás" del sistema
        binding.btVolver.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
