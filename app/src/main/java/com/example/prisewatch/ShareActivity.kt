package com.example.prisewatch

import android.content.Intent
import android.content.SearchRecentSuggestionsProvider
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.bumptech.glide.Glide
import com.example.prisewatch.databinding.ActivityShareBinding
import com.example.prisewatch.ui.vewmodels.ShareItemModel

class ShareActivity : AppCompatActivity() {
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

        title = binding.acShareTitle
        shop = binding.acShareShop
        img = binding.acShareImg

        Log.d("TAG", title.text.toString())

        val provider = ViewModelProviders.of(this)
        val viemodel = provider.get(ShareItemModel::class.java)

        viemodel.item.observe(this) {
            title.text = it.title
            shop.text = it.host

            Glide.with(this)
                .load(it.imgUrl)
                .centerCrop()
                .placeholder(R.color.purple_700)
                .into(img)
        }

      //  viemodel.parse(data)
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