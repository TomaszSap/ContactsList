package com.example.myapplication123

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication123.Data.TaskItem
import com.example.myapplication123.Data.Tasks
import com.example.myapplication123.databinding.FragmentAddTaskBinding
import com.google.android.material.snackbar.Snackbar
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.random.Random.Default.nextInt


class AddTaskFragment : Fragment(), DatePickerDialog.OnDateSetListener {
    val args:AddTaskFragmentArgs by navArgs()
    private lateinit var binding:FragmentAddTaskBinding
    var day:Int=0
    var month:Int=0
    var year :Int =0
    var savedday:Int=0
    var savedmonth:Int=0
    var savedyear :Int =0
 override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
       binding= FragmentAddTaskBinding.inflate(inflater,container,false)
     pickDate()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.saveButton.setOnClickListener { saveTask() }
        binding.titleInput.setText(args.taskToEdit?.Name)
        binding.descriptionInput.setText(args.taskToEdit?.phoneNumber)
        binding.dateInput.setText(args.taskToEdit?.dateOfBirth)
    }
    private fun getDateCalendar()
    {
        val calendar:Calendar= Calendar.getInstance()
         day=calendar.get(Calendar.DAY_OF_MONTH)
        month=calendar.get(Calendar.MONTH)
       year=calendar.get(Calendar.YEAR)
    }
    private fun pickDate()
    {
     binding.dateButton.setOnClickListener {
         getDateCalendar()
         context?.let { it1 -> DatePickerDialog(it1,this,year,month,day).show() }
     }
    }


    private fun saveTask() {
        var title:String=binding.titleInput.text.toString()
        var phoneNumber:String =binding.descriptionInput.text.toString()
        var date:String=binding.dateInput.text.toString()

        val avatar:Int= (0..13).random()
        if(title.isEmpty()) title=getString(R.string.default_task_title)+ "${ Tasks.ITEMS.size+1}"
        val taskItem= TaskItem(
            {title+phoneNumber}.hashCode().toString(),
            title,
            date,
            phoneNumber,
            avatar

        )
        if(!args.edit and numberValidate(phoneNumber)){
        Tasks.addTask(taskItem)}
        else{
            Snackbar.make(requireView(),"Invalid number", Snackbar.LENGTH_LONG).show()
        }
        if(numberValidate(phoneNumber)){Tasks.updateTask(args.taskToEdit,taskItem)}
        else{
            binding.saveButton.isEnabled=false
        }
        val inputMethodManager: InputMethodManager =activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager //hide the software keyboard after finished input
        inputMethodManager.hideSoftInputFromWindow(binding.root.windowToken,0)
        findNavController().popBackStack(R.id.taskFragment,false)
    }
    private fun numberValidate (text: String): Boolean
    {
        if(mobileValidate(text)){
            binding.saveButton.isEnabled=true
        return true}
        else {binding.saveButton.isEnabled=false
            return false }
    }
    private fun mobileValidate(text: String?): Boolean {
        var p:Pattern=Pattern.compile("[0-9]{9}")
        var m: Matcher =p.matcher(text)
        return m.matches()
    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        savedday=p3
        savedmonth=p2
        savedyear=p3
        binding.dateInput.text="$savedday/ $savedmonth / $savedyear"
    }

}
