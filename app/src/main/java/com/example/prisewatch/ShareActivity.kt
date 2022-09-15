package com.example.prisewatch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.example.prisewatch.databinding.ActivityShareBinding
import com.example.prisewatch.ui.vewmodels.ShareItemModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ShareActivity : AppCompatActivity() {
    private var _binding: ActivityShareBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TAG", "ShareActivity")
        _binding = ActivityShareBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}