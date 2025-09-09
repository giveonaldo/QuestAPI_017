package com.pam.rest_api.viewmodel

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pam.rest_api.modeldata.DataSiswa
import com.pam.rest_api.repositori.RepositoryDatasiswa
import com.pam.rest_api.uicontroller.route.DestinasiDetail
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import retrofit2.Response

sealed interface StatusUIDetail {
    data class Success(val satusiswa: DataSiswa) : StatusUIDetail
    object Error : StatusUIDetail
    object Loading : StatusUIDetail
}

class DetailViewModel(savedStateHandle: SavedStateHandle, private val repositoryDatasiswa: RepositoryDatasiswa):
    ViewModel() {
        private val idSiswa: Int = checkNotNull(savedStateHandle[DestinasiDetail.itemIdArg])

        var statusUIDetail: StatusUIDetail by mutableStateOf(StatusUIDetail.Loading)
            private set

        init {
            getSatuSiswa()
        }

    fun getSatuSiswa() {
        viewModelScope.launch {
            statusUIDetail = StatusUIDetail.Loading
            statusUIDetail = try {
                StatusUIDetail.Success(satusiswa = repositoryDatasiswa.getSatuDataSiswa(idSiswa))
            }
            catch (e: IOException) {
                StatusUIDetail.Error
            }
            catch (e: HttpException) {
                StatusUIDetail.Error
            }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    suspend fun hapusSatuSiswa() {
        val resp: Response<Void> = repositoryDatasiswa.hapusSatuSiswa(idSiswa)
        if (resp. isSuccessful) {
            println("Sukses Hapus Data : ${resp.message()}")
        } else {
            println("Gagal Hapus Data : ${resp.errorBody()}")
        }
    }

    }