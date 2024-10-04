package com.ryutec.pruebatecnica.data.model

import com.google.gson.annotations.SerializedName

data class OperationsModel(
    @SerializedName("operation_type") val operationType: String,
    @SerializedName("total_operation") val totalOperation: Int,
    @SerializedName("total_operation_auth") val totalOperationAuth: Int,
    @SerializedName("is_authorized") val isAuthorized: Boolean,
    val icon: Int,
)
