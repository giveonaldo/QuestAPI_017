package com.pam.rest_api.repositori

import com.pam.rest_api.apiservice.ServiceApiSiswa
import com.pam.rest_api.modeldata.DataSiswa

interface RepositoryDatasiswa {
    suspend fun getDataSiswa() : List<DataSiswa>
}

class JaringanRepositoryDataSiswa(
    private val serviceApiSiswa: ServiceApiSiswa
): RepositoryDatasiswa {
    override suspend fun getDataSiswa(): List<DataSiswa> = serviceApiSiswa.getAllSiswa()
}