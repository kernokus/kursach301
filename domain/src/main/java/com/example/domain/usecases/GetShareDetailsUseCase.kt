package com.example.domain.usecases

import com.example.domain.models.deviceModel
import com.example.domain.repositories.RemoteRepo
import javax.inject.Inject

class GetShareDetailsUseCase @Inject constructor(val apiRepo: RemoteRepo) :
    SingleUseCase<deviceModel> {

    override fun execute(): List<deviceModel> {
        return apiRepo.getShareDetails()

    }
}