package com.ryutec.pruebatecnica.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryutec.pruebatecnica.data.Repository
import com.ryutec.pruebatecnica.data.Resource
import com.ryutec.pruebatecnica.data.model.operations.OperationsModel
import com.ryutec.pruebatecnica.domain.GetCharacters
import com.ryutec.pruebatecnica.domain.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RickAndMortyViewModel @Inject constructor(
    private val getCharacters: GetCharacters
) : ViewModel(){

    private val _uploadFlow = MutableLiveData<Resource<List<Character>>?>(null)
    val uploadFlow: LiveData<Resource<List<Character>>?> = _uploadFlow

    fun onCreate(page:Int){
        viewModelScope.launch {
            _uploadFlow.value = Resource.Loading
            delay(600)
            val result = getCharacters(page.toString())
            _uploadFlow.value = result
        }
    }


}