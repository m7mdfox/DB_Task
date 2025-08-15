package com.example.db_task.ui.projects

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProjectFragment: Fragment() {
    private val viewModel: ProjectViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.allProjects.observe(viewLifecycleOwner){
            projects ->
            Log.d("DB_TEST","Live Data Project -> $projects")
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getTasksForProject(projectId = 1).collectLatest { tasks ->
                Log.d("DB_TEST", "Flow Tasks for Project with Id (1) : $tasks")
            }
        }
    }
}