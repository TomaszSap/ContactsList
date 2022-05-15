package com.example.myapplication123

import android.R.drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication123.Data.TaskItem
import com.example.myapplication123.databinding.FragmentDisplayTaskBinding
import java.util.*


class DisplayTaskFragment : Fragment() {
    lateinit var binding:FragmentDisplayTaskBinding
    val args:DisplayTaskFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding= FragmentDisplayTaskBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val task: TaskItem = args.task
        binding.displayTitle.text=task.Name
        binding.displayDescritpion.text=task.phoneNumber
        binding.displayDate.text=task.dateOfBirth
        val resource=when(task.avatarId){
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
        binding.displayAvatar.setImageResource(resource)
        binding.displayEdit.setOnClickListener {
            val taskToEdit=DisplayTaskFragmentDirections.actionDisplayTaskFragmentToAddTaskFragment(
                taskToEdit = task,
                edit = true
            )
            findNavController().navigate(taskToEdit)
        }
    }

}