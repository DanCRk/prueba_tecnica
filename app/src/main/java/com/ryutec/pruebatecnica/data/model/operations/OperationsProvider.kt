package com.ryutec.pruebatecnica.data.model.operations

import com.ryutec.pruebatecnica.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OperationsProvider @Inject constructor() {

    var operations: List<OperationsModel> = listOf(
        OperationsModel("Cambio de Estatus", 500, 100, true, R.drawable.ico_ce),
        OperationsModel("Remesas", 700, 100, false, R.drawable.ico_remesas),
        OperationsModel("Remesas Mayores", 600, 200, false, R.drawable.ico_remesas),
        OperationsModel("Canjes", 600, 10, false, R.drawable.group_4),
        OperationsModel("Pago por Anticipado", 700, 200, false,R.drawable.campana),
    )

}