package com.ryutec.pruebatecnica.ui.view.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.asFlow
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ryutec.pruebatecnica.data.Resource
import com.ryutec.pruebatecnica.data.adapter.AdapterIAutoriza
import com.ryutec.pruebatecnica.data.adapter.AdapterRickAndMorty
import com.ryutec.pruebatecnica.data.model.operations.OperationsModel
import com.ryutec.pruebatecnica.databinding.FragmentRickAndMortyBinding
import com.ryutec.pruebatecnica.domain.model.Character
import com.ryutec.pruebatecnica.domain.model.sendToActivity
import com.ryutec.pruebatecnica.ui.viewmodel.RickAndMortyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RickAndMortyFragment : Fragment() {

    private var _binding: FragmentRickAndMortyBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RickAndMortyViewModel by viewModels()

    private lateinit var adapter: AdapterRickAndMorty

    private var offset = 1

    private val list = mutableListOf<Character>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRickAndMortyBinding.inflate(layoutInflater)
        viewModel.onCreate(offset)

        setRecyclerView(list)

        uiFlow()

        loadMore()
        return binding.root
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

    private fun manageFlow(uiState: Resource<List<Character>>?) {
        when (uiState) {
            is Resource.Success -> {
                list.addAll(uiState.result)

                adapter.notifyDataSetChanged()


                binding.rv.visibility = View.VISIBLE

                binding.progresBar.visibility = View.GONE
            }

            Resource.Loading -> {
                binding.progresBar.visibility = View.VISIBLE
            }

            is Resource.Failure -> {
                Toast.makeText(
                    activity,
                    "Ocurrio un error, consulte al administrador",
                    Toast.LENGTH_SHORT
                ).show()
                binding.progresBar.visibility = View.GONE
            }

            else -> {}

        }
    }

    private fun loadMore() {

        binding.rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    offset++
                    viewModel.onCreate(offset)
                }
            }
        })
    }


    private fun setRecyclerView(list: List<Character>) {
        adapter = AdapterRickAndMorty(list) { character ->
            context?.let { character.sendToActivity(it) }
        }
        binding.rv.layoutManager = GridLayoutManager(context, 2)
        binding.rv.adapter = adapter
    }


}