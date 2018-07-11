package com.example.nytapp.adapters

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nytapp.BR

// Common adapter to load the recyclerview
class BaseRecyclerAdapter : RecyclerView.Adapter<BaseRecyclerAdapter.BaseViewHolder>() {

    @LayoutRes
    var layoutId: Int = 0

    lateinit var items: List<*>
    var onCustomClickItemListner = fun(view: View, position: Int): Unit = Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, layoutId, parent, false)
        return BaseViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class BaseViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(ob: Any?) {
            binding.setVariable(BR.model, ob)
            binding.setVariable(BR.handler, this)
            binding.setVariable(BR.position, adapterPosition)
            binding.executePendingBindings()
        }

        fun onCustomClick(view: View, position: Int) {
            onCustomClickItemListner(view, position)
        }
    }
}