package com.ccspart2.projectcerberusadmincompose.presentation.routes.home

import androidx.lifecycle.ViewModel
import com.ccspart2.projectcerberusadmincompose.data.model.Employee
import com.ccspart2.projectcerberusadmincompose.domain.EmployeesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val employeesUseCases: EmployeesUseCases
) : ViewModel() {
    private val _viewState = MutableStateFlow(HomeState())
    val viewState = _viewState.asStateFlow()

    private val _data = MutableStateFlow<List<Employee>>(emptyList())
    val data: StateFlow<List<Employee>> get() = _data
}
