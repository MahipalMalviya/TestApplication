package com.mahipal.testapplication.mvvm.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mahipal.testapplication.R
import com.mahipal.testapplication.mvvm.model.BaseResponse
import kotlinx.android.synthetic.main.layout_movie_item.view.*


class MovieAdapter(private val context: Context, photoList: ArrayList<BaseResponse>) :
    RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {

    private var list: MutableList<BaseResponse>? = null

    init {
        list = photoList.asIterable().toMutableList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_movie_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list?.size?: 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItem(position)
    }

    inner class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {

        fun bindItem(position: Int) {
            Glide.with(context)
                .load(list?.get(position)?.poster)
                .into(itemView.iv_item_movie)

            itemView.tv_movie_name.text = list?.get(position)?.title
            itemView.tv_movie_year.text = list?.get(position)?.year
        }
    }

}