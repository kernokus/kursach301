package com.example.kursach301.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.kursach301.R
import com.example.kursach301.viewModels.TempHumidityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_sample_transmission.*
import kotlinx.android.synthetic.main.fragment_temperature.*

@AndroidEntryPoint
class TempHumidityFragment:Fragment() {
    private val tempHumViewModel: TempHumidityViewModel by viewModels()
    override fun onCreateView (
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_temperature,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        readInfoTV.setOnClickListener {
            tempHumViewModel.getTempAndHumidity()
        }


        tempHumViewModel.bufferForTempAndHum.observe(viewLifecycleOwner, object : Observer<String?> {
            override fun onChanged( s: String?) {
                if (s!=null) {
                    temperatureTV.text=s.toString()
                }

            }
        })
    }
}