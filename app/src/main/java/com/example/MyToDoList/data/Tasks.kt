package com.example.myapplication123.Data

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

object Tasks {

    /**
     * An array of sample (placeholder) items.
     */
    val ITEMS: MutableList<TaskItem> = ArrayList()

    /**
     * A map of sample (placeholder) items, by ID.
     */
    private val COUNT = 3

    init {
        // Add some sample items.
        for (i in 1..COUNT) {
            addTask(createPlaceholderItem(i))
        }
    }

    fun addTask(item: TaskItem) {
        ITEMS.add(item)
    }

    private fun createPlaceholderItem(position: Int): TaskItem {
        return TaskItem(position.toString(), "contact " + position, makeDate(position),"726092516",(0..13).random())
    }

    private fun makeDate(position: Int): String {
        val builder = StringBuilder()
        builder.append("dd/mm/yy").append(position)
        return builder.toString()
    }

    fun updateTask(taskToEdit: TaskItem?, newTask: TaskItem) {
        taskToEdit?.let{
                oldTask->val indexOfOldTask= ITEMS.indexOf(oldTask)
            ITEMS.add(indexOfOldTask,newTask)
            ITEMS.removeAt(indexOfOldTask+1)
        }
    }
}
data class TaskItem(
    val id: String, val Name: String, val dateOfBirth:String, val phoneNumber: String,
    val avatarId: Int
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt())
    {  }

    override fun toString(): String = Name
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(Name)
        parcel.writeString(dateOfBirth)
        parcel.writeString(phoneNumber)
        parcel.writeInt(avatarId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TaskItem> {
        override fun createFromParcel(parcel: Parcel): TaskItem {
            return TaskItem(parcel)
        }

        override fun newArray(size: Int): Array<TaskItem?> {
            return arrayOfNulls(size)
        }
    }
}
