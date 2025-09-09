package com.pam.rest_api.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pam.rest_api.modeldata.DetailSiswa
import com.pam.rest_api.modeldata.UIStateSiswa
import com.pam.rest_api.modeldata.toDataSiswa // <-- Import fungsi ekstensi jika perlu
import com.pam.rest_api.repositori.RepositoryDatasiswa
import kotlinx.coroutines.launch
import java.io.IOException

class EntryViewModel(private val repositoryDatasiswa: RepositoryDatasiswa) : ViewModel() {
    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    private fun validasiInput(uiState: DetailSiswa = uiStateSiswa.detailSiswa): Boolean {
        return with(uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }

    fun updateUiState(detailSiswa: DetailSiswa) {
        uiStateSiswa =
            UIStateSiswa(detailSiswa = detailSiswa, isEntryValid = validasiInput(detailSiswa))
    }

    /**
     * Menggunakan viewModelScope untuk menjalankan coroutine di lifecycle ViewModel.
     * Menggunakan try-catch untuk menangani error jaringan.
     */
    fun addSiswa() {
        if (validasiInput()) {
            viewModelScope.launch {
                try {
                    // 1. Nama fungsi diperbaiki dari toDatasiswa() -> toDataSiswa()
                    repositoryDatasiswa.postDataSiswa(uiStateSiswa.detailSiswa.toDataSiswa())
                    println("Berhasil tambah data") // Anggap sukses jika tidak ada exception
                } catch (e: IOException) { // Menangani error konektivitas (misal: tidak ada internet)
                    println("Gagal tambah data: Masalah jaringan.")
                } catch (e: Exception) { // Menangani error lain (misal: respons server error)
                    println("Gagal tambah data: ${e.message}")
                }
            }
        }
    }
}