package com.example.prisewatch.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.model.Item
import com.example.prisewatch.databinding.FragmentHomeBinding
import com.example.prisewatch.domain.model.DTOUtils
import com.example.prisewatch.ui.vewmodels.ItemViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recycler = binding.homeFrRecycler
        val adapter = ItemAdapter(listOf())
        val linearLayoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter
        recycler.layoutManager = linearLayoutManager


        val provider = ViewModelProviders.of(this)
        val viewmodel = provider.get(ItemViewModel::class.java)

        viewmodel.itemList.observe(viewLifecycleOwner) {
            adapter.list = it.map {itemWithPrices -> DTOUtils.fromEntityToItem(itemWithPrices)  }
            adapter.notifyDataSetChanged()
        }

      //  viewmodel.getAllItems()

        // кнопка
        val btn = binding.btn
        btn.setOnClickListener({
            Log.d("TAG", "CLICK")
            viewmodel.testAdd()
        })
        //


        // lifecycleScope.launch(Dispatchers.IO)  {
        /*   val fabric = ParserFabric()
           val parserByDomain = fabric.getParserByDomain("www.dns-shop.ru")
           val item =
               parserByDomain.getParsedItem("https://www.dns-shop.ru/product/d00d5ac5b4852eb1/videokarta-gigabyte-geforce-rtx-3070-ti-aorus-master-gv-n307taorus-m-8gd/")
           Log.d("TAG", "$item.title ${item.price}")*/
        /*  val fabric = ParserFabric()
           val parserByDomain = fabric.getParserByDomain("www.polus.ru")
           val item =
               //parserByDomain.getParsedItem("https://www.citilink.ru/product/pamyat-ddr4-16gb-2666mhz-crucial-cb16gs2666-oem-pc4-21300-cl16-so-dimm-1430548/")
               parserByDomain.getParsedItem("https://polus.su/noutbuki/haier/pk-office-515-mt-i5-9400-8gb-ssd500gb-uhdg-630-free-dos-gbiteth-500w-klaviatura-mysh-chernyy--14")
           */

        //  }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}