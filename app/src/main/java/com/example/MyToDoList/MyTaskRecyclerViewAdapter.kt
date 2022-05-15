package com.example.myapplication123

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.myapplication123.Data.Tasks
import com.example.myapplication123.Data.TaskItem

import com.example.myapplication123.databinding.FragmentItemBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyTaskRecyclerViewAdapter(
    private val values: List<TaskItem>,
    private val eventListener: ToDoListListener
) : RecyclerView.Adapter<MyTaskRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        val resource=when(item.avatarId){
            1 ->com.example.myapplication123.R.drawable.avatar_1
            2 ->com.example.myapplication123.R.drawable.avatar_2
            3->com.example.myapplication123.R.drawable.avatar_3
            4 ->com.example.myapplication123.R.drawable.avatar_4
            5 ->com.example.myapplication123.R.drawable.avatar_5
            6->com.example.myapplication123.R.drawable.avatar_6
            7 ->com.example.myapplication123.R.drawable.avatar_7
            8 ->com.example.myapplication123.R.drawable.avatar_8
            9->com.example.myapplication123.R.drawable.avatar_9
            10 ->com.example.myapplication123.R.drawable.avatar_10
            11 ->com.example.myapplication123.R.drawable.avatar_11
            12->com.example.myapplication123.R.drawable.avatar_12
            13->com.example.myapplication123.R.drawable.avatar_13
            else->com.example.myapplication123.R.drawable.avatar_1
        }
        holder.imgView.setImageResource(resource)
        holder.contentView.text = item.Name
        holder.itemContainer.setOnClickListener{

                eventListener.onItemClick(position)
        }
        holder.itemContainer.setOnLongClickListener{
            eventListener.onItemLongClick(position)
            return@setOnLongClickListener true
        }
        holder.button.setOnClickListener {
            eventListener.onClick(position)
        }
    }
    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val imgView: ImageView = binding.itemImg
        val contentView: TextView = binding.content
        val button:ImageButton=binding.deleteButton
        val itemContainer: View =binding.root
        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}