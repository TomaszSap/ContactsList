package com.example.myapplication123

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication123.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding //connecting xml with class, no need to use findviewbyid
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}