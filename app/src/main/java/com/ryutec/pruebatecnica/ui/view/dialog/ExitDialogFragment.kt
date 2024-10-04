package com.ryutec.pruebatecnica.ui.view.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.ryutec.pruebatecnica.R
import com.ryutec.pruebatecnica.databinding.FragmentExitDialogBinding

class ExitDialogFragment(
    private val onSubmitClickListener:(Boolean) -> Unit
) : DialogFragment() {

    private var _binding :FragmentExitDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExitDialogBinding.inflate(layoutInflater)
        dialog!!.window?.setBackgroundDrawableResource(R.color.transparent)

        binding.confirmButton.setOnClickListener {
            onSubmitClickListener(true)
        }

        binding.cancelButton.setOnClickListener {
            dismiss()
        }
        return binding.root
    }

}