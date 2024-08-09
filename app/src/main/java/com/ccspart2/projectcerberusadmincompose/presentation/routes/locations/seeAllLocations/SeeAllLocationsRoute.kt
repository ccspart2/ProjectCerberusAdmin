package com.ccspart2.projectcerberusadmincompose.presentation.routes.locations.seeAllLocations

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ccspart2.projectcerberusadmincompose.R
import com.ccspart2.projectcerberusadmincompose.presentation.core.navigation.Screen
import com.ccspart2.projectcerberusadmincompose.presentation.core.ui.components.dialogs.LoadingDialog
import com.ccspart2.projectcerberusadmincompose.presentation.core.ui.components.itemcomponents.SeeAllItem
import com.ccspart2.projectcerberusadmincompose.presentation.core.ui.components.topbars.MainTopBar
import com.ccspart2.projectcerberusadmincompose.presentation.core.ui.preview.PreviewScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun SeeAllLocationsRoute(navController: NavHostController) {
    val viewModel = hiltViewModel<SeeAllLocationsViewModel>()
    SeeAllLocationsScreen(
        stateFlow = viewModel.viewState,
        onAddLocationButtonClick = { navController.navigate(Screen.AddNewLocation.route) },
        onErrorConfirmationButtonClick = { navController.popBackStack() })
}

@Composable
private fun SeeAllLocationsScreen(
    stateFlow: StateFlow<SeeAllLocationsState>,
    onAddLocationButtonClick: () -> Unit,
    onErrorConfirmationButtonClick: () -> Unit,
) {
    val viewState by stateFlow.collectAsState()

    when (viewState.locationUploadState) {
        LocationUploadState.SUCCESS -> {
            Column(modifier = Modifier.fillMaxSize()) {
                MainTopBar(
                    title = "Locations",
                    actionImageResource = R.drawable.outline_add_location_alt_24,
                    onActionClick = onAddLocationButtonClick)
                LazyColumn(
                    modifier =
                        Modifier.fillMaxSize()
                            .padding(20.dp)
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.outline,
                                shape = RoundedCornerShape(10.dp))) {
                        items(viewState.locationList) { location ->
                            SeeAllItem(itemLabel = location.name, onClick = {})
                        }
                    }
            }
        }
        LocationUploadState.LOADING -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                LoadingDialog()
            }
        }
        LocationUploadState.ERROR -> {
            AlertDialog(
                title = { Text(text = "Error") },
                text = { Text(text = "There was an error retrieving the locations.") },
                onDismissRequest = onErrorConfirmationButtonClick,
                confirmButton = { TextButton(onClick = {}) { Text("Ok") } })
        }
    }
}

@Preview(widthDp = 1024, heightDp = 800, showBackground = true)
@Composable
private fun SeeAllLocationsPreview() {
    PreviewScreen {
        SeeAllLocationsScreen(
            stateFlow =
                MutableStateFlow(
                    SeeAllLocationsState(
                        locationUploadState = LocationUploadState.ERROR,
                    ),
                ),
            onAddLocationButtonClick = {},
            onErrorConfirmationButtonClick = {},
        )
    }
}
