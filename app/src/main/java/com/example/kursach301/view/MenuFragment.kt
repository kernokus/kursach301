package com.example.kursach301.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kursach301.R
import com.example.kursach301.ViewPager2Adapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_menu.*

class MenuFragment:Fragment() {
    override fun onCreateView (
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu,container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager.adapter=ViewPager2Adapter(this)
        viewPager.isUserInputEnabled=false //отключает свайпы не по табу
        TabLayoutMediator(tabs, viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 -> { tab.text = "HelloWorld"

                    //tab.icon= activity?.getDrawable(R.drawable.temperature_view_pager_1_icon)
                        }
                    1 -> { tab.text = "TempHumid"}
                    2->  {tab.text="Резерв"}
                }
            }).attach()
    }
}