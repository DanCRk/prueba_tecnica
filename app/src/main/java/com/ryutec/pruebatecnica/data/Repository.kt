package com.ryutec.pruebatecnica.data

import com.ryutec.pruebatecnica.data.model.OperationsModel
import com.ryutec.pruebatecnica.data.model.OperationsProvider
import javax.inject.Inject

class Repository @Inject constructor(private val operationsProvider: OperationsProvider) {

    fun getAllOperations() :Resource<List<OperationsModel>>{
        return try {
            Resource.Success(operationsProvider.operations)
        }catch (e:Exception){
            e.printStackTrace()
            Resource.Failure(e)
        }
    }

}