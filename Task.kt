package com.example.studenttasks

enum class Priority {
    LOW,
    MEDIUM,
    HIGH
}

data class Task(
    val id: Int,
    val title: String,
    val isCompleted: Boolean = false,
    val priority: Priority = Priority.MEDIUM
)
val task = Task(
    id = 1,
    title = "Read Kotlin documentation"
)

val completedTask = task.copy(isCompleted = true)

val tasks = listOf(
    Task(1, "Prepare exercise"),
    Task(2, "Read documentation"),
    Task(3, "Run Android app")

)

