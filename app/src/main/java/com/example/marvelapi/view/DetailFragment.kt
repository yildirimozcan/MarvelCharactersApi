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
import com.example.marvelapi.adapter.RecyclerViewComicsAdapter
import com.example.marvelapi.core.BaseFragment
import com.example.marvelapi.databinding.FragmentDetailBinding
import com.example.marvelapi.model.ComicResults
import com.example.marvelapi.viewmodel.DetailFragmentViewModel
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    private val detailFragmentViewModel:DetailFragmentViewModel by viewModel()
    private var recyclerViewComicsAdapter:RecyclerViewComicsAdapter?=null

    private var id:String?=null
    private var name:String?=null
    private var description:String?=null
    private var imageUrl:String?=null

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        binding?.recyclerViewComics?.layoutManager=layoutManager

        observe()

        arguments?.let {
            id=DetailFragmentArgs.fromBundle(it).characterId
            name=DetailFragmentArgs.fromBundle(it).characterName
            description=DetailFragmentArgs.fromBundle(it).characterDescription
            imageUrl=DetailFragmentArgs.fromBundle(it).characterImage
        }

        binding?.txtDetailName?.text=name
        binding?.txtDetailDescription?.text=description
        Picasso.get().load(imageUrl).into(binding?.imageViewImage)

        detailFragmentViewModel.callComics(id!!,"2005-01-01,2022-01-01","-onsaleDate","10")
    }

    private fun observe(){
        detailFragmentViewModel.callComicsLiveData.observe(viewLifecycleOwner) { result ->
            result.onSuccess {
             //   binding?.txtDetailName?.text=it.data?.results!![0].title
                recyclerViewAdapter(it.data?.results!!)
            }
            result.onApiError {
                binding?.txtDetailName?.text=it.error.errorMessage
            }
        }
    }

    private fun recyclerViewAdapter(comicsModel:ArrayList<ComicResults>){
        recyclerViewComicsAdapter=RecyclerViewComicsAdapter(comicsModel)
        binding?.recyclerViewComics?.adapter=recyclerViewComicsAdapter
    }
}

