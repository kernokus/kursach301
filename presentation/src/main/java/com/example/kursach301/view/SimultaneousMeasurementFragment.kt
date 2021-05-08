package com.example.kursach301.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.kursach301.R
import com.example.kursach301.databinding.FragmentSimultaneousMeasurementBinding
import com.example.kursach301.databinding.FragmentTmp36Binding
import com.example.kursach301.viewModels.SimultaneousMeasurementViewModel
import com.example.kursach301.viewModels.TMP36ViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SimultaneousMeasurementFragment: Fragment() {
    private val simultMeasurementViewModel: SimultaneousMeasurementViewModel by viewModels()
    private lateinit var binding : FragmentSimultaneousMeasurementBinding

    override fun onCreateView (
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_simultaneous_measurement, container, false)
        binding.viewmodel = simultMeasurementViewModel
        binding.lifecycleOwner=this
        return binding.root
    }
}