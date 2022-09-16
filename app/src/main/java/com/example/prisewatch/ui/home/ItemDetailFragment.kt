package com.example.prisewatch.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.viewmodel.CreationExtras
import com.bumptech.glide.Glide
import com.example.myapplication.model.Item
import com.example.prisewatch.R
import com.example.prisewatch.databinding.FragmentItemDetailBinding
import com.example.prisewatch.db.room.repo.ItemRepo
import com.example.prisewatch.domain.model.DTOUtils
import com.example.prisewatch.ui.vewmodels.ItemDetailViewModel
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import java.util.*

class ItemDetailFragment : Fragment() {
    private lateinit var graph: LineChart
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

        _binding = FragmentItemDetailBinding.inflate(inflater, container, false)

        setupExtraData()

        setuprViews()
        setViewModel()

        return binding.root
    }

    private fun setupExtraData() {
        arguments?.let {
            idItem = it.getLong(ID_ITEM)
        }
    }

    private fun setuprViews() {
        img = binding.frItemDetailImg
        title = binding.frItemDetailTitle
        shop = binding.frItemDetailShop
        graph = binding.frItemDetailGraph
    }

    private fun setViewModel() {
        val provider = ViewModelProviders.of(this)
        val viewModel = provider.get(ItemDetailViewModel::class.java)

        viewModel.item.observe(viewLifecycleOwner) {
            val item = DTOUtils.fromEntityToItem(it)
            Log.d("TAG", item.toString())
            Log.d("TAG", item.fullLinImg())
            Glide.with(requireContext())
                .load(item.fullLinImg())
                .centerCrop()
                .placeholder(R.color.purple_700)
                .into(img)

            (requireActivity() as AppCompatActivity).supportActionBar?.title = item.title
            title.text = item.title
            shop.text = item.host

            createGraph(item)
        }

        viewModel.findById(idItem)
    }

    private fun createGraph(item: Item) {
        val listPrices = item.priceList
        val arrEntry = arrayListOf<Entry>()
        var i = 0
        val arrDescription = arrayListOf<String>()
        Log.d("TAG", listPrices.size.toString())
        listPrices.forEach {
            val calendar = Calendar.getInstance()
            calendar.time = Date(it.date)

            arrDescription.add(
                "${calendar.get(Calendar.DAY_OF_MONTH)}." +
                        "${calendar.get(Calendar.MONTH)}." +
                        "${calendar.get(Calendar.YEAR)}"
            )

            val entry = Entry(i.toFloat(), it.price.toFloat())
            arrEntry.add(entry)
            i++
            Log.d("TAG", "i v cycle $i")
        }
        val arrDescr  = arrDescription.toArray()
        val formator = object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                Log.d("TAG", "value form graf is $value")
                return arrDescr[value.toInt()].toString()
            }
        }

        val lineDataSet = LineDataSet(arrEntry, "грфик")
        val lineData = LineData(lineDataSet)
        graph.data = lineData
        val xAxis = graph.xAxis
        xAxis.valueFormatter = formator
        xAxis.granularity = 1f
        graph.invalidate()

    }

    override fun getDefaultViewModelCreationExtras(): CreationExtras {
        return super.getDefaultViewModelCreationExtras()
    }
}