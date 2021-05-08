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
import com.example.kursach301.databinding.FragmentDs18b20Binding
import com.example.kursach301.viewModels.DHT11ViewModel
import com.example.kursach301.viewModels.DS18B20ViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DS18B20Fragment:Fragment() {
    private val ds18b20ViewModel: DS18B20ViewModel by viewModels()
    private lateinit var binding : FragmentDs18b20Binding
    override fun onCreateView (
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ds18b20, container, false)
        binding.viewmodel = ds18b20ViewModel
        binding.lifecycleOwner=this
        return binding.root
    }
}