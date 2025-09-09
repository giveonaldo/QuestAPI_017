package com.pam.rest_api.repositori

import com.pam.rest_api.apiservice.ServiceApiSiswa
import com.pam.rest_api.modeldata.DataSiswa
import retrofit2.Response

interface RepositoryDatasiswa {
    suspend fun getDataSiswa() : List<DataSiswa>
    suspend fun getSatuDataSiswa(id: Int) : DataSiswa
    suspend fun postDataSiswa(dataSiswa: DataSiswa): retrofit2.Response<Void>
    suspend fun editDataSiswa(id:Int, dataSiswa: DataSiswa): retrofit2.Response<Void>
    suspend fun hapusSatuSiswa(id:Int): retrofit2.Response<Void>
}

class JaringanRepositoryDataSiswa(
    private val serviceApiSiswa: ServiceApiSiswa
): RepositoryDatasiswa {
    override suspend fun getDataSiswa(): List<DataSiswa> = serviceApiSiswa.getAllSiswa()
    override suspend fun postDataSiswa(dataSiswa: DataSiswa): Response<Void> = serviceApiSiswa.postDataSiswa(dataSiswa)
    override suspend fun getSatuDataSiswa(id: Int): DataSiswa = serviceApiSiswa.getSatuSiswa(id)
    override suspend fun editDataSiswa(id: Int, dataSiswa: DataSiswa): Response<Void> = serviceApiSiswa.editSatuSiswa(id, dataSiswa)
    override suspend fun hapusSatuSiswa(id: Int): Response<Void> = serviceApiSiswa.hapusSatuSiswa(id)
}