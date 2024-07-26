package com.ccspart2.projectcerberusadmincompose.presentation.routes.employeeDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ccspart2.projectcerberusadmincompose.data.model.Employee
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
class EmployeeDetailsViewModel
@Inject
constructor(savedStateHandle: SavedStateHandle, private val employeesUseCases: EmployeesUseCases) :
    ViewModel() {
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
            EmployeeDetailsEvent.OnConfirmDeleteDialog -> removeEmployee()
            EmployeeDetailsEvent.OnDisplayDeleteDialog -> displayDeleteEmployeeDialog()
            EmployeeDetailsEvent.OnDismissDeleteDialog -> dismissDeleteEmployeeDialog()
        }
    }

    private fun dismissDeleteEmployeeDialog() {
        _viewState.update { state ->
            state.copy(employeeDetailsEditState = EmployeeDetailsEditState.PENDING)
        }
    }

    private fun removeEmployee() {
        _viewState.update { state ->
            state.copy(employeeDetailsEditState = EmployeeDetailsEditState.LOADING)
        }
        viewState.value.selectedEmployee?.let { employee ->
            viewModelScope.launch {
                employeesUseCases.deleteEmployee(
                    id = employee.id,
                    onSuccess = {
                        _viewState.update { state ->
                            state.copy(
                                employeeDetailsEditState =
                                    EmployeeDetailsEditState.DISPLAY_SUCCESS_DELETE_DIALOG)
                        }
                    },
                    onError = {
                        _viewState.update { state ->
                            state.copy(
                                employeeDetailsEditState =
                                    EmployeeDetailsEditState.DISPLAY_ERROR_DELETE_DIALOG)
                        }
                    })
            }
        }
    }

    private fun displayDeleteEmployeeDialog() {
        _viewState.update { state ->
            state.copy(employeeDetailsEditState = EmployeeDetailsEditState.DISPLAY_DELETE_DIALOG)
        }
    }

    private fun updateInvalidInputFlag() {
        _viewState.update { state ->
            state.copy(employeeDetailsEditState = EmployeeDetailsEditState.DISPLAY_EDIT_DIALOG)
        }
    }

    private fun checkInformation(event: EmployeeDetailsEvent.OnConfirmEditDialog) {
        _viewState.update { state ->
            state.copy(employeeDetailsEditState = EmployeeDetailsEditState.LOADING)
        }

        if (InputValidator.isValidName(event.firstName) &&
            InputValidator.isValidName(event.lastName) &&
            InputValidator.isValidEmail(event.email) &&
            InputValidator.isValidPhoneNumber(event.phoneNumber)) {
            LogUtils.info("Employee ready to be updated.")
            viewModelScope.launch {
                viewState.value.selectedEmployee?.let { currentEmployee ->
                    employeesUseCases.updateEmployee(
                        id = currentEmployee.id,
                        updatedEmployee =
                            Employee(
                                id = currentEmployee.id,
                                firstName = event.firstName,
                                lastName = event.lastName,
                                phoneNumber = event.phoneNumber,
                                email = event.email,
                                isAdmin = currentEmployee.isAdmin),
                        onSuccess = {
                            _viewState.update { state ->
                                state.copy(
                                    employeeDetailsEditState =
                                        EmployeeDetailsEditState.DISPLAY_SUCCESS_EDIT_DIALOG)
                            }

                            LogUtils.info(
                                "The Employee was successfully updated.",
                                "EmployeeDetailsViewModel")
                        },
                        onError = { error ->
                            _viewState.update { state ->
                                state.copy(
                                    employeeDetailsEditState =
                                        EmployeeDetailsEditState.DISPLAY_ERROR_EDIT_DIALOG)
                            }
                            LogUtils.info(
                                "Error in Updating the employee. with error $error ",
                                "EmployeeDetailsViewModel")
                        })
                }
            }
        } else {
            _viewState.update { state ->
                state.copy(
                    employeeDetailsEditState =
                        EmployeeDetailsEditState.DISPLAY_INVALID_INPUT_DIALOG)
            }
        }
    }

    private fun updateDisplayDialogFlag(event: EmployeeDetailsEvent.OnDisplayEditDialog) {
        _viewState.update { state ->
            state.copy(
                employeeDetailsEditState =
                    if (event.action) EmployeeDetailsEditState.DISPLAY_EDIT_DIALOG
                    else EmployeeDetailsEditState.PENDING)
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
                            employeeDetailsEditState = EmployeeDetailsEditState.PENDING)
                    }
                },
                onFailure = { exception ->
                    LogUtils.error("Failed to retrieve employee with error : ${exception.message}")
                })
        }
    }
}
