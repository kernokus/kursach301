package com.example.kursach301.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.kursach301.R
import com.example.kursach301.base.ViewPager2Adapter
import com.example.kursach301.databinding.FragmentMenuBinding
import com.example.kursach301.databinding.FragmentSampleTransmissionBinding
import com.google.android.material.tabs.TabLayoutMediator


class MenuFragment:Fragment() {
    private lateinit var binding : FragmentMenuBinding
    override fun onCreateView (
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter= ViewPager2Adapter(this)
        binding.viewPager.isUserInputEnabled=false //отключает свайпы не по табу
        TabLayoutMediator(binding.tabs, binding.viewPager
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "HelloWorld" //tab.icon= activity?.getDrawable(R.drawable.temperature_view_pager_1_icon)
                     }
                1 -> { tab.text = "DHT11" }
                2 -> { tab.text = "Резерв" }
            }
        }.attach()
    }
}