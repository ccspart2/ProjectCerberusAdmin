package com.ccspart2.projectcerberusadmincompose.presentation.routes.employeeDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ccspart2.projectcerberusadmincompose.utils.LogUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class EmployeeDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _viewState = MutableStateFlow(EmployeeDetailsState())
    val viewState = _viewState.asStateFlow()

    init {
        val employeeId: String = savedStateHandle["employeeId"] ?: ""
        LogUtils.info("The Employee has an $employeeId", "EmployeeDetailsViewModel")
    }
}
