package com.ivano.uas_native

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.volley.Request
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ivano.uas_native.databinding.ActivityCardCerbungBinding
import com.ivano.uas_native.databinding.ActivityCardParagrafBinding
import com.ivano.uas_native.databinding.ActivityParagraphCardBinding

class ParagrafAdapter(val paragrafs:ArrayList<Paragraf>, val iduser:String): RecyclerView.Adapter<ParagrafAdapter.ParagrafViewHolder>()  {
    class ParagrafViewHolder(val binding: ActivityCardParagrafBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParagrafViewHolder {
        val binding=ActivityCardParagrafBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ParagrafViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return paragrafs.size
    }

    override fun onBindViewHolder(holder: ParagrafViewHolder, position: Int) {
        with(holder.binding){
            txtParagraf.text = paragrafs[position].paragraf.toString()
            txtPenulisParagraf.text = paragrafs[position].name.toString()
            btnLikeRead.setOnClickListener{
                val q = Volley.newRequestQueue(holder.itemView.context)
                val url = "https://ubaya.me/native/160421054/update-like.php"
                val stringRequest = object:StringRequest(
                    Request.Method.POST, url, Response.Listener {
                        Log.d("cekparameter", it)
                    }, Response.ErrorListener {
                        Log.d("cekparameter", it.message.toString())
                    }
                    ){
                    override fun getParams()= hashMapOf(
                        "id" to paragrafs[holder.adapterPosition].id.toString(),
                        "iduser" to iduser
                    )
                    }
                    q.add(stringRequest)
                }
            }
        }


}