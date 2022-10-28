package com.example.usuers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.usuers.databinding.ItemUsersBinding

class UsaerAdapter(private val users: List<Usuario>,private var listener: OnClickListener) : RecyclerView.Adapter<UsaerAdapter.ViewHolder>(){

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            context = parent.context

        val view = LayoutInflater.from(context).inflate(R.layout.item_users,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        val humanPosicion = position + 1


        with(holder){
            setListener(user,humanPosicion)
            binding.tvOrden.text = humanPosicion.toString()
            binding.tvName.text = user.getFullName()
            Glide.with(context).load(user.url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .circleCrop()
                .into(binding.imgPo)
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemUsersBinding.bind(view)

        fun setListener(user: Usuario, position: Int){
            binding.root.setOnClickListener {
                listener.onClick(user,position)
            }
        }

    }
}