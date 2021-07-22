package com.example.recyclerviewwithmultipleviewtype.ui.adapter

import com.example.recyclerviewwithmultipleviewtype.R
import com.example.recyclerviewwithmultipleviewtype.data.base.BaseRecyclerViewAdapter
import com.example.recyclerviewwithmultipleviewtype.databinding.ItemTitleBinding

class TestAdapter : BaseRecyclerViewAdapter<HomeRecyclerViewItem.Title, ItemTitleBinding>() {
    override fun getLayoutId(): Int = R.layout.item_title

    override fun onBindViewHolder(holder: Companion.BaseViewHolder<ItemTitleBinding>, position: Int) {
        holder.binding.textViewTitle.text = items[position].title
        holder.binding.textViewTitle.setOnClickListener {
            listener?.invoke(it,items[position],position)
        }
    }
}
