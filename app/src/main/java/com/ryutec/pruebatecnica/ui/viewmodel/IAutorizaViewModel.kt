package com.ryutec.pruebatecnica.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryutec.pruebatecnica.data.Repository
import com.ryutec.pruebatecnica.data.Resource
import com.ryutec.pruebatecnica.data.model.OperationsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IAutorizaViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel(){

    private val _uploadFlow = MutableLiveData<Resource<List<OperationsModel>>?>(null)
    val uploadFlow: LiveData<Resource<List<OperationsModel>>?> = _uploadFlow

    fun onCreate(){
        viewModelScope.launch {
            _uploadFlow.value = Resource.Loading
            delay(600)
            val result = repository.getAllOperations()
            _uploadFlow.value = result
        }
    }


}