package com.example.marvelapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapi.databinding.CharacterComicsListBinding
import com.example.marvelapi.model.ComicResults

class RecyclerViewComicsAdapter(private var comicsList:ArrayList<ComicResults>):RecyclerView.Adapter<RecyclerViewComicsAdapter.RowHolder>() {

    inner class RowHolder(private var binding:CharacterComicsListBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(comics:ComicResults){
            binding.txtComics.text=comics.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val binding=CharacterComicsListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RowHolder(binding)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        val comicsModel:ComicResults=comicsList[position]
        holder.bind(comicsModel)
    }

    override fun getItemCount(): Int {
        return comicsList.size
    }
}