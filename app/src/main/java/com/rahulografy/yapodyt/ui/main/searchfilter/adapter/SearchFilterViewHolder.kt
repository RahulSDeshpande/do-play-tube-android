package com.rahulografy.yapodyt.ui.main.searchfilter.adapter

import androidx.recyclerview.widget.RecyclerView
import com.rahulografy.yapodyt.data.model.videocategories.VideoCategoryItem
import com.rahulografy.yapodyt.databinding.ItemSearchFilterBinding
import com.rahulografy.yapodyt.ui.main.searchfilter.listener.VideoCategoryListListener
import com.rahulografy.yapodyt.util.ext.show

class SearchFilterViewHolder(
    private val binding: ItemSearchFilterBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        videoCategoryItem: VideoCategoryItem,
        videoCategoryListListener: VideoCategoryListListener?
    ) {
        binding.apply {

            textViewSearchFilterName.text = videoCategoryItem.snippet.title

            imageViewSearchFilterCheck.show(videoCategoryItem.isChecked)

            root.setOnClickListener {
                videoCategoryListListener?.onVideoCategoryClicked(
                    listPosition = adapterPosition,
                    videoCategoryItem = videoCategoryItem
                )
            }

            executePendingBindings()
        }
    }
}
