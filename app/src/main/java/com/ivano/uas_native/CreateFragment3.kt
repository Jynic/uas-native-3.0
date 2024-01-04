package com.ivano.uas_native

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateFragment3.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateFragment3 : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_create3, container, false)
        val dataGenre = (activity as MainActivity).genreCreate
        val dataJudul = (activity as MainActivity).judulCreate
        val dataidPenulis = (activity as MainActivity).iduser
        val dataDesc = (activity as MainActivity).descCreate
        val dataFoto = (activity as MainActivity).imgCreate
        val dataAccess = (activity as MainActivity).accessCreate
        val firstpara = (activity as MainActivity).firstparaCreate

        view.findViewById<Button>(R.id.btnGenreView).text = dataGenre
        val btnPublis = view.findViewById<Button>(R.id.btnPublish)
        btnPublis.setOnClickListener{
            val url = "https://ubaya.me/native/160421054/create-cerbung.php"
            val request = object : StringRequest(
                Request.Method.POST, url,
                Response.Listener<String> { response ->
                    // Handle response dari server
                    Toast.makeText(activity, response, Toast.LENGTH_SHORT).show()
                },
                Response.ErrorListener { error ->
                    // Handle error
                    Toast.makeText(activity, "Error: $error", Toast.LENGTH_SHORT).show()
                }
            ){
                // Override metode untuk mengirim data ke server
                override fun getParams(): Map<String, String> {
                    val params = HashMap<String, String>()
                    params["judul"] = dataJudul
                    params["idpenulis"] = dataidPenulis.toString()
                    params["desc"] = dataDesc
                    params["foto"] = dataFoto
                    params["access"] = dataAccess
                    params["genre"] = dataGenre
                    params["firstpara"] = firstpara
                    return params
                }

        }
            Volley.newRequestQueue(activity).add(request)
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
         * @return A new instance of fragment CreateFragment3.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CreateFragment3().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}