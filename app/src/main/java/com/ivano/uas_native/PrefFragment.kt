package com.ivano.uas_native

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

private const val ARG_ID = "id"

class PrefFragment : Fragment() {
    private var id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt(ARG_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pref, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(id:Int) =
            PrefFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_ID, id.toString())
                }
            }
    }
}