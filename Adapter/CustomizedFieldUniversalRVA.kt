package com.gkprojects.cmmsandroidapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CustomizedFieldUniversalRVA <T>(
    private var items: ArrayList<T>,
    private val layoutId: Int,
    private val bind: (item: T, view: View) -> Unit
) : RecyclerView.Adapter<CustomizedFieldUniversalRVA<T>.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(item: T) {
            bind(item, itemView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int = items.size

    /**
     * Updates the adapter's data set and refreshes the RecyclerView.
     */
    fun setData(newItems: ArrayList<T>) {
        // Replace the existing items with new data
        items.clear()
        items.addAll(newItems)
        // Notify the adapter that the data set has changed
        notifyDataSetChanged()
    }

    /**
     * Adds a single item to the list and notifies the adapter.
     */
    fun addItem(item: T) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    /**
     * Removes a single item from the list and notifies the adapter.
     */
    fun removeItem(position: Int) {
        if (position >= 0 && position < items.size) {
            items.removeAt(position)
            notifyItemRemoved(position)
            // Optionally notify the item range that has shifted
            notifyItemRangeChanged(position, items.size)
        }
    }
    fun getItemAtPosition(position: Int): T? {
        return if (position in 0 until items.size) {
            items[position]
        } else {
            null // Return null if the position is out of bounds
        }
    }
}