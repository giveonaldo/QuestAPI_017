package com.pam.rest_api.uicontroller.route

import androidx.compose.ui.res.stringResource
import com.pam.rest_api.R

object DestinasiEdit: DestinasiNavigasi {
    override val route = "item_edit"
    override val titleRes = R.string.edit_siswa
    const val itemIdArg = "idSiswa"
    val routeWithArgs = "$route/{$itemIdArg}"
}