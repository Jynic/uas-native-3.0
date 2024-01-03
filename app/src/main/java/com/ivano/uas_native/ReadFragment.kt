package com.ivano.uas_native

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ivano.uas_native.databinding.FragmentHomeBinding
import com.ivano.uas_native.databinding.FragmentReadBinding
import com.squareup.picasso.Picasso
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ReadFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val ARG_EVENT ="cerbung"



class ReadFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var cerbung:Cerita? = null
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var recyclerView: RecyclerView
    val iduser = (activity as MainActivity).iduser
    var paragrafs:ArrayList<Paragraf> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cerbung = it.getParcelable(ARG_EVENT)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view =  inflater.inflate(R.layout.fragment_read, container, false)
        val idcerita = arguments?.getString("index_cerita").toString()
        val judul = arguments?.getString("judul_cerita").toString()
        val foto = arguments?.getString("foto_cerita").toString()
        val genre = arguments?.getString("genre_cerita").toString()
        val penulis = arguments?.getString("penulis_cerita").toString()
        Toast.makeText(activity, iduser, Toast.LENGTH_SHORT).show()
        view.findViewById<TextView>(R.id.txtJudulCerbung).text = judul
        val imageView: ImageView = view.findViewById(R.id.image)
        Picasso.get().load(foto).into(imageView)
        view.findViewById<TextView>(R.id.txtGenre).text = genre
        view.findViewById<TextView>(R.id.txtPenulisCerbung).text = penulis
        paragrafs.clear()
        val p = Volley.newRequestQueue(activity)
        val url = "https://ubaya.me/native/160421054/read-paragraf-cerita.php?idcerbung="+idcerita.toString()
        var stringRequest = StringRequest(
            Request.Method.POST, url, Response.Listener {
                Log.d("apiresult2", it)
                val obj = JSONObject(it)
                if(obj.getString("result")=="OK"){
                    val data =obj.getJSONArray("data")
                    val sType = object: TypeToken<List<Paragraf>>(){ }.type
                    paragrafs = Gson().fromJson(data.toString(), sType)as ArrayList<Paragraf>
                    Log.d("paragrafresult", paragrafs.toString())
                }
                updateList()
                Log.d("cekparagraf", paragrafs.toString())
            },
            Response.ErrorListener {
                Log.e("paragrafresult", it.message.toString())
            })
        p.add(stringRequest)
        recyclerView = view.findViewById(R.id.recycleViewParagraf)
        view.findViewById<Button>(R.id.btnLike)
        return view


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    fun updateList() {
        val lm = LinearLayoutManager(activity)
        recyclerView.layoutManager = lm
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = ParagrafAdapter(paragrafs, iduser)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ReadFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ReadFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}