package com.example.taskmanager.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentHomeBinding
import com.example.taskmanager.model.Task
import com.example.taskmanager.ui.home.adapter.TaskAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val adapter = TaskAdapter(this::onLongClickForTask)

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        putData()
        attachToTaskFragment()

        binding.recyclerViewHomeFragment.adapter = adapter
    }

    private fun putData() {
        val list = App.database.taskDao().getAll()
        adapter.addTasks(list)
    }

    private fun attachToTaskFragment() {
        binding.fabCreateTask.setOnClickListener {
            findNavController().navigate(R.id.task_fragment)
        }
    }

    private fun onLongClickForTask(task: Task) {
        val alertDialogDeleteTask = AlertDialog.Builder(requireContext())

        val dialogView = layoutInflater.inflate(R.layout.alert_dialog_from_task, null)
        alertDialogDeleteTask.setView(dialogView)


        alertDialogDeleteTask.setPositiveButton("Confirm") { _, _ ->
            App.database.taskDao().delete(task)
            putData()
        }

        alertDialogDeleteTask.setNegativeButton("Cancel") { dialog, _ ->
            dialog?.cancel()
        }

        alertDialogDeleteTask.create().show()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}