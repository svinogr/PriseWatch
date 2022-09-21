package com.example.prisewatch.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.myapplication.model.Item
import com.example.prisewatch.R
import com.example.prisewatch.databinding.FragmentItemDetailBinding
import com.example.prisewatch.databinding.ProgressBinding
import com.example.prisewatch.domain.model.DTOUtils
import com.example.prisewatch.ui.vewmodels.ItemDetailViewModel
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import java.util.*

class ItemDetailFragment : Fragment() {
    private lateinit var col_layout: CollapsingToolbarLayout
    private lateinit var progress: ProgressBar
    private lateinit var graph: LineChart
    private var _binding: FragmentItemDetailBinding? = null
    private val binding get() = _binding!!
    private var idItem: Long = 0
    private lateinit var img: ImageView
 //   private lateinit var toolbar: Toolbar
    private lateinit var shop: TextView
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

        setupViews()
        setViewModel()

        return binding.root
    }

    private fun setupExtraData() {
        arguments?.let {
            idItem = it.getLong(ID_ITEM)
        }
    }

    private fun setupViews() {
        img = binding.frItemDetailImg
       // toolbar = binding.frItemDetailToolbar
        shop = binding.frItemDetailShop
        graph = binding.frItemDetailGraph
        progress = binding.frItemDetailProgress.root

      // (activity as AppCompatActivity).setSupportActionBar(toolbar)
      // requireActivity().supportActionBar?.setDisplayHomeAsUpEnabled(true)

        col_layout = binding.frItemDetailColBarLay
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

        //    (requireActivity() as AppCompatActivity).supportActionBar?.title = item.title
            col_layout.title = item.title
            shop.text = item.host
            createGraph(item)
        }
        viewModel.progress.observe(viewLifecycleOwner) {
            progressBarVisible(it)
        }

        viewModel.findById(idItem)
    }

    private fun createGraph(item: Item) {
        Log.d("TAG", " in graf $item")
        val listPrices = item.priceList
        val arrEntry = arrayListOf<Entry>()
        var i = 0f
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
            Log.d("TAG", "entry v cycle ${entry.x} ${entry.y} ")
        }
        val arrDescr = arrDescription.toArray()
        Log.d("TAG", "graf arr ${Arrays.toString(arrDescr)}")

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

    private fun progressBarVisible(isVisible: Boolean) {
        if (isVisible) {
            progress.visibility = View.VISIBLE
        } else {
            progress.visibility = View.GONE
        }
    }
}