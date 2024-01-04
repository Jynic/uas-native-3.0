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

class ParagrafAdapter(val paragrafs:ArrayList<Paragraf>): RecyclerView.Adapter<ParagrafAdapter.ParagrafViewHolder>()  {
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
            txtPenulisParagraf.text = paragrafs[position].username.toString()
            btnLikeRead.setOnClickListener{

                }
            }
        }


}