package com.pam.rest_api.repositori

import android.app.Application
import android.app.Service
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.pam.rest_api.apiservice.ServiceApiSiswa
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface ContainerApp {
    val repositoryDatasiswa: RepositoryDatasiswa
}

class DefaultContainerApp: ContainerApp {
    private val baseurl = "http://127.0.0.1:8000/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseurl)
        .addConverterFactory(
            Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            }.asConverterFactory("application/json".toMediaType())
        ).build()

    private val retrofitService: ServiceApiSiswa by lazy {
        retrofit.create(ServiceApiSiswa::class.java)
    }

    override val repositoryDatasiswa: RepositoryDatasiswa by lazy {
        JaringanRepositoryDataSiswa(retrofitService)
    }
}

class AplikasiDataSiswa: Application() {
    lateinit var container: ContainerApp
    override fun onCreate() {
        super.onCreate()
        this.container = DefaultContainerApp()
    }
}