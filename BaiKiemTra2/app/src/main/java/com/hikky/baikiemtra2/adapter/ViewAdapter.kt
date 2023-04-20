package com.hikky.baikiemtra2.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.hikky.baikiemtra2.R
import com.hikky.baikiemtra2.model.ProductModel
import com.squareup.picasso.Picasso
import kotlin.collections.ArrayList
import kotlin.math.log


class ViewAdapter(private val context : Context, var productList : ArrayList<ProductModel> ) : RecyclerView.Adapter<ViewAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MyViewHolder(itemView)

    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = productList[position]
        holder.ten.text = item.tenSp!!
        holder.gia.text = item.giaSp!!
        holder.loai.text = item.loaiSp!!
        val image = holder.image
        Picasso.get()
            .load(item.urlImageSp)
            .fit()
            .into(image)


    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ten: TextView = itemView.findViewById(R.id.tenSp)
        val loai: TextView = itemView.findViewById(R.id.loaiSp)
        val gia: TextView = itemView.findViewById(R.id.giaSp)
        val image: ImageView = itemView.findViewById(R.id.anhSp)

    }
}
