package com.project1.cookbook

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.project1.cookbook.databinding.FragmentWriteRecipesBinding


class WriteRecipesFragment : Fragment() {

    private var _binding: FragmentWriteRecipesBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWriteRecipesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.actionButton.setOnClickListener {
            binding.tvTitleShow.text = binding.tvInsertTitle.text
            Snackbar.make(binding.actionButton, binding.tvInsertTitle.text, Snackbar.LENGTH_INDEFINITE).show()
        }

        //MIN CHAR LENGTH TO LET CONFIRM BUTTON ENABLE
        binding.tvInsertTitle.doOnTextChanged { text, start, before, count ->
            Log.v("WriteRecipes","insert title: text = ${text}, start = ${start}, before= ${before}, count= $count")
            text?.let {
                binding.actionButton.isEnabled = it.length >=3
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}