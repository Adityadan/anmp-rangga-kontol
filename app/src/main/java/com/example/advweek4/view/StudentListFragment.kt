package com.example.advweek4.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.advweek4.R
import com.example.advweek4.databinding.FragmentStudentListBinding
import com.example.advweek4.model.StudentListAdapter


import com.example.advweek4.viewmodel.StudentListViewModel

class StudentListFragment : Fragment() {
    private val studentListViewModel: StudentListViewModel by viewModels()
    private val studentListAdapter = StudentListAdapter(arrayListOf())
    private lateinit var binding: FragmentStudentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_student_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentStudentListBinding.bind(view) // Inisialisasi binding di sini

        binding.refreshLayout.setOnRefreshListener {
            binding.recView.visibility = View.GONE
            binding.textError.visibility = View.GONE
            binding.progressLoad.visibility = View.VISIBLE
            studentListViewModel.refresh()
            binding.refreshLayout.isRefreshing = false
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        studentListViewModel.students.observe(viewLifecycleOwner) {
            studentListAdapter.updateStudentList(it)
        }

        studentListViewModel.studentsLoading.observe(viewLifecycleOwner) { loading ->
            Log.d("ViewModelObserve", "loading: $loading")
            val recyclerView = view?.findViewById<RecyclerView>(R.id.recView)
            val progressBar = view?.findViewById<ProgressBar>(R.id.progressBar)

            if (loading) {
                recyclerView?.visibility = View.GONE
                progressBar?.visibility = View.VISIBLE
            } else {
                recyclerView?.visibility = View.VISIBLE
                progressBar?.visibility = View.GONE
            }
        }

        studentListViewModel.studentsLoadError.observe(viewLifecycleOwner) { error ->
            Log.d("ViewModelObserve", "error: $error")
            view?.findViewById<TextView>(R.id.textError)?.visibility =
                if (error) View.VISIBLE else View.GONE
        }
    }
}