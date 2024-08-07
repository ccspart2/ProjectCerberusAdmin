package com.ccspart2.projectcerberusadmincompose.presentation.routes.locations.addNewLocation

import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ccspart2.projectcerberusadmincompose.presentation.core.ui.components.topbars.MainTopBar
import com.ccspart2.projectcerberusadmincompose.presentation.core.ui.preview.PreviewScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@Composable
fun AddNewLocationRoute(navController: NavHostController) {
    val viewModel = hiltViewModel<AddNewLocationViewModel>()
    AddNewLocationScreen(
        stateFlow = viewModel.viewState,
        onSaveButtonClicked = { name, entrances, positions, suggestedCount ->
            viewModel.handleEvent(
                AddNewLocationEvent.OnSaveButtonClicked(
                    name = name,
                    entrances = entrances,
                    positions = positions,
                    suggestedCount = suggestedCount,
                ))
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddNewLocationScreen(
    stateFlow: StateFlow<AddNewLocationState>,
    onSaveButtonClicked: (String, Int, Int, Int) -> Unit
) {
    var locationName by remember { mutableStateOf("") }
    var locationEntrances by remember { mutableIntStateOf(0) }
    var locationPositions by remember { mutableIntStateOf(0) }
    var locationSuggestedCount by remember { mutableIntStateOf(0) }

    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val keyboardHeight = WindowInsets.ime.getBottom(LocalDensity.current)

    val viewState by stateFlow.collectAsState()

    LaunchedEffect(key1 = keyboardHeight) {
        coroutineScope.launch { scrollState.scrollBy(keyboardHeight.toFloat()) }
    }

    Column(Modifier.fillMaxWidth().padding(horizontal = 25.dp)) {
        MainTopBar(title = "Add Location", onActionClick = {})

        Text(text = "Location Name", style = MaterialTheme.typography.labelMedium)
        OutlinedTextField(
            value = locationName,
            onValueChange = { locationName = it },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next))
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Location Entrances", style = MaterialTheme.typography.labelMedium)
        OutlinedTextField(
            value = locationEntrances.toString(),
            onValueChange = { newValue -> locationEntrances = newValue.toIntOrNull() ?: 0 },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = TextStyle(textAlign = TextAlign.Center))
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Fixed Positions", style = MaterialTheme.typography.labelMedium)
        OutlinedTextField(
            value = locationPositions.toString(),
            onValueChange = { newValue -> locationPositions = newValue.toIntOrNull() ?: 0 },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = TextStyle(textAlign = TextAlign.Center))
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Suggested Headcount", style = MaterialTheme.typography.labelMedium)
        OutlinedTextField(
            value = locationSuggestedCount.toString(),
            onValueChange = { newValue -> locationSuggestedCount = newValue.toIntOrNull() ?: 0 },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = TextStyle(textAlign = TextAlign.Center))
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                onSaveButtonClicked(
                    locationName, locationEntrances, locationPositions, locationSuggestedCount)
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text("Submit")
            }
    }
}

@Preview(widthDp = 1024, heightDp = 800, showBackground = true)
@Composable
private fun AddNewLocationPreview() {
    PreviewScreen {
        AddNewLocationScreen(
            stateFlow =
                MutableStateFlow(
                    AddNewLocationState(name = "AddNewLocation"),
                ),
            onSaveButtonClicked = { _, _, _, _ -> })
    }
}
