package com.example.prisewatch.ui.share

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.prisewatch.R
import com.example.prisewatch.databinding.FragmentShareBinding
import com.example.prisewatch.ui.home.ItemDetailFragment
import com.example.prisewatch.ui.vewmodels.ShareItemModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ShareFragment : Fragment() {
    private lateinit var fabBtn: FloatingActionButton
    private lateinit var img: ImageView
    private lateinit var shop: TextView
    private lateinit var title: TextView
    private lateinit var viemodel: ShareItemModel
    private  var _binding: FragmentShareBinding? = null
    private val binding get() =  _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentShareBinding.inflate(inflater, container, false)
        Log.d("TAG", "share fragment")
        setupViews()
        setupViewModel()
        setupListeners()
        val data = getDataFromIntetnt(requireActivity().intent)
        viemodel.getItem(data)
        return binding.root
    }

  /*  override fun onAttach(context: Context) {
        super.onAttach(context)

        Log.d("TAG", data)
    }*/

    private fun setupListeners() {
        fabBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                Log.d("TAG", "fab press")
             //   viemodel.saveToDb()
                Navigation.findNavController(binding.root).navigate(
                    R.id.action_shareFragment_to_nav_home
                )
            //Toast.makeText(applicationContext, "YYYY", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setupViewModel() {
        val provider = ViewModelProviders.of(this)
        viemodel = provider.get(ShareItemModel::class.java)

        viemodel.item.observe(viewLifecycleOwner) {
            title.text = it.title
            shop.text = it.host
            val imgUrl = it.host + it.imgLink
            Log.d("TAG", "img url $imgUrl")

            Glide.with(this)
                .load(imgUrl)
                .centerCrop()
                .placeholder(R.color.purple_700)
                .into(img)
        }

        viemodel.progress.observe(viewLifecycleOwner) {
            fabBtn.isEnabled = !it
        }
    }

    private fun setupViews() {
        title = binding.acShareTitle
        shop = binding.acShareShop
        img = binding.acShareImg
        fabBtn = binding.acShareFabFab

    }

    private fun getDataFromIntetnt(intent: Intent?): String {
        return when (intent?.action) {
            Intent.ACTION_SEND -> {
                return intent.getStringExtra(Intent.EXTRA_TEXT).toString()
            }
            else -> ""
        }
    }
}