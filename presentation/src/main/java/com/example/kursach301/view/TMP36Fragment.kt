package com.example.kursach301.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.kursach301.R

import com.example.kursach301.databinding.FragmentTmp36Binding
import com.example.kursach301.viewModels.TMP36ViewModel
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class TMP36Fragment:Fragment() {
    private val tmP36ViewModel: TMP36ViewModel by viewModels()
    private lateinit var binding : FragmentTmp36Binding

    override fun onCreateView (
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tmp36, container, false)
        binding.viewmodel = tmP36ViewModel
        binding.lifecycleOwner=this
        return binding.root
    }
}