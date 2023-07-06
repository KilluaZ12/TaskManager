package com.example.taskmanager.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentTaskBinding
import com.example.taskmanager.model.Task

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private var task: Task? = null
    private lateinit var data: Task

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            task = requireArguments().getSerializable("task") as Task?
        }

        fillEditTexts()
        buttonHandleClick()

    }

    private fun fillEditTexts() {
        if (task != null) {
            binding.editTextTitle.setText(task!!.title)
            binding.editTextDesc.setText(task!!.description)
            binding.btnSave.text = getString(R.string.update)
        } else {
            binding.btnSave.text = getString(R.string.save)
        }
    }

    private fun buttonHandleClick() {
        binding.btnSave.setOnClickListener {
            data = Task(
                title = binding.editTextTitle.text.toString(),
                description = binding.editTextDesc.text.toString()
            )
            if(binding.editTextTitle.text.toString().isNotEmpty() && binding.editTextDesc.text.toString().isNotEmpty()) {
                if (task != null) {
                    updateTask()
                } else {
                    saveTask()
                }
                findNavController().navigateUp()
            } else {
                Toast.makeText(requireContext(), getString(R.string.you_can_t_save_an_empty_task), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveTask() {
        task = Task(data.id, data.title, data.description)
        App.database.taskDao().insert(task!!)
    }

    private fun updateTask() {
        task!!.title = data.title
        task!!.description = data.description
        App.database.taskDao().update(task!!)
    }
}
