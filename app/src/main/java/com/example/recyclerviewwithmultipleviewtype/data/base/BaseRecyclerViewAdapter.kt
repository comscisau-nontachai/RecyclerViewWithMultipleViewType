package com.example.recyclerviewwithmultipleviewtype.data.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T : Any , VB:ViewDataBinding> : RecyclerView.Adapter<BaseRecyclerViewAdapter.Companion.BaseViewHolder<VB>>() {

    /*
    var items = mutableListOf<T>()
    fun addItems(item : List<T>){
        this.items = item as MutableList<T>
        notifyDataSetChanged()
    }*/

    var items = listOf<T>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var listener : ((view : View , item:T, position : Int) -> Unit)? = null

    abstract fun getLayoutId() : Int

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VB> = BaseViewHolder<VB>(
        DataBindingUtil.inflate(LayoutInflater.from(parent.context),getLayoutId(),parent,false)
    )
    companion object{
        class BaseViewHolder<VB : ViewDataBinding>(val binding : VB) : RecyclerView.ViewHolder(binding.root)
    }
}
