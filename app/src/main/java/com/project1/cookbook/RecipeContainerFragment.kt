package com.project1.cookbook

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.project1.cookbook.networking.RetrofitInstance
import kotlinx.coroutines.launch


class RecipeContainerFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipe_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDetails()
    }

    fun getDetails() {
        lifecycleScope.launch {
            try {
                val call = RetrofitInstance().apiServiceInterface.extractRecipe("main course")
                val text = view?.findViewById<TextView>(R.id.tv_recipeShow)
                text?.text = call.results[2].title
                val image = view?.findViewById<ImageView>(R.id.iv_recipeShow)
                if (image != null) {
                    context?.let { Glide.with(it).load(call.results[2].image).into(image) }
                }


            } catch (e: Exception) {
                Log.d("Fragment", "ERROR ${e.message}")
            }
        }
    }

}