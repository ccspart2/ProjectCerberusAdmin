package com.ccspart2.projectcerberusadmincompose.presentation.routes.locations.addNewLocation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ccspart2.projectcerberusadmincompose.data.model.Location
import com.ccspart2.projectcerberusadmincompose.domain.LocationsUseCases
import com.ccspart2.projectcerberusadmincompose.utils.LogUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AddNewLocationViewModel
@Inject
constructor(private val locationsUseCases: LocationsUseCases) : ViewModel() {

    private val _viewState = MutableStateFlow(AddNewLocationState())
    val viewState = _viewState.asStateFlow()

    fun handleEvent(event: AddNewLocationEvent) {
        when (event) {
            is AddNewLocationEvent.OnSaveButtonClicked -> checkInformation(event)
        }
    }

    private fun checkInformation(event: AddNewLocationEvent.OnSaveButtonClicked) {
        LogUtils.info("The event is $event")

        // TODO Add validation for the Location Input

        viewModelScope.launch {
            locationsUseCases.addLocation(
                location =
                    Location(
                        name = event.name,
                        entrances = event.entrances,
                        positions = event.positions,
                        suggestedCount = event.suggestedCount),
                onError = { LogUtils.error("Error in adding the location") },
                onSuccess = { LogUtils.info("The location was successfully added") })
        }
    }
}
