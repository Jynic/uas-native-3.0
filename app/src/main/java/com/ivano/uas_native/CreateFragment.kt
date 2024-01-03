package com.ivano.uas_native

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.ListFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ivano.uas_native.databinding.FragmentHomeBinding
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
    private lateinit var binding : FragmentHomeBinding
    var ceritas:ArrayList<Cerita> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
//        val q = Volley.newRequestQueue(activity)
//        val url = "https://ubaya.me/native/160421054/create-cerita.php"
//
//        var stringRequest = StringRequest(
//            Request.Method.POST, url,
//            Response.Listener<String> {
//                Log.d("apiresult", it)
//                val obj = JSONObject(it)
//                if(obj.getString("result") == "OK") {
//                    val data = obj.getJSONArray("data")
//                    val sType = object : TypeToken<List<Cerita>>() { }.type
//                    ceritas = Gson().fromJson(data.toString(), sType) as
//                            ArrayList<Cerita>
//                    Log.d("apiresult", ceritas.toString())
//                    for(i in 0 until data.length()) {
//                        val playObj = data.getJSONObject(i)
//                        val cerita = Cerita(
//                            playObj.setInt("id"),
//                            playObj.setString("title"),
//                            playObj.setString("subtitle"),
//                            playObj.setString("description"),
//                            playObj.setString("image_url"),
//                            playObj.setInt("num_likes"),
//
//                        )
//                        ceritas.add(cerita)
//                    }
//                    addList()
//                    Log.d("cekisiarray", ceritas.toString())
//                }
//
//            },
//            Response.ErrorListener {
//                Log.e("apiresult", it.message.toString())
//            })
//        q.add(stringRequest)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun addList() {
        val lm = LinearLayoutManager(activity)
        with(binding.recycleView) {
            layoutManager = lm
            setHasFixedSize(true)
            adapter = CeritaAdapter(ceritas, this.context)
        }
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