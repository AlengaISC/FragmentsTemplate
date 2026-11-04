package es.sanclemente.plantilla

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import es.sanclemente.plantilla.databinding.FragmentInicioBinding

class InicioFragment : Fragment() {

    // Binding de fragmento: nulo cuando no hay vista (ver onDestroyView)
    private var _binding: FragmentInicioBinding? = null
    private val binding get() = checkNotNull(_binding) {
        "No se puede acceder al binding porque es nulo. ¿Está visible la vista?"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInicioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btSeguinte.setOnClickListener {
            val texto = binding.etMensaxe.text.toString()

            // Safe Args: la clase InicioFragmentDirections la genera Gradle a partir
            // de la <action> del grafo. Si no existe, haz Build → Assemble Project.
            val accion = InicioFragmentDirections.accionInicioADetalle(texto)
            findNavController().navigate(accion)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
