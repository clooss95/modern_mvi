package com.bonacode.modernmvi.sample.presentation.feature.dogs.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bonacode.modernmvi.databinding.ListItemDogBinding
import com.bonacode.modernmvi.sample.domain.feature.dogs.model.Dog

class DogListAdapter :
    ListAdapter<Dog, DogListAdapter.ViewHolder>(
        object : DiffUtil.ItemCallback<Dog>() {
            override fun areItemsTheSame(
                oldItem: Dog,
                newItem: Dog
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Dog,
                newItem: Dog
            ): Boolean =
                oldItem == newItem
        }
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ListItemDogBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ListItemDogBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Dog?) {
            binding.model = model
            binding.executePendingBindings()
        }
    }
}