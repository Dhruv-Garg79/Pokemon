package com.android.pokemon.pokemonDetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.pokemon.App
import com.android.pokemon.R
import com.apollographql.apollo.coroutines.toDeferred
import com.apollographql.apollo.queries.PokemonDetailQuery
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_pokemon_detail.*
import kotlinx.android.synthetic.main.fragment_pokemon_detail.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PokemonDetailFragment : Fragment() {

    private var job : Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_pokemon_detail, container, false)
        root.loading.playAnimation()
        root.pokeCard.visibility = View.GONE

        val name = arguments?.getString(POKEMON_NAME)
        if (!name.isNullOrBlank()){
            fetchPokemonDetails(name)
        }
        else fetchPokemonDetails("pikachu")
        return root
    }

    private fun fetchPokemonDetails(name : String){
        val request = PokemonDetailQuery.builder().name(name).build()

        job = GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = (activity?.application as App).apolloClient.query(request).toDeferred().await()
                updateUi(response.data())
            }
            catch (e : Exception){
                Log.d(TAG, e.localizedMessage)
            }
        }

    }

    private fun updateUi(data: PokemonDetailQuery.Data?) {
        showLoading(false)
        data?.detail()?.fragments()?.pokemonDetailFragment()?.let { poke ->
            pokeName.text = poke.name()
            pokeHeight.text = poke.height()?.maximum()
            pokeWeight.text = poke.weight()?.maximum()
            pokeNumber.text = poke.number()
            pokeClassification.text = poke.classification()
            pokeCp.text = poke.maxCP().toString()
            pokeType.text = poke.types()?.joinToString(" / ")
            context?.let { it1 -> Glide.with(it1).load(poke.image()).into(pokeImage) }

            specialAttackRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = poke.attacks()?.special()?.let { it -> AttackAdapter(it, context) }
                setHasFixedSize(true)
            }

            normalAttackRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = poke.attacks()?.fast()?.let { it -> AttackAdapter(it, context) }
                setHasFixedSize(true)
            }
        }

        data?.evolution()?.evolutions()?.get(0)?.let { poke ->
            if (!poke.name().isNullOrBlank()){
                pokeEvolution.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putString(POKEMON_NAME, poke.name())
                    findNavController().navigate(R.id.action_pokemonDetailFragment_self, bundle)
                }
            }
        }
        if (data?.evolution()?.evolutions()?.get(0) == null){
            pokeEvolution.setOnClickListener {
                Snackbar.make(it, "This is final form", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun showLoading(show : Boolean){
        if (show){
            loading.playAnimation()
            loading.visibility = View.VISIBLE
            pokeCard.visibility = View.GONE
        }
        else{
            loading.pauseAnimation()
            loading.visibility = View.GONE
            pokeCard.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        job?.cancel()
        super.onDestroy()
    }

    companion object{
        private const val TAG = "pokemonDetailFragment"
        private const val POKEMON_NAME = "pokeName"
    }
}
