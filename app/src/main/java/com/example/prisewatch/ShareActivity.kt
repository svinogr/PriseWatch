package com.example.prisewatch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.prisewatch.databinding.ActivityShareBinding
import com.example.prisewatch.ui.vewmodels.ShareItemModel

class ShareActivity : AppCompatActivity() {
    private lateinit var viemodel: ShareItemModel
    private var _binding: ActivityShareBinding? = null
    private val binding get() = _binding!!

    private lateinit var title: TextView
    private lateinit var shop: TextView
    private lateinit var img: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityShareBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = getDataFromIntetnt(intent)
        Log.d("TAG", data)
        setupViews()
        setupViewModel()
        viemodel.getItem(data)
    }

    private fun setupViewModel() {
        val provider = ViewModelProviders.of(this)
        viemodel = provider.get(ShareItemModel::class.java)

        viemodel.item.observe(this) {
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
    }

    private fun setupViews() {
        title = binding.acShareTitle
        shop = binding.acShareShop
        img = binding.acShareImg
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