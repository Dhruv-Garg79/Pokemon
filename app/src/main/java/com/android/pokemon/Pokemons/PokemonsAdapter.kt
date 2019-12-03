package com.android.pokemon.Pokemons

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.pokemon.R
import com.apollographql.apollo.queries.PokemonsQuery
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_pokemon.view.*

class PokemonsAdapter(private val context: Context, private val listener : (name : String) -> Unit) : RecyclerView.Adapter<PokemonsAdapter.MyViewHolder>(){
    private var list = emptyList<PokemonsQuery.Pokemon>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(list : List<PokemonsQuery.Pokemon>?){
        if (list != null) this.list = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val pokemon = list[position].fragments().pokemonFragment()
        Glide.with(context).load(pokemon.image()).into(holder.image)
        holder.maxCP.text = pokemon.maxCP().toString()
        holder.type.text = pokemon.types()?.joinToString("/")
        holder.numName.text = "${pokemon.number()} - ${pokemon.name()}"
        holder.itemView.setOnClickListener { pokemon.name()?.let { it1 -> listener(it1) } }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.pokemonImage
        val numName = itemView.pokemonNameNumber
        val maxCP = itemView.pokemonCP
        val type = itemView.pokemonType
    }
}