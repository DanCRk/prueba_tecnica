package com.ryutec.pruebatecnica.ui.view.tabs

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.asFlow
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ryutec.pruebatecnica.data.Resource
import com.ryutec.pruebatecnica.data.adapter.AdapterIAutoriza
import com.ryutec.pruebatecnica.data.model.operations.OperationsModel
import com.ryutec.pruebatecnica.databinding.FragmentIAutorizaBinding
import com.ryutec.pruebatecnica.ui.viewmodel.IAutorizaViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import android.app.Activity.RESULT_OK
import android.net.Uri
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.ryutec.pruebatecnica.R
import com.ryutec.pruebatecnica.ui.view.dialog.ExitDialogFragment
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class IAutorizaFragment : Fragment() {

    private var _binding: FragmentIAutorizaBinding? = null
    private val binding get() = _binding!!
    private val viewModel: IAutorizaViewModel by viewModels()

    private lateinit var adapter: AdapterIAutoriza

    private val list = mutableListOf<OperationsModel>()

    private val responseUploadLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ activityResult ->
            lifecycleScope.launch {
                if (activityResult.resultCode == RESULT_OK && activityResult.data != null){
                    val imageUri: Uri = activityResult.data?.data!!
                    Glide.with(requireActivity())
                        .load(imageUri)
                        .transform(CenterCrop())
                        .into(binding.logoHeader)
                }else{
                    Toast.makeText(
                        requireActivity(),
                        "No se recupero ningun archivo",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIAutorizaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView(list)

        viewModel.onCreate()

        uiFlow()

        changeImage()

        suportButton()

        setNowDate()

        exitButton()
    }

    private fun setNowDate() {
        val dateFormat = SimpleDateFormat("dd 'de' MMMM yyyy", Locale("es", "ES"))
        val now = Date()
        binding.date.text = dateFormat.format(now)
    }

    private fun exitButton(){
        binding.layoutSalir.setOnClickListener {
            ExitDialogFragment(onSubmitClickListener = { decision ->
                if (decision){
                    activity?.finish()
                }
            }).show(childFragmentManager, "exitDialogFragment")
        }
    }


    private fun uiFlow() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.uploadFlow.asFlow()
                    .collect { uiState ->
                        manageFlow(uiState)
                    }
            }
        }
    }

    private fun manageFlow(uiState: Resource<List<OperationsModel>>?) {
        when (uiState) {
            is Resource.Success -> {
                // Si se necesita que este constantemente
                // refrescando se elimina el condicional
                if (list.isEmpty()){
                    list.addAll(uiState.result)

                    adapter.notifyDataSetChanged()

                    binding.progresBarIAutoriza.visibility = View.GONE

                    binding.rv.visibility = View.VISIBLE
                }
            }

            Resource.Loading -> {
                binding.progresBarIAutoriza.visibility = View.VISIBLE
            }

            is Resource.Failure -> {
                Toast.makeText(
                    activity,
                    "Ocurrio un error, consulte al administrador",
                    Toast.LENGTH_SHORT
                ).show()
                binding.progresBarIAutoriza.visibility = View.GONE
            }

            else -> {}

        }
    }

    private fun changeImage(){
        binding.changeImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
                type = "image/*"
            }
            responseUploadLauncher.launch(intent)

        }
    }

    private fun setRecyclerView(list: List<OperationsModel>) {
        adapter = AdapterIAutoriza(list)
        binding.rv.layoutManager = LinearLayoutManager(context)
        binding.rv.adapter = adapter
    }

    private fun suportButton(){
        binding.soporte.setOnClickListener {
            Toast.makeText(activity, getString(R.string.en_proceso), Toast.LENGTH_SHORT).show()
        }
    }


}