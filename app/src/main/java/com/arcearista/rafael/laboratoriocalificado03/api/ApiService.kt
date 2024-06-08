package com.arcearista.rafael.laboratoriocalificado03.api

import com.arcearista.rafael.laboratoriocalificado03.model.TeacherResponse
import retrofit2.http.GET

interface ApiService {
    @GET("list/teacher")
    suspend fun getTeachers(): TeacherResponse
}
