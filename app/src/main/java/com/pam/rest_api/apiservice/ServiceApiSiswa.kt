package com.pam.rest_api.apiservice

import com.pam.rest_api.modeldata.DataSiswa
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface ServiceApiSiswa {
    @GET("api/identitas")
    suspend fun getAllSiswa(): List<DataSiswa>

    @POST("api/identitas")
    suspend fun postDataSiswa(@Body dataSiswa: DataSiswa): retrofit2.Response<Void>

    @GET("api/identitas/get/{id}")
    suspend fun getSatuSiswa(@Query("id") id: Int): DataSiswa

    @PUT("api/identitas/edit/{id}")
    suspend fun editSatuSiswa(@Query("id") id:Int, @Body dataSiswa: DataSiswa): retrofit2.Response<Void>

    @DELETE()
    suspend fun hapusSatuSiswa(@Query("id") id:Int): retrofit2.Response<Void>
}