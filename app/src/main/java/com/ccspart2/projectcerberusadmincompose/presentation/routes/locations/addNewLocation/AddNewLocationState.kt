package com.ccspart2.projectcerberusadmincompose.presentation.routes.locations.addNewLocation

data class AddNewLocationState(
    val locationUploadState: LocationUploadState = LocationUploadState.PENDING
)

enum class LocationUploadState {
    PENDING,
    SUCCESS,
    ERROR,
    INVALID_INPUT,
    LOADING
}
