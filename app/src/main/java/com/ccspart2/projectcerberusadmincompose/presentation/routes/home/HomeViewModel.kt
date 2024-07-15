package com.ccspart2.projectcerberusadmincompose.presentation.routes.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ccspart2.projectcerberusadmincompose.data.model.FirestoreModel
import com.ccspart2.projectcerberusadmincompose.domain.AddDataUseCase
import com.ccspart2.projectcerberusadmincompose.domain.GetDataUseCase
import com.ccspart2.projectcerberusadmincompose.utils.LogUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDataUseCase: GetDataUseCase,
    private val addDataUseCase: AddDataUseCase
) : ViewModel() {
    private val _viewState = MutableStateFlow(HomeState())
    val viewState = _viewState.asStateFlow()

    private val _data = MutableStateFlow<List<FirestoreModel>>(emptyList())
    val data: StateFlow<List<FirestoreModel>> get() = _data

    init {
//        LogUtils.info("Entro!!")
//
//        viewModelScope.launch {
//            getDataUseCase().collect { list ->
//                _data.value = list
//            }
//        }

//        viewModelScope.launch {
//            addDataUseCase(
//                FirestoreModel(
//                    name = "Chardoro"
//                )
//            )
//        }
    }
}
