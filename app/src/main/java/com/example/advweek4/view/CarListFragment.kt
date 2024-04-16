package com.example.advweek4.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.advweek4.R
import com.example.advweek4.model.CarListAdapter
import com.example.advweek4.viewmodel.CarListViewModel

class CarListFragment : Fragment() {
    private val aircraftListViewModel: CarListViewModel by viewModels()
    private val aircraftListAdapter = CarListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_car_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.recyclerViewCar).apply {
            adapter = aircraftListAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }

        aircraftListViewModel.aircrafts.observe(viewLifecycleOwner) { aircrafts ->
            Log.d("ObserveViewModel.Aircraft", "size: ${aircrafts.size}")
            aircraftListAdapter.updateData(aircrafts)
        }

        aircraftListViewModel.refresh()
    }
}