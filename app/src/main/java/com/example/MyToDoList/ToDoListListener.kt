package com.example.myapplication123

interface ToDoListListener {
    fun onItemClick(position:Int)
    fun onItemLongClick(position:Int)
    fun onClick(position:Int)
}