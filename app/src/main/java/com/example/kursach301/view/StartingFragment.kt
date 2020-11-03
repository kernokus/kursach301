package com.example.kursach301.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.kursach301.R
import com.example.kursach301.viewModels.SampleTransmissionViewModel
import com.example.kursach301.viewModels.StartingViewModel
import kotlinx.android.synthetic.main.fragment_starting.*
import splitties.permissions.requestPermission

class StartingFragment:Fragment() {
    private val startingViewModel: StartingViewModel by viewModels()


   override fun onCreateView (
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_starting,container,false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startingViewModel.requestPermissionsInFragment(this) //запрос разрешения на Bluetooth
        continueBtn.setOnClickListener {
            findNavController().navigate(R.id.menuFragment)
        }

        settingBtn.setOnClickListener {
            findNavController().navigate(R.id.settingFragment)
        }

    }




}