package com.example.prisewatch.ui.home

import android.os.Bundle
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.prisewatch.R
import com.example.prisewatch.databinding.FragmentItemDetailBinding
import com.example.prisewatch.db.room.repo.ItemRepo
import com.example.prisewatch.domain.model.DTOUtils
import com.example.prisewatch.ui.vewmodels.ItemDetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemDetailFragment : Fragment() {
    private var _binding: FragmentItemDetailBinding? = null
    private val binding get() = _binding!!
    private var idItem: Long = 0
    private lateinit var img: ImageView
    private lateinit var title: TextView
    private lateinit var shop: TextView
    private val itemRepo = ItemRepo.get()
    //TODO график

    companion object {
        const val ID_ITEM = "id"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let {
            idItem = it.getLong(ID_ITEM)
        }

        _binding = FragmentItemDetailBinding.inflate(inflater, container, false)

        img = binding.frItemDetailImg
        title = binding.frItemDetailTitle
        shop = binding.frItemDetailShop

        setViewModel()

        return binding.root
    }

    private fun setViewModel() {
        val provider = ViewModelProviders.of(this)
        val viewModel = provider.get(ItemDetailViewModel::class.java)

        viewModel.item.observe(viewLifecycleOwner) {
            val item = DTOUtils.fromEntityToItem(it)

            Glide.with(requireContext())
                .load(item.imgUrl)
                .centerCrop()
                .placeholder(R.color.purple_700)
                .into(img)

            (requireActivity() as AppCompatActivity).supportActionBar?.title = item.title
            title.text = item.title
            shop.text = item.host
        }

        viewModel.findById(idItem)
    }
}