package com.ryutec.pruebatecnica.ui.view.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ryutec.pruebatecnica.R

/**
 * A simple [Fragment] subclass.
 * Use the [IAutorizaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class IAutorizaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_i_autoriza, container, false)
    }

}