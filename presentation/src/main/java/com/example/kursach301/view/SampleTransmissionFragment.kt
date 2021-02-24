package com.example.kursach301.view

import android.R.attr.data
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.kursach301.R
import com.example.kursach301.databinding.FragmentSampleTransmissionBinding
import com.example.kursach301.viewModels.SampleTransmissionViewModel
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class SampleTransmissionFragment:Fragment() {
    private val transmisionViewModel: SampleTransmissionViewModel by viewModels()
    private lateinit var binding : FragmentSampleTransmissionBinding

    override fun onCreateView (
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sample_transmission, container, false)
        binding.viewmodel = transmisionViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        transmisionViewModel.bufferLD.observe(viewLifecycleOwner, object : Observer<String?> {
//            override fun onChanged( s: String?) {
//                if (s!=null) {
//                Log.d("fragment",s.toString())
//                bufferTV.visibility=View.VISIBLE
//                bufferTV.text= s.toString()
//                }
//
//            }
//        })

    }




}