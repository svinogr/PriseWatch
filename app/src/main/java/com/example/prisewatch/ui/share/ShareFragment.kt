package com.example.prisewatch.ui.share

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.prisewatch.MainActivity
import com.example.prisewatch.R
import com.example.prisewatch.databinding.FragmentShareBinding
import com.example.prisewatch.ui.home.ItemDetailFragment
import com.example.prisewatch.ui.vewmodels.ShareItemModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ShareFragment : Fragment() {
    private lateinit var progress: ProgressBar
    private lateinit var fabBtn: FloatingActionButton
    private lateinit var img: ImageView
    private lateinit var shop: TextView
    private lateinit var title: TextView
    private lateinit var viemodel: ShareItemModel
    private var _binding: FragmentShareBinding? = null
    private val binding get() = _binding!!

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

    private fun setupListeners() {
        fabBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                Log.d("TAG", "fab press share")
                viemodel.saveToDb()
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
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
            (requireActivity() as AppCompatActivity).supportActionBar?.title = it.title
            Log.d("TAG", "img url $imgUrl")

            Glide.with(this)
                .load(imgUrl)
                .centerCrop()
                .placeholder(R.color.purple_700)
                .into(img)
        }

        viemodel.progress.observe(viewLifecycleOwner) {
            fabBtn.isEnabled = !it
            if (it) {
                progress.visibility = View.VISIBLE
            } else {
                progress.visibility = View.GONE
            }
        }
    }

    private fun setupViews() {
        title = binding.acShareTitle
        shop = binding.acShareShop
        img = binding.acShareImg
        fabBtn = binding.acShareFabFab
        progress = binding.homeFrProgress.root
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