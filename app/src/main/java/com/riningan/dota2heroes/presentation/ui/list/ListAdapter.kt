package com.riningan.dota2heroes.presentation.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.riningan.dota2heroes.R
import com.riningan.dota2heroes.data.model.Hero
import kotlinx.android.synthetic.main.item_list.view.*


class ListAdapter(private val mListener: OnItemClickListener) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    private val mHeroes = arrayListOf<Hero>()


    override fun getItemCount() = mHeroes.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mHeroes[position])
    }


    fun setHeroes(heroes: List<Hero>) {
        mHeroes.apply {
            clear()
            addAll(heroes)
        }
    }


    interface OnItemClickListener {
        fun onItemClick(hero: Hero)
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var mHero: Hero


        init {
            view.setOnClickListener { mListener.onItemClick(mHero) }
        }


        fun bind(hero: Hero) {
            mHero = hero
            itemView.tvListItemTitle.text = hero.localizedName
            itemView.tvListItemMessage.text = hero.name
        }
    }
}