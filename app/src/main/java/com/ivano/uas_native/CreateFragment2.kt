package com.ivano.uas_native

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateFragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateFragment2 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create2, container, false)
        val btnNext = view.findViewById<Button>(R.id.btnNextCreate)
        btnNext.setOnClickListener{
            val grupAccess = view.findViewById<RadioGroup>(R.id.grpAccess)
            val selectedrdo = grupAccess.checkedRadioButtonId
            if(selectedrdo != 1){
                val selectedRadioButton = view.findViewById<RadioButton>(selectedrdo)
                val selectedAccess = selectedRadioButton.text.toString()
                Toast.makeText(this.context, selectedAccess, Toast.LENGTH_SHORT).show()
                val firstpara = view.findViewById<EditText>(R.id.txtFirstPara).text.toString()
                (activity as MainActivity).accessCreate = selectedAccess
                (activity as MainActivity).firstparaCreate = firstpara
                val fregment = CreateFragment3()
                childFragmentManager.beginTransaction().replace(R.id.container, fregment).addToBackStack(null).commit()
            }
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CreateFragment2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CreateFragment2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}