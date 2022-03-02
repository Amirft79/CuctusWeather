package com.example.co.cuctusweather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.co.cuctusweather.databinding.FragmentSearchCityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchCityFragment : Fragment() {
    private var _Binding:FragmentSearchCityBinding?=null
    private val binding get() = _Binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
    _Binding= FragmentSearchCityBinding.inflate(layoutInflater,container,false)
        val view=binding.root
        binding.btnSearch.setOnClickListener {

            val action = Navigation.findNavController(view)
                .navigate(R.id.action_searchCityFragment_to_weatherResultFragment)
        }



        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _Binding=null
    }
}