package com.example.kursach301.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.kursach301.R
import com.example.kursach301.databinding.FragmentDht11Binding
import com.example.kursach301.viewModels.DHT11ViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DHT11Fragment:Fragment() {
    private val dhT11ViewModel: DHT11ViewModel by viewModels()
    private lateinit var binding :FragmentDht11Binding
    override fun onCreateView (
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dht11, container, false)
        binding.viewmodel = dhT11ViewModel
        binding.lifecycleOwner=this
        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        readInfoTV.setOnClickListener {
//            tempHumViewModel.getTempAndHumidity()
//        }
//
//
//        tempHumViewModel.bufferForTempAndHum.observe(viewLifecycleOwner,
//            { s ->
//                if (s!=null) {
//                    temperatureTV.text=s.toString()
//                }
//            })
//    }
}