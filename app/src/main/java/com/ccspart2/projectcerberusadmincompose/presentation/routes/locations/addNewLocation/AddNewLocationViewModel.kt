package com.ccspart2.projectcerberusadmincompose.presentation.routes.locations.addNewLocation

import androidx.lifecycle.ViewModel
import com.ccspart2.projectcerberusadmincompose.utils.LogUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddNewLocationViewModel @Inject constructor() : ViewModel() {

    private val _viewState = MutableStateFlow(AddNewLocationState())
    val viewState = _viewState.asStateFlow()

    fun handleEvent(event: AddNewLocationEvent) {
        when (event) {
            is AddNewLocationEvent.OnSaveButtonClicked -> checkInformation(event)
        }
    }

    private fun checkInformation(event: AddNewLocationEvent.OnSaveButtonClicked) {
        LogUtils.info("The event is $event")
    }
}
