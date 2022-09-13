package com.example.prisewatch.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prisewatch.databinding.FragmentHomeBinding
import com.example.prisewatch.ui.vewmodels.ItemViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment() {
    private lateinit var viewmodel: ItemViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemAdapter
    private lateinit var fabBtn: FloatingActionButton
    private lateinit var progress: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        setupViews()
        setupViewModel()
        setupButton()

        viewmodel.getListItemsById()

        return binding.root
    }

    private fun setupButton() {
        val btn = binding.homeFrFab
        btn.setOnClickListener {
            startAddDiaolog()
        }
    }

    private fun startAddDiaolog() {
        Log.d("TAG", "Start add url dialog")
    }

    private fun setupViewModel() {
        val provider = ViewModelProviders.of(this)
        viewmodel = provider.get(ItemViewModel::class.java)

        viewmodel.itemList.observe(viewLifecycleOwner) {
            adapter.list = it
            adapter.notifyDataSetChanged()
        }

        viewmodel.progress.observe(viewLifecycleOwner) {
            progressBarVisible(it)
        }
    }

    private fun progressBarVisible(progressRun: Boolean) {
        if (progressRun) {
            progress.visibility = View.VISIBLE
        } else {
            progress.visibility = View.GONE
        }
    }

    private fun setupViews() {
        recyclerView = binding.homeFrRecycler
        adapter = ItemAdapter(listOf())
        val linearLayoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = linearLayoutManager

        fabBtn = binding.homeFrFab

        progress = binding.homeFrProgress
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}