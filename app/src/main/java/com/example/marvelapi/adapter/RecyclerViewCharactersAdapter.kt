package com.example.marvelapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapi.databinding.CharactersListItemBinding
import com.example.marvelapi.model.CharacterResults
import com.example.marvelapi.view.HomeFragmentDirections
import com.squareup.picasso.Picasso

class RecyclerViewCharactersAdapter(private val characterList:ArrayList<CharacterResults>):RecyclerView.Adapter<RecyclerViewCharactersAdapter.RowHolder>() {

    inner class RowHolder(private var binding: CharactersListItemBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(characters: CharacterResults){
            binding.txtName.text=characters.name
            val path=characters.thumbnail?.path
            val extension=characters.thumbnail?.extension
            val truePath= "$path/portrait_uncanny.$extension"
            Picasso.get().load(truePath).into(binding.imgImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val binding=CharactersListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RowHolder(binding)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        val charactersModel:CharacterResults=characterList[position]
        holder.bind(charactersModel)

        holder.itemView.setOnClickListener {
            val id=characterList[position].id.toString()
            val characterName=characterList[position].name.toString()
            val description=characterList[position].description.toString()
            val path=characterList[position].thumbnail?.path.toString()
            val extension=characterList[position].thumbnail?.extension.toString()
            val truePath= "$path/portrait_uncanny.$extension"

            val action=HomeFragmentDirections.actionHomeFragmentToDetailFragment(id,characterName,description,truePath)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return characterList.size
    }
}