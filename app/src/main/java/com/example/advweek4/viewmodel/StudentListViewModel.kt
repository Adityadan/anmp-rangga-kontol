package com.example.advweek4.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.advweek4.model.Student
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.ArrayList

class StudentListViewModel(application: Application) : AndroidViewModel(application) {
    val students     = MutableLiveData<ArrayList<Student>>()
    val studentsLoading = MutableLiveData<Boolean>()
    val studentsLoadError = MutableLiveData<Boolean>()

    private val VOLLEY_TAG = "volley"
    private var queue: RequestQueue? = null

    fun refresh() {
        studentsLoading.value = true
        studentsLoadError.value = false

        /*students.value = arrayListOf(
            Student(
                "16055",
                "Nonie",
                "1998/03/28",
                "5718444778",
                "http://dummyimage.com/75x100.jpg/cc0000/ffffff",
            ),
            Student(
                "13312",
                "Rich",
                "1994/12/14",
                "3925444073",
                "http://dummyimage.com/75x100.jpg/5fa2dd/ffffff",
            ),
            Student(
                "11204",
                "Dinny",
                "1994/10/07",
                "6827808747",
                "http://dummyimage.com/75x100.jpg/5fa2dd/ffffff1",
            ),
        )*/

        queue = Volley.newRequestQueue(getApplication())

        val stringRequest = StringRequest(
            Request.Method.GET,
            "http://adv.jitusolution.com/student.php",
            { response ->
                Log.d("VolleyRequest", response)
                val students = Gson().fromJson<List<Student>>(
                    response,
                    object : TypeToken<List<Student>>() {}.type,
                )
                this.students.value = students as ArrayList<Student>
                studentsLoading.value = false
            },
            { error ->
                Log.d("VolleyRequest", error.message.toString())
                studentsLoading.value = false
                studentsLoadError.value = true
            },
        )

        stringRequest.tag = VOLLEY_TAG

        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(VOLLEY_TAG)
    }
}
