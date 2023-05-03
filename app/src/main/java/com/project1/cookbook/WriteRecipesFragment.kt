package com.project1.cookbook

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.project1.cookbook.databinding.FragmentWriteRecipesBinding


class WriteRecipesFragment : Fragment() {

    private var _binding: FragmentWriteRecipesBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWriteRecipesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.actionButton.setOnClickListener {
            binding.tvTitleShow.text = binding.tvInsertTitle.text

        }

        loadData()

        binding.actionButton.setOnClickListener {
            saveData()
        }


        //MIN CHAR LENGTH TO LET CONFIRM BUTTON ENABLE
        binding.tvInsertTitle.doOnTextChanged { text, start, before, count ->
            Log.v(
                "WriteRecipes",
                "insert title: text = ${text}, start = ${start}, before= ${before}, count= $count"
            )
            text?.let {
                binding.actionButton.isEnabled = it.length >= 3
            }
        }
    }

    private fun saveData() {
        val inputCamp = binding.tvInsertTitle.text.toString()
        binding.tvTitleShow.text = inputCamp

        val sharedPreferences : SharedPreferences = requireContext().getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        editor.apply {
            putString("KEY_STRING", inputCamp)
        }.apply()

    }

    private fun loadData(){
        val sharedPreferences : SharedPreferences = requireContext().getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        val savedString : String? = sharedPreferences.getString("KEY_STRING", null)


        binding.tvTitleShow.text = savedString

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}