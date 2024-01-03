package com.ivano.uas_native

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.ListFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ivano.uas_native.databinding.FragmentCreateBinding
import com.ivano.uas_native.databinding.FragmentHomeBinding
import com.ivano.uas_native.databinding.FragmentPrefBinding
import org.json.JSONException
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateFragment : ListFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentCreateBinding
    var ceritas:ArrayList<Cerita> = ArrayList()

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
        binding = FragmentCreateBinding.inflate(inflater, container, false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userlogin = requireActivity().getSharedPreferences("loginaccount", Context.MODE_PRIVATE)
        val id = userlogin.getInt("id", 0)

        binding.btnNextCreate1.setOnClickListener {
            val title = binding.txtTitle.text.toString()
            val desc = binding.txtDescCreate.text.toString()
            val url = binding.txtUrlCreate.text.toString()
            val genre = binding.spinGenre.selectedItem.toString()
        }
    }

    private fun CreateCerbung(id: Int, title:String, desc: String, url: String, genre: String) {
        val q = Volley.newRequestQueue(requireContext())
        val url = "https://ubaya.me/native/160421054/create-cerita.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            { response ->
                val obj = JSONObject(response)
                if (obj.getString("result") == "OK") {
                    // Password successfully updated
                    Toast.makeText(requireContext(), "Cerita Added successfully", Toast.LENGTH_SHORT).show()
                } else {
                    // Handle the case where the password update failed
                    Toast.makeText(requireContext(), "Failed to Add Cerita", Toast.LENGTH_SHORT).show()
                }
            },
            { error ->
                // Handle errors or exceptions here
                Log.e("AddCeritaError", error.printStackTrace().toString())
                Toast.makeText(requireContext(), "Error Creating Cerita", Toast.LENGTH_SHORT).show()
            }
        ) {
            override fun getParams(): MutableMap<String, String>? {
                val params = HashMap<String, String>()
                params["id"] = id.toString()
                params["title"] = title
                params["desc"] = desc
                params["url"] = url
                params["genre"] = genre
                return params
            }
        }

        q.add(stringRequest)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CreateFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CreateFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}