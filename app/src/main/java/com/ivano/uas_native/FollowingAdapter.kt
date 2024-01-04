package com.ivano.uas_native

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ivano.uas_native.databinding.ActivityCardCerbungBinding
import com.ivano.uas_native.databinding.ActivityCardFollowingBinding
import com.squareup.picasso.Picasso

class FollowingAdapter(val followings:ArrayList<ReadFollowingCerbung>): RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder>() {
    class FollowingViewHolder(val binding: ActivityCardFollowingBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingViewHolder {
        val binding=ActivityCardFollowingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowingAdapter.FollowingViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return followings.size
    }

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        val url = followings[position].foto
        val builder = Picasso.Builder(holder.itemView.context)
        builder.listener{picasso, uri, exception -> exception.printStackTrace() }
        Picasso.get().load(url).into(holder.binding.imageFoto)
        with(holder.binding){
            txtJudulCardFollowing.text = followings[position].judul.toString()
            txtPenulisCerbungFollowing.text = followings[position].username.toString()
            txtSinceFollowing.text=followings[position].last_update.toString()

        }
    }


}