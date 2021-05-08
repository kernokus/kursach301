package com.example.kursach301.base

import androidx.fragment.app.Fragment

import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kursach301.view.TMP36Fragment
import com.example.kursach301.view.DS18B20Fragment
import com.example.kursach301.view.DHT11Fragment

class ViewPager2Adapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount()=3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            1 -> TMP36Fragment()
            0 -> DHT11Fragment()
            else -> return DS18B20Fragment()
        }
    }

}