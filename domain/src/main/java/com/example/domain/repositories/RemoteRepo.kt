package com.example.domain.repositories

import com.example.domain.models.deviceModel

interface RemoteRepo {


        fun getShareDetails(): List<deviceModel> //тут был Single

}