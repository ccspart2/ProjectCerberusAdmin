package com.ccspart2.projectcerberusadmincompose.presentation.routes.employees.addNewEmployee

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ccspart2.projectcerberusadmincompose.data.model.Employee
import com.ccspart2.projectcerberusadmincompose.domain.EmployeesUseCases
import com.ccspart2.projectcerberusadmincompose.utils.InputValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class AddNewEmployeeViewModel @Inject constructor(
    private val employeesUseCases: EmployeesUseCases
) : ViewModel() {
    private val _viewState = MutableStateFlow(AddNewEmployeeState())
    val viewState = _viewState.asStateFlow()

    fun handleEvent(event: AddNewEmployeeEvent) {
        when (event) {
            is AddNewEmployeeEvent.OnSaveButtonClicked -> checkInformation(event)
            AddNewEmployeeEvent.OnInputValidationConfirmButtonCLicked -> updateInvalidInputFlag()
        }
    }

    private fun updateInvalidInputFlag() {
        _viewState.update { state ->
            state.copy(
                employeeUploadState = EmployeeUploadState.PENDING
            )
        }
    }

    private fun checkInformation(event: AddNewEmployeeEvent.OnSaveButtonClicked) {
        if (InputValidator.isValidName(event.firstName) &&
            InputValidator.isValidName(event.lastName) &&
            InputValidator.isValidPhoneNumber(event.phoneNumber) &&
            InputValidator.isValidEmail(event.email)
        ) {
            _viewState.update { state ->
                state.copy(
                    employeeUploadState = EmployeeUploadState.LOADING
                )
            }
            viewModelScope.launch {
                employeesUseCases.addEmployee(
                    Employee(
                        firstName = event.firstName,
                        lastName = event.lastName,
                        phoneNumber = event.phoneNumber,
                        email = event.email,
                        isAdmin = event.isAdmin
                    ),
                    onSuccess = {
                        _viewState.update { state ->
                            state.copy(
                                employeeUploadState = EmployeeUploadState.SUCCESS
                            )
                        }
                    },
                    onError = {
                        _viewState.update { state ->
                            state.copy(
                                employeeUploadState = EmployeeUploadState.ERROR
                            )
                        }
                    }
                )
            }
        } else {
            _viewState.update { state ->
                state.copy(
                    employeeUploadState = EmployeeUploadState.INVALID_INPUT
                )
            }
        }
    }
}
