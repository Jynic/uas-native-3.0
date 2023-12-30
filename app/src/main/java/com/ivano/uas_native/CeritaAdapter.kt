package com.ivano.uas_native

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ivano.uas_native.databinding.ActivityCardCerbungBinding
import com.squareup.picasso.Picasso

class CeritaAdapter(val ceritas:ArrayList<Cerita>): RecyclerView.Adapter<CeritaAdapter.CeritaViewHolder>()  {
    class CeritaViewHolder(val binding: ActivityCardCerbungBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CeritaViewHolder {
        val binding=ActivityCardCerbungBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CeritaViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return ceritas.size
    }

    override fun onBindViewHolder(holder: CeritaViewHolder, position: Int) {
        val url = ceritas[position].foto
        val builder = Picasso.Builder(holder.itemView.context)
        builder.listener{picasso, uri, exception -> exception.printStackTrace() }
        Picasso.get().load(url).into(holder.binding.imageView)

        with(holder.binding){
            txtJudul.text=ceritas[position].judul
            txtPenulis.text=ceritas[position].penulis
            txtDesc.text =ceritas[position].desc
        }
    }
}