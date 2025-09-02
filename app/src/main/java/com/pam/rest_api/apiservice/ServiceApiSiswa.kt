package com.pam.rest_api.apiservice

import com.pam.rest_api.modeldata.DataSiswa
import retrofit2.http.GET

interface ServiceApiSiswa {
    @GET("http://127.0.0.1:8000/api/identitas")
    suspend fun getAllSiswa(): List<DataSiswa>
}