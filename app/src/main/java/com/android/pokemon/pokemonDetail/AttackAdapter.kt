package com.android.pokemon.pokemonDetail

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.android.pokemon.R
import com.apollographql.apollo.queries.fragment.PokemonDetailFragment
import kotlinx.android.synthetic.main.item_attack.view.*

class AttackAdapter<T>(private val list: List<T>, private val context: Context) :
    RecyclerView.Adapter<AttackAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_attack, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position], context)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun <T> bind(attack: T, context : Context) {
            if (attack is PokemonDetailFragment.Special) {
                type.text = attack.type()
                name.text = attack.name()
                damage.text = attack.damage().toString()
                val typeBg = type.background as GradientDrawable
                typeBg.setColor(ContextCompat.getColor(context, getTypeColor(attack.type()?.toLowerCase())))
            }
            if (attack is PokemonDetailFragment.Fast) {
                type.text = attack.type()
                name.text = attack.name()
                damage.text = attack.damage().toString()
                val typeBg = type.background as GradientDrawable
                typeBg.setColor(ContextCompat.getColor(context, getTypeColor(attack.type()?.toLowerCase())))
            }
        }

        val type: TextView = itemView.attackType
        val name: TextView = itemView.attackName
        val damage: TextView = itemView.attackDamage
    }

    companion object {
        private fun getTypeColor(type: String?): Int {
            return when (type) {
                "electric" -> R.color.electric
                "normal" -> R.color.normal
                "fire" -> R.color.fire
                "water" -> R.color.water
                "grass" -> R.color.grass
                "ice" -> R.color.ice
                "fighting" -> R.color.fighting
                "poison" -> R.color.poison
                "ground" -> R.color.ground
                "flying" -> R.color.flying
                "psychic" -> R.color.psychic
                "bug" -> R.color.bug
                "rock" -> R.color.rock
                "ghost" -> R.color.ghost
                "dark" -> R.color.dark
                "dragon" -> R.color.dragon
                "steel" -> R.color.steel
                "fairy" -> R.color.fairy
                else -> R.color.normal
            }
        }
    }
}