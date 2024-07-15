package com.ccspart2.projectcerberusadmincompose.presentation.routes.addNewEmployee

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ccspart2.projectcerberusadmincompose.data.model.Employee
import com.ccspart2.projectcerberusadmincompose.domain.EmployeesUseCases
import com.ccspart2.projectcerberusadmincompose.utils.LogUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AddNewEmployeeViewModel @Inject constructor(
    private val employeesUseCases: EmployeesUseCases
) : ViewModel() {
    private val _viewState = MutableStateFlow(AddNewEmployeeState())
    val viewState = _viewState.asStateFlow()

    init {}

    fun handleEvent(event: AddNewEmployeeEvent) {
        when (event) {
            is AddNewEmployeeEvent.onSaveButtonClicked -> checkInformation(event)
        }
    }

    private fun checkInformation(event: AddNewEmployeeEvent.onSaveButtonClicked) {
        viewModelScope.launch {
            employeesUseCases.addData(
                Employee(
                    firstName = event.firstName,
                    lastName = event.lastName,
                    phoneNumber = event.phoneNumber,
                    email = event.email,
                    isAdmin = event.isAdmin
                ),
                onSuccess = {
                    LogUtils.info("The Message was sent!")
                },
                onError = {
                    // TODO Add Error Handling
                }
            )
        }
    }
}
