package com.project1.cookbook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class DishesAdapter(private val list: List<Dishes>, private val onClick: () -> Unit) :
    RecyclerView.Adapter<DishesAdapter.DishesViewHolder>() {

    class DishesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val btnChooseDish: Button = itemView.findViewById(R.id.btnChooseDish)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishesViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.dishes, parent, false)
        return DishesViewHolder(itemView)
    }

    override fun getItemCount(): Int {

        return list.size
    }

    override fun onBindViewHolder(holder: DishesViewHolder, position: Int) {

        val currentItem = list[position]
        holder.btnChooseDish.background =
            ContextCompat.getDrawable(holder.itemView.context, currentItem.background)
        holder.btnChooseDish.text = currentItem.title
        holder.btnChooseDish.setOnClickListener {
            onClick()
        }

    }

}