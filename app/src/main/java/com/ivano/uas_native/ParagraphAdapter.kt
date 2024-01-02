package com.ivano.uas_native

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ParagraphAdapter(val paragrafs:ArrayList<Paragraph>): RecyclerView.Adapter<ParagraphAdapter.ParagraphViewHolder>(){
    class ParagraphViewHolder(val binding: ParagraphCardBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParagraphViewHolder {
        val binding = ParagraphCardBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ParagraphAdapter.ParagraphViewHolder(binding)
    }
    override fun getItemCount(): Int {
        return paragrafs.size
    }

    override fun onBindViewHolder(holder: ParagraphViewHolder, position: Int) {

        with(holder.binding)
        {
            txtParagraphs.text = paragrafs[position].paragraph
            btnUser.text = paragrafs[position].user
            btnLike.text = paragrafs[position].likes.toString()
        }
    }
}