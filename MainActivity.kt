package com.example.studenttasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.studenttasks.ui.theme.StudentTasksTheme


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudentTasksTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    StudentTasksApp(modifier = Modifier.padding(paddingValues = innerPadding))
                }
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            }
            for (task in tasks) {
                println(task.title)
            }
            Column {
                Text("Student Tasks")
                Text("3 tasks")
                Button(onClick = { }) {
                    Text("Add Task")
                }
                Row {
                    Text("Task")
                    Button(onClick = { }) {
                        Text("Done")
                    }
                }
                TaskRow(
                    title = "Prepare Kotlin exercise",
                    isCompleted = false
                )

                TaskRow(
                    title = "Install Android Studio",
                    isCompleted = true
                )
                Button(
                    onClick = {
                        println("Button clicked")
                    }
                ) {
                    Text("Add Task")
                }
            }
        }
    }




    val courseName = "Mobile Development"
    var completedTasks = 0
    val maxTasks = 10

    // maxTasks = 20  // Error
    val studentName: String = "Ana"
    val taskCount: Int = 3
    val isCompleted: Boolean = false

    //val = no se puede cambiar
    //var = variable normal
    //$ -> significa String

    val count = 5
    val message = "You have $count tasks"



    fun taskMessage(taskCount: Int): String {
        return "You have $taskCount tasks"
    }

    fun statusMessage(completed: Boolean): String {
        return if (completed) {
            "Completed"
        } else {
            "Pending"
        }
    }

    fun priorityLabel(priority: Priority): String {
        return when (priority) {
            Priority.LOW -> "Low"
            Priority.MEDIUM -> "Medium"
            Priority.HIGH -> "High"
        }
    }


    @Composable
    fun StudentTasksApp(modifier: Modifier = Modifier) {
        var tasks by remember {
            mutableStateOf(
                listOf(
                    Task(
                        id = 1,
                        title = "Prepare Kotlin exercise",
                        priority = Priority.HIGH
                    ),
                    Task(
                        id = 2,
                        title = "Read Android documentation",
                        priority = Priority.MEDIUM
                    ),
                    Task(
                        id = 3,
                        title = "Run the app",
                        isCompleted = true,
                        priority = Priority.LOW
                    )
                )
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Student Tasks",
                style = MaterialTheme.typography.headlineMedium
            )

            CounterExample()
        }
    }

    //3Layout

    @Composable
    fun AppTitle() {
        Text("Student Tasks")
    }


    @Composable
    fun TaskList(
        tasks: List<Task>
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = tasks,
                key = { task -> task.id }
            ) { task ->
                TaskRow(task)
            }
        }
    }

    @Composable
    fun TaskRow(task: Task) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = task.isCompleted,
                onCheckedChange = null
            )

            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f)
            ) {
                Text(task.title)
                Text(
                    text = task.priority.name,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }

    @Composable
    fun TaskRow(
        title: String,
        isCompleted: Boolean
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isCompleted,
                onCheckedChange = null
            )

            Text(
                text = title,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }



    @Composable
    fun CounterExample() {
        var count by remember { mutableStateOf(0) }

        Column {
            Text("Tasks: $count")

            Button(
                onClick = {
                    count++
                }
            ) {
                Text("Add")
            }
        }
    }

    @Composable
    fun AddTaskExample() {
        var taskTitle by rememberSaveable {
            mutableStateOf("")
        }

        Column {
            OutlinedTextField(
                value = taskTitle,
                onValueChange = { newValue ->
                    taskTitle = newValue
                },
                label = {
                    Text("Task title")
                }
            )

            Text("You typed: $taskTitle")
        }
    }
}