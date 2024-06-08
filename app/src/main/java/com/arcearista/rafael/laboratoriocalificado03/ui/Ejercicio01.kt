package com.arcearista.rafael.laboratoriocalificado03.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.arcearista.rafael.laboratoriocalificado03.databinding.ActivityEjercicio01Binding
import com.arcearista.rafael.laboratoriocalificado03.model.Teacher
import com.arcearista.rafael.laboratoriocalificado03.model.TeacherResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Ejercicio01 : AppCompatActivity(), TeacherAdapter.OnItemClickListener {

    private lateinit var binding: ActivityEjercicio01Binding
    private lateinit var teacherAdapter: TeacherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEjercicio01Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        loadTeachers()
    }
    private fun setupRecyclerView() {
        teacherAdapter = TeacherAdapter(emptyList(), this)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@Ejercicio01)
            adapter = teacherAdapter
        }
    }
    private fun loadTeachers() {
        val jsonResponse = """
            {
              "teachers": [
                {
                  "name": "Rafael",
                  "lastName": "Arce",
                  "photo": "http://example.com/photo.jpg",
                  "phone": "990675577",
                  "email": "rafael.arce@tecsup.edu.pe"
                },
                {
                  "name": "Rafifi",
                  "lastName": "Arista",
                  "photo": "http://example.com/photo2.jpg",
                  "phone": "990675577",
                  "email": "rafael.arce@tecsup.edu.pe"
                }
              ]
            }
        """.trimIndent()
        val gson = Gson()
        val teacherType = object : TypeToken<TeacherResponse>() {}.type
        val teacherResponse: TeacherResponse = gson.fromJson(jsonResponse, teacherType)
        teacherAdapter = TeacherAdapter(teacherResponse.teachers, this)
        binding.recyclerView.adapter = teacherAdapter
    }
    override fun onItemClick(teacher: Teacher) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:${teacher.phone}")
        }
        startActivity(intent)
    }
    override fun onItemLongClick(teacher: Teacher) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:${teacher.email}")
        }
        startActivity(intent)
    }
}
