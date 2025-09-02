package com.pam.rest_api.apiservice

import com.pam.rest_api.modeldata.DataSiswa
import retrofit2.http.GET

interface ServiceApiSiswa {
    @GET("api/identitas")
    suspend fun getAllSiswa(): List<DataSiswa>
}