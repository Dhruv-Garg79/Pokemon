package com.android.pokemon.Pokemons

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.android.pokemon.App
import com.android.pokemon.R
import com.apollographql.apollo.coroutines.toDeferred
import com.apollographql.apollo.queries.PokemonsQuery
import kotlinx.android.synthetic.main.fragment_pokemons.*
import kotlinx.android.synthetic.main.fragment_pokemons.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class PokemonsFragment : Fragment() {

    private var job : Job = Job()
    private val coroutineContext : CoroutineContext = job + Dispatchers.Main
    private lateinit var pokemonsAdapter: PokemonsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_pokemons, container, false)
        root.pokemonsLoading.playAnimation()
        root.pokemonsRecyclerView.visibility = View.GONE

        context?.let {context ->
            pokemonsAdapter = PokemonsAdapter(context){
                val direction = PokemonsFragmentDirections.actionPokemonsFragmentToPokemonDetailFragment(it)
                findNavController().navigate(direction)
            }
        }
        val spacing = resources.getDimension(R.dimen.itemSpacing).toInt()
        root.pokemonsRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 1)
            adapter = pokemonsAdapter
        }
        fetchPokemons(231)
//        chip.setOnClickListener {
//
//        }
//        chip.setOnCheckedChangeListener { buttonView, isChecked ->
//
//        }
        return root
    }

    private fun fetchPokemons(count : Int){
        val query = PokemonsQuery.builder().i(count).build()

        CoroutineScope(coroutineContext).launch(Dispatchers.Main) {
            try {
                val response = (activity?.application as App).apolloClient.query(query).toDeferred().await()
                pokemonsAdapter.updateList(response.data()?.pokemons())
                showLoading(false)
            }
            catch (e : Exception){
                e.localizedMessage?.let {
                    Log.d(TAG, it)
                }
            }
        }
    }

    private fun showLoading(show : Boolean){
        if (show){
            pokemonsLoading.playAnimation()
            pokemonsLoading.visibility = View.VISIBLE
            pokemonsRecyclerView.visibility = View.GONE
        }
        else{
            pokemonsLoading.pauseAnimation()
            pokemonsLoading.visibility = View.GONE
            pokemonsRecyclerView.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }

    companion object{
        private const val TAG = "pokemonsFragment"
    }
}
