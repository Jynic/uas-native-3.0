package com.ivano.uas_native

import android.media.metrics.Event
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.ListFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_EVENTS = "arrayevents"

/**
 * A simple [Fragment] subclass.
 * Use the [EventFragmentList.newInstance] factory method to
 * create an instance of this fragment.
 */
class EventFragmentList : ListFragment() {
    // TODO: Rename and change types of parameters
    private var ceritas: ArrayList<Cerita> = ArrayList()
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            ceritas = it.getParcelableArrayList<Cerita>(ARG_EVENTS) as ArrayList<Cerita>
            var adapter = activity?.applicationContext?.let {
                ArrayAdapter(it, android.R.layout.simple_list_item_1, ceritas)
            }
            listAdapter= adapter
        }
    }

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_event_list, container, false)
//    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EventFragmentList.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(evts:ArrayList<Cerita>) =
            EventFragmentList().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_EVENTS, evts)
                }
            }
    }
}