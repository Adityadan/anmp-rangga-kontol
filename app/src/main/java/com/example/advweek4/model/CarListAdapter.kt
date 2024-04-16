package com.example.advweek4.model
import android.icu.text.NumberFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.advweek4.R

class CarListAdapter(
    private val cars: MutableList<Car> = mutableListOf(),
) : RecyclerView.Adapter<CarListAdapter.ViewHolder>() {
    private val numberFormat = NumberFormat.getNumberInstance()

    class ViewHolder(
        view: View,
    ) : RecyclerView.ViewHolder(view) {
        val textCarType: TextView
        val textCarName: TextView
        val textCarOrigin: TextView
        val textPerformanceTopSpeed: TextView
        val textPerformanceCruiseSpeed: TextView
        val textPerformanceRange: TextView
        val textFeatures: TextView

        init {
            textCarType = view.findViewById(R.id.textCarType)
            textCarName = view.findViewById(R.id.textCarName)
            textCarOrigin = view.findViewById(R.id.textCarCountry)
            textPerformanceTopSpeed = view.findViewById(R.id.textPerformanceTopSpeed)
            textPerformanceCruiseSpeed = view.findViewById(R.id.textPerformanceCruiseSpeed)
            textPerformanceRange = view.findViewById(R.id.textPerformanceRange)
            textFeatures = view.findViewById(R.id.textCarFeatures)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_car, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = cars.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            val aircraft = cars[position]

            textCarType.text =
                aircraft.type
            textCarName.text =
                "${aircraft.manufacturer} ${aircraft.name}"
            textCarOrigin.text =
                "${aircraft.country}. First flight in ${aircraft.firstFlight}, introduced in ${aircraft.introduced}"
            textPerformanceTopSpeed.text =
                "Top speed: ${numberFormat.format(aircraft.performance!!.topSpeed)} km/s"
            textPerformanceCruiseSpeed.text =
                "Cruise speed: ${numberFormat.format(aircraft.performance!!.cruiseSpeed)} km/s"
            textPerformanceRange.text =
                "Range: ${numberFormat.format(aircraft.performance!!.range)} km"
            textFeatures.text =
                aircraft.features.joinToString(", ")
        }
    }

    fun updateData(data: List<Car>) {
        cars.clear()
        cars.addAll(data)
        notifyDataSetChanged()
    }

}
