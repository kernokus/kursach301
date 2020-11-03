package com.example.kursach301.view

import android.R.attr.data
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.kursach301.R
import com.example.kursach301.viewModels.SampleTransmissionViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_sample_transmission.*


@AndroidEntryPoint
class SampleTransmissionFragment:Fragment() {
    private val transmisionViewModel: SampleTransmissionViewModel by viewModels()

    override fun onCreateView (
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sample_transmission,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        connectMK_btn.setOnClickListener {
            transmisionViewModel.connect()
        }
        writeMK_btn.setOnClickListener {
            transmisionViewModel.send()
        }
        readMK_btn.setOnClickListener {
            transmisionViewModel.read()
        }
        disconnectBtn.setOnClickListener {
            transmisionViewModel.disconnect()
        }

        transmisionViewModel.bufferLD.observe(viewLifecycleOwner, object : Observer<String?> {
            override fun onChanged( s: String?) {
                if (s!=null) {
                Log.d("fragment",s.toString())
                bufferTV.visibility=View.VISIBLE
                bufferTV.text= s.toString()
                }

            }
        })

//        GlobalScope.launch(Dispatchers.Main) {
//            this@SampleTransmissionFragment.requestPermissionsInFragment()
//        }

    }




}