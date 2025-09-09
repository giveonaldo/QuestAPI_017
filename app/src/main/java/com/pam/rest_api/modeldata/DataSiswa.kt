package com.pam.rest_api.modeldata

import kotlinx.serialization.Serializable

@Serializable
data class DataSiswa(
    val id: Int,
    val nama: String,
    val alamat: String,
    val telpon: String
)

data class UIStateSiswa(
    val detailSiswa: DetailSiswa = DetailSiswa(),
    val isEntryValid: Boolean = false
)

data class DetailSiswa(
    val id: Int = 0,
    val nama: String = "",
    val alamat: String = "",
    val telpon: String = ""
)

// --- FUNGSI KONVERSI ---

/**
 * Mengubah objek DetailSiswa menjadi DataSiswa.
 */
fun DetailSiswa.toDataSiswa(): DataSiswa = DataSiswa(
    id = id,
    nama = nama,
    alamat = alamat,
    telpon = telpon
)

/**
 * Mengubah objek DataSiswa menjadi UIStateSiswa.
 * Di sini letak perbaikannya, dengan menambahkan fungsi toDetailSiswa() untuk DataSiswa.
 */
fun DataSiswa.toUiStateSiswa(isEntryValid: Boolean = false): UIStateSiswa = UIStateSiswa(
    detailSiswa = this.toDetailSiswa(), // Memanggil fungsi baru di bawah
    isEntryValid = isEntryValid
)

/**
 * [YANG DITAMBAHKAN]
 * Mengubah objek DataSiswa menjadi DetailSiswa.
 * Fungsi ini yang sebelumnya tidak ada dan menyebabkan error.
 */
fun DataSiswa.toDetailSiswa(): DetailSiswa = DetailSiswa(
    id = id,
    nama = nama,
    alamat = alamat,
    telpon = telpon
)

/*
 * Catatan: Fungsi di bawah ini sebenarnya tidak diperlukan karena
 * tidak ada logika konversi di dalamnya dan bisa menyebabkan kebingungan.
 * Objek DetailSiswa tidak perlu diubah menjadi DetailSiswa lagi.
 * Namun, saya tetap menyertakannya jika ada tujuan lain.
 *
 * fun DetailSiswa.toDetailSiswa(): DetailSiswa = DetailSiswa(
 * id = id,
 * nama = nama,
 * alamat = alamat,
 * telpon = telpon
 * )
 */