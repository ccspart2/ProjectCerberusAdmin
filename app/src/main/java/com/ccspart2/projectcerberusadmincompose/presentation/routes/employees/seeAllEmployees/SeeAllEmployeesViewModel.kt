package com.ccspart2.projectcerberusadmincompose.presentation.routes.employees.seeAllEmployees

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ccspart2.projectcerberusadmincompose.domain.EmployeesUseCases
import com.ccspart2.projectcerberusadmincompose.presentation.routes.employees.seeAllEmployees.SeeAllEmployeesState
import com.ccspart2.projectcerberusadmincompose.utils.LogUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SeeAllEmployeesViewModel @Inject constructor(
    private val employeesUseCases: EmployeesUseCases
) :
    ViewModel() {
    private val _viewState = MutableStateFlow(SeeAllEmployeesState())
    val viewState = _viewState.asStateFlow()

    init {
        viewModelScope.launch {
            employeesUseCases.getAllEmployees(
                onSuccess = { employees ->
                    _viewState.update { state ->
                        state.copy(
                            employeeList = employees,
                            isLoading = false
                        )
                    }
                },
                onError = {
                    // TODO Fix This
                    LogUtils.error("")
                }
            ).collect {}
        }
    }
}
