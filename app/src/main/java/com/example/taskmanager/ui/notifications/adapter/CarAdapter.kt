package com.example.taskmanager.ui.notifications.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.data.Car
import com.example.taskmanager.databinding.ItemTaskBinding

class CarAdapter() : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    private val list = arrayListOf<Car>()

    @SuppressLint("NotifyDataSetChanged")
    fun setCars(cars: List<Car>) {
        list.clear()
        list.addAll(cars)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        return CarViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    inner class CarViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(car: Car) = with(binding) {
            textViewTitle.text = car.label
            textViewDescription.text = car.model
        }
    }

}