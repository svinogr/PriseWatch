package com.example.prisewatch.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.prisewatch.databinding.FragmentItemDetailBinding

class ItemDetailFragment : Fragment() {
    private var _binding: FragmentItemDetailBinding? = null
    private val binding get() = _binding!!
    private var idItem: Int? = null

    companion object {
        const val ID_ITEM = "id"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        arguments.let {
            idItem = arguments?.getInt(ID_ITEM)
        }

        binding.frItemDetailTitle.text = idItem.toString()

        val root = binding.root
        return root
    }
}