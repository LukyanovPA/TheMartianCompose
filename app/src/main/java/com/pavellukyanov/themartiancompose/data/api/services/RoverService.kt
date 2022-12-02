package com.pavellukyanov.themartiancompose.data.api.services

import com.pavellukyanov.themartiancompose.data.api.util.ApiParams
import com.pavellukyanov.themartiancompose.domain.entity.rovers.RoverManifestDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RoverService {
    @GET("manifests/{rover}/?")
    fun loadRoverInfo(
        @Path(ApiParams.ROVER) roverName: String
    ): Call<RoverManifestDto>
}