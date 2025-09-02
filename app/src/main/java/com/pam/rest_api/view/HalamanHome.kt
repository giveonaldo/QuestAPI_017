package com.pam.rest_api.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pam.rest_api.R
import com.pam.rest_api.modeldata.DataSiswa
import com.pam.rest_api.uicontroller.DestinasiNavigasi
import com.pam.rest_api.viewmodel.HomeViewModel
import com.pam.rest_api.viewmodel.PenyediaViewModel
import com.pam.rest_api.viewmodel.StatusUiSiswa

object DestinasiHome: DestinasiNavigasi {
    override val route = "home"
    override val titleRes = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.app_name), color = Color.White) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(colorResource(R.color.purple_700)),
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_large))
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Tambah"
                )
            }
        }
    ) { innerPadding ->
        HomeBody(
            statusUiSiswa = viewModel.listSiswa,
            retryAction = { viewModel.listSiswa },
            modifier = modifier.padding(innerPadding).fillMaxSize()
        )
    }
}

@Composable
fun HomeBody(
    statusUiSiswa: StatusUiSiswa,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    when(statusUiSiswa) {
        is StatusUiSiswa.Loading -> LoadingScreen()
        is StatusUiSiswa.Success -> DaftarSiswa(itemSiswa = statusUiSiswa.siswa)
        is StatusUiSiswa.Error -> ErrorScreen(
            retryAction,
            modifier = modifier.fillMaxSize()
        )
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.ic_launcher_background),
        contentDescription = ""
    )
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Gagal"
        )
        Button(
            onClick = retryAction
        ) {
            Text("Coba")
        }
    }
}

@Composable
fun DaftarSiswa(
    itemSiswa: List<DataSiswa>,
    modifier: Modifier = Modifier
){
    LazyColumn(modifier = Modifier) {
        items(items = itemSiswa, key = {it.id}) {
                person ->
            ItemSiswa(
                siswa = person,
                modifier = modifier.padding(dimensionResource(R.dimen.padding_small))
            )
        }
    }
}

@Composable
fun ItemSiswa(
    siswa: DataSiswa,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_large)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = siswa.nama,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = null
                )
                Text(
                    text = siswa.telpon,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = siswa.alamat,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}