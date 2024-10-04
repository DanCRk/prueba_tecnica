package com.ryutec.pruebatecnica.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ryutec.pruebatecnica.R
import com.ryutec.pruebatecnica.data.model.OperationsModel
import com.ryutec.pruebatecnica.databinding.OperationBinding

class AdapterIAutoriza (
    private val operaciones : List<OperationsModel>
): RecyclerView.Adapter<OperationsHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OperationsHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return OperationsHolder(layoutInflater.inflate(R.layout.operation, parent, false))
    }

    override fun getItemCount(): Int = operaciones.size

    override fun onBindViewHolder(holder: OperationsHolder, position: Int) {
        holder.binding(operaciones[position])
    }
}

class OperationsHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private val bind = OperationBinding.bind(view)

    fun binding(operationsModel: OperationsModel){
        bind.totalOperation.text = operationsModel.totalOperation.toString()
        bind.typeOperation.text = operationsModel.operationType
        bind.totalAuthOperation.text = operationsModel.totalOperationAuth.toString()
        if (operationsModel.isAuthorized) bind.authOperation.visibility = View.VISIBLE
        bind.icono.setImageResource(operationsModel.icon)

    }

}
