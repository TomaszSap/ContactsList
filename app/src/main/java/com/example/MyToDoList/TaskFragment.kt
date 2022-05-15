package com.example.myapplication123

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.findNavController
import com.example.myapplication123.Data.Tasks
import com.example.myapplication123.databinding.FragmentItemBinding
import com.example.myapplication123.databinding.FragmentItemListBinding
import com.google.android.material.snackbar.Snackbar

/**
 * A fragment representing a list of Items.
 */
class TaskFragment : Fragment(), ToDoListListener, OnDeleteDialogInteractionListener {

    private lateinit var binding:FragmentItemListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding= FragmentItemListBinding.inflate(inflater,container,false)
        with(binding.list) {
                layoutManager =LinearLayoutManager(context)
                adapter = MyTaskRecyclerViewAdapter(Tasks.ITEMS,this@TaskFragment)
            }
        binding.addButton.setOnClickListener{addButtonClick()}
        return binding.root
        }


    private fun addButtonClick() {
        findNavController().navigate(R.id.action_taskFragment_to_addTaskFragment)
    }
    override fun onItemClick(position: Int) {
        val actionTaskFragmentToDisplayTaskFragment=TaskFragmentDirections.actionTaskFragmentToDisplayTaskFragment(Tasks.ITEMS.get(position))
        findNavController().navigate(actionTaskFragmentToDisplayTaskFragment)
    }

    override fun onItemLongClick(position: Int) {
        val callIntent=Intent(Intent.ACTION_CALL)
        callIntent.setData(Uri.parse("tel:"+Tasks.ITEMS.get(position).phoneNumber))
        startActivity(callIntent)
    }
    override fun onClick(position: Int) {
        showDeleteDialog(position)
    }
    private fun showDeleteDialog(position: Int)
    {
        val deleteDialog=DeleteDialogFragment.newInstance(Tasks.ITEMS.get(position).Name,position,this)
        deleteDialog.show(requireActivity().supportFragmentManager,"Delete Dialog")
    }

    override fun onDialogPositiveClick(pos: Int?) {
    Tasks.ITEMS.removeAt(pos!!)
        Snackbar.make(requireView(),"Task Deleted",Snackbar.LENGTH_LONG).show()
        notifyDataSetChanged()

    }

    private fun notifyDataSetChanged() {
       val rVAdapter=binding.list.adapter
        rVAdapter?.notifyDataSetChanged()
        //When we remove an item from the Tasks list we have to notify the RecycleView adapter
        //that the change in data set occurred. There is a specific method for that called
        //notifyDataSetChanged
    }

    override fun onDialogNegativeClick(pos: Int?) {
        Snackbar.make(requireView(),"Delete cancelled",Snackbar.LENGTH_LONG).
        setAction("Redo",View.OnClickListener { showDeleteDialog(pos!!) }).show()

    }
}

