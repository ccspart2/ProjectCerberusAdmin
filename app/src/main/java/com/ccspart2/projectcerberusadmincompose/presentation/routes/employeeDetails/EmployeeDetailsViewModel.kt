package com.ccspart2.projectcerberusadmincompose.presentation.routes.employeeDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ccspart2.projectcerberusadmincompose.domain.EmployeesUseCases
import com.ccspart2.projectcerberusadmincompose.presentation.core.navigation.ArgumentDefinitions
import com.ccspart2.projectcerberusadmincompose.utils.InputValidator
import com.ccspart2.projectcerberusadmincompose.utils.LogUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class EmployeeDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val employeesUseCases: EmployeesUseCases
) : ViewModel() {
    private val _viewState = MutableStateFlow(EmployeeDetailsState())
    val viewState = _viewState.asStateFlow()

    init {
        val employeeId: String = savedStateHandle[ArgumentDefinitions.EMPLOYEE_ID.path] ?: ""
        LogUtils.info("The Employee has an $employeeId", "EmployeeDetailsViewModel")

        if (employeeId.isNotEmpty()) {
            fetchEmployeeById(employeeId)
        }
    }

    fun handleEvent(event: EmployeeDetailsEvent) {
        when (event) {
            is EmployeeDetailsEvent.OnDisplayEditDialog -> updateDisplayDialogFlag(event)
            is EmployeeDetailsEvent.OnConfirmEditDialog -> checkInformation(event)
            EmployeeDetailsEvent.OnInvalidInputDismiss -> updateInvalidInputFlag()
        }
    }

    private fun updateInvalidInputFlag() {
        _viewState.update { state ->
            state.copy(
                displayErrorDialog = false
            )
        }
    }

    private fun checkInformation(event: EmployeeDetailsEvent.OnConfirmEditDialog) {
        _viewState.update { state ->
            state.copy(
                openEditDialog = false
            )
        }

        if (InputValidator.isValidName(event.firstName) &&
            InputValidator.isValidName(event.lastName) &&
            InputValidator.isValidEmail(event.email) &&
            InputValidator.isValidPhoneNumber(event.phoneNumber)
        ) {
            LogUtils.info("Employee ready to be updated.")
        } else {
            _viewState.update { state ->
                state.copy(
                    displayErrorDialog = true
                )
            }
        }
    }

    private fun updateDisplayDialogFlag(event: EmployeeDetailsEvent.OnDisplayEditDialog) {
        _viewState.update { state ->
            state.copy(
                openEditDialog = event.action
            )
        }
    }

    private fun fetchEmployeeById(id: String) {
        viewModelScope.launch {
            employeesUseCases.getEmployeeById(
                employeeId = id,
                onSuccess = { employee ->
                    LogUtils.info("$employee")
                    _viewState.update { state ->
                        state.copy(
                            selectedEmployee = employee,
                            isLoading = false
                        )
                    }
                },
                onFailure = { exception ->
                    LogUtils.error("Failed to retrieve employee with error : ${exception.message}")
                }
            )
        }
    }
}
