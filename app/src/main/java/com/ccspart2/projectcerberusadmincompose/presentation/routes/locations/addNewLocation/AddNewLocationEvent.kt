package com.ccspart2.projectcerberusadmincompose.presentation.routes.locations.addNewLocation

sealed class AddNewLocationEvent {
    data class OnSaveButtonClicked(
        val name: String,
        val entrances: Int,
        val positions: Int,
        val suggestedCount: Int,
    ) : AddNewLocationEvent()

    object OnInvalidInputConfirmButtonClicked : AddNewLocationEvent()

}
