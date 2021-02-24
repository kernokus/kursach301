package com.example.kursach301.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.kursach301.R
import com.example.kursach301.databinding.FragmentSampleTransmissionBinding
import com.example.kursach301.databinding.FragmentStartingBinding
import com.example.kursach301.viewModels.StartingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartingFragment:Fragment() {
    private val startingViewModel: StartingViewModel by viewModels()
    private lateinit var binding : FragmentStartingBinding

   override fun onCreateView (
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = DataBindingUtil.inflate(inflater, R.layout.fragment_starting, container, false)
       binding.viewmodel = startingViewModel
       return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startingViewModel.requestPermissionsInFragment(this) //запрос разрешения на Bluetooth
        binding.continueBtn.setOnClickListener {
            findNavController().navigate(R.id.menuFragment)
        }
        binding.settingBtn.setOnClickListener {
            findNavController().navigate(R.id.settingFragment)
        }
    }
}