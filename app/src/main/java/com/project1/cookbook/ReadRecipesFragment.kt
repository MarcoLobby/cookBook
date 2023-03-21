package com.project1.cookbook


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project1.cookbook.databinding.FragmentReadRecipesBinding
import com.project1.cookbook.networking.RetrofitInstance
import kotlinx.coroutines.launch


class ReadRecipesFragment : androidx.fragment.app.Fragment() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var dishesArrayList: ArrayList<Dishes>
    private lateinit var adapter: DishesAdapter
    private lateinit var btnImageID: Array<Int>


    private var _binding: FragmentReadRecipesBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentReadRecipesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInitialized()
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.rvReadRecipes)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = DishesAdapter(dishesArrayList) {
            findNavController().navigate(R.id.action_ReadRecipesFragment_to_recipeContainerFragment)
        }
        recyclerView.adapter = adapter



        getDetails()

    }

    private fun getDetails() {
        lifecycleScope.launch {
            try {
                val call = RetrofitInstance().apiServiceInterface.extractRecipe("main course")
                Log.d("Succes", "Call ${call.results.size}")

            } catch (e: Exception) {
                Log.d("Fragment", "ERROR ${e.message}")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun dataInitialized() {
        dishesArrayList = arrayListOf()
        btnImageID = arrayOf(
            R.drawable.starters_background,
            R.drawable.appetizers,
            R.drawable.main_dish_pasta,
            R.drawable.second_dish,
            R.drawable.desserts_background,
            R.drawable.drinks_background
        )
        val dishTitles =
            arrayOf("Starters", "Appetizer", "Main Dishes", "Second Dishes", "Dessert", "Drinks")
        val backgroundID = arrayOf(
            R.drawable.starters_background,
            R.drawable.appetizers,
            R.drawable.main_dish_pasta,
            R.drawable.second_dish,
            R.drawable.desserts_background,
            R.drawable.drinks_background
        )
        for (i in btnImageID.indices) {
            val item = Dishes(btnImageID[i], dishTitles[i], backgroundID[i])
            dishesArrayList.add(item)

        }


    }
}