package com.ccspart2.projectcerberusadmincompose.presentation.routes.locations.seeAllLocations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ccspart2.projectcerberusadmincompose.domain.LocationsUseCases
import com.ccspart2.projectcerberusadmincompose.utils.LogUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SeeAllLocationsViewModel
@Inject
constructor(private val locationsUseCases: LocationsUseCases) : ViewModel() {
    private val _viewState = MutableStateFlow(SeeAllLocationsState())
    val viewState = _viewState.asStateFlow()

    init {
        viewModelScope.launch {
            locationsUseCases
                .getAllLocations(
                    onSuccess = { locations ->
                        _viewState.update { state ->
                            state.copy(
                                locationList = locations,
                                locationUploadState = LocationUploadState.SUCCESS)
                        }
                    },
                    onError = { error ->
                        LogUtils.error("Errors in fetching locations: $error")
                        _viewState.update { state ->
                            state.copy(locationUploadState = LocationUploadState.ERROR)
                        }
                    })
                .collect {}
        }
    }
}
