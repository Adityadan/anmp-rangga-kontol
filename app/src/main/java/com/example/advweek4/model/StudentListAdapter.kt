package com.example.advweek4.model


import android.view.LayoutInflater
import android.view.ViewGroup

import android.widget.ImageView
import android.widget.ProgressBar
import androidx.navigation.Navigation
//import androidx.navigation.R
import androidx.recyclerview.widget.RecyclerView
import com.example.advweek4.databinding.StudentListItemBinding
import com.example.advweek4.util.loadImage
import com.example.advweek4.R
import com.example.advweek4.view.StudentListFragmentDirections


class StudentListAdapter(val studentList:ArrayList<Student>):RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>()
{
    class StudentViewHolder(var binding: StudentListItemBinding):RecyclerView.ViewHolder(binding.root)

    fun updateStudentList(newStudentList: ArrayList<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = StudentListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        var imageView = holder.itemView.findViewById<ImageView>(R.id.imageProfile)
        var progressBar = holder.itemView.findViewById<ProgressBar>(R.id.progressBar)
        imageView.loadImage(studentList[position].photoUrl, progressBar)

        holder.binding.textID.text = studentList[position].id
        holder.binding.textFullName.text = studentList[position].fullName

        holder.binding.buttonOpenDetail.setOnClickListener {
            val action = StudentListFragmentDirections.actionStudentDetail()
            Navigation.findNavController(it).navigate(action)

        }
    }


}