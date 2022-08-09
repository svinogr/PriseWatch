package com.example.prisewatch.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.model.Item
import com.example.prisewatch.databinding.FragmentHomeBinding
import com.example.prisewatch.domain.ImplParserFabricable.ParserFabric
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        lifecycleScope.launch(Dispatchers.IO)  {
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
             val fabric = ParserFabric()
            val parserByDomain = fabric.getParserByDomain("www.kasla.ru")
            val item =
                //parserByDomain.getParsedItem("https://www.citilink.ru/product/pamyat-ddr4-16gb-2666mhz-crucial-cb16gs2666-oem-pc4-21300-cl16-so-dimm-1430548/")
                parserByDomain.getParsedItem("https://kasla.ru/catalog/accessories/zaryadnye_ustroystva_i_adaptery/avtomobilnoe_zaryadnoe_ustroystvo_anker_power_drive_2_ru.html?offerID=5339")

            Log.d("TAG", "$item")
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}