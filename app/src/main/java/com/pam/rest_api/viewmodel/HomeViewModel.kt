package com.pam.rest_api.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pam.rest_api.modeldata.DataSiswa
import com.pam.rest_api.repositori.RepositoryDatasiswa
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

sealed interface StatusUiSiswa {
    data class Success(val siswa: List<DataSiswa> = listOf()): StatusUiSiswa
    object Error : StatusUiSiswa
    object Loading : StatusUiSiswa
}

class HomeViewModel(private val repositoryDatasiswa: RepositoryDatasiswa): ViewModel() {
    var listSiswa: StatusUiSiswa by mutableStateOf(StatusUiSiswa.Loading)
        private set

    init {
        loadSiswa()
    }

    fun loadSiswa() {
        viewModelScope.launch {
            listSiswa = StatusUiSiswa.Loading
            listSiswa = try {
                StatusUiSiswa.Success(repositoryDatasiswa.getDataSiswa())
            } catch (e: IOException) {
                StatusUiSiswa.Error
            } catch (e: HttpException) {
                StatusUiSiswa.Error
            }
        }
    }
}