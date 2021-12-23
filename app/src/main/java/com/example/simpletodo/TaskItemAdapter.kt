package com.example.simpletodo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/*
* A bridge that tells the recycler view how to display the data we give it
* */
class TaskItemAdapter(val listOfItems: List<String>,
                      val longClickListener: OnLongClickListener) ://,
                      //val clickListener: OnClickListener) :
    RecyclerView.Adapter<TaskItemAdapter.ViewHolder>() {

    interface OnLongClickListener {
        fun onItemLongClicked(position: Int)
    }

//    interface OnClickListener {
//        fun onItemClicked(position: Int)
//    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        //Store references to elements in our layout view
        val textView: TextView

        init {
            textView = itemView.findViewById(android.R.id.text1)

            itemView.setOnLongClickListener {
                longClickListener.onItemLongClicked(adapterPosition)
                true
            }

//            itemView.setOnClickListener {
//                clickListener.onItemClicked(adapterPosition)
//                true
//            }
        }
    }
    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listOfItems.get(position)

        holder.textView.text = item
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }
}