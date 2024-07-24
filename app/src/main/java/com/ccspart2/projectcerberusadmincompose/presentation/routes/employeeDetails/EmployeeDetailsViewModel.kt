package com.ccspart2.projectcerberusadmincompose.presentation.routes.employeeDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ccspart2.projectcerberusadmincompose.domain.EmployeesUseCases
import com.ccspart2.projectcerberusadmincompose.presentation.core.navigation.ArgumentDefinitions
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
                    LogUtils.error("Failed to retrieve employee.")
                }
            )
        }
    }
}
