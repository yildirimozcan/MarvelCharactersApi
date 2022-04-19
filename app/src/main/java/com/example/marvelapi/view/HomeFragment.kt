package com.example.marvelapi.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapi.R
import com.example.marvelapi.adapter.RecyclerViewCharactersAdapter
import com.example.marvelapi.core.BaseFragment
import com.example.marvelapi.databinding.FragmentHomeBinding
import com.example.marvelapi.model.CharacterResults
import com.example.marvelapi.viewmodel.HomeFragmentViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.java.KoinJavaComponent.inject

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val homeFragmentViewModel: HomeFragmentViewModel by viewModel()
    private var recyclerViewCharactersAdapter:RecyclerViewCharactersAdapter?=null

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding {
       return FragmentHomeBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        binding?.recyclerViewCharacters?.layoutManager=layoutManager

        observe()
        homeFragmentViewModel.callResult()

    }

    private fun observe(){
        homeFragmentViewModel.callCharactersResult.observe(viewLifecycleOwner) { result ->
            result.onSuccess {
                recyclerViewAdapter(it.data?.results!!)
            }
            result.onApiError {
                Toast.makeText(context,it.error.errorMessage,Toast.LENGTH_LONG ).show()
            }
        }
    }

    private fun recyclerViewAdapter(characterModel:ArrayList<CharacterResults>){
        recyclerViewCharactersAdapter= RecyclerViewCharactersAdapter(characterModel)
        binding?.recyclerViewCharacters?.adapter=recyclerViewCharactersAdapter
    }
}