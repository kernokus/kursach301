package com.example.mylibrary.repo

import com.example.mylibrary.bleService.bleService
import com.example.mylibrary.mappers.ShareMapper
import javax.inject.Inject

class RemoteRepoImpl @Inject constructor(private val bleService: bleService, private val shareMapper: ShareMapper) :RemoteR{

}