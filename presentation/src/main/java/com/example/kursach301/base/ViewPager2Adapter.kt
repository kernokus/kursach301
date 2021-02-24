package com.example.kursach301.base

import androidx.fragment.app.Fragment

import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kursach301.view.SampleTransmissionFragment
import com.example.kursach301.view.SettingFragment
import com.example.kursach301.view.DHT11Fragment

class ViewPager2Adapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount()=3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SampleTransmissionFragment()
            1 -> DHT11Fragment()
            else -> return SettingFragment()
        }
    }

}