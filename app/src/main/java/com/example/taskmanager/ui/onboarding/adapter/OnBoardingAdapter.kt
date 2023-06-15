package com.example.taskmanager.ui.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.taskmanager.databinding.ItemOnBoardingBinding
import com.example.taskmanager.model.OnBoarding

class OnBoardingAdapter(private val onClick: () -> Unit) :
    Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    private val list = listOf(
        OnBoarding("Легко организуйте свои задачи и проекты",
            "Организуйте свои задачи, установите сроки и расставьте приоритеты в своей работе",
            "https://qph.cf2.quoracdn.net/main-qimg-bc8ebeeeae92f6d362ea26182657afe7-pjlq"),
        OnBoarding("Всегда на связи с командой в любое время в любом месте",
            "Общайтесь, делитесь обновлениями и назначайте задачи в режиме реального времени",
            "https://img.freepik.com/premium-vector/a-man-performs-tasks-completing-assignments-to-the-end-list-of-cases-or-tasks-business-project_159757-1381.jpg"),
        OnBoarding("Все, что вы можете сделать в приложении",
            "Создавайте и систематизируйте задачи, устанавливайте напоминания и отслеживайте свой прогресс — все в одном месте.",
            "https://www.highgear.com/wp-content/uploads/2022/12/why-is-task-management-important-1.jpg")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            ItemOnBoardingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class OnBoardingViewHolder(private val binding: ItemOnBoardingBinding) :
        ViewHolder(binding.root) {
        fun bind(onBoarding: OnBoarding) = with(binding) {
            textViewTitle.text = onBoarding.title
            textViewDescription.text = onBoarding.description
            Glide.with(binding.imageViewOnboard).load(onBoarding.image).into(binding.imageViewOnboard)
            buttonGetStarted.isVisible = adapterPosition == list.lastIndex
            buttonGetStarted.setOnClickListener {
                onClick()
            }
        }

    }

}