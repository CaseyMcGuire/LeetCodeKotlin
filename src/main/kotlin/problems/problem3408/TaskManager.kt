package problems.problem3408

import java.util.TreeSet

class TaskManager(tasks: List<List<Int>>) {

  private val taskIdToTask = mutableMapOf<Int, Task>()
  private val orderedTasks = TreeSet<Task>(
    compareBy(
      { it.priority },
      { it.taskId },
      { it.userId }
    )
  )

  init {
    for (taskArr in tasks) {
      addTask(taskArr[0], taskArr[1], taskArr[2])
    }
  }

  fun add(userId: Int, taskId: Int, priority: Int) {
    addTask(userId, taskId, priority)
  }

  private fun addTask(userId: Int, taskId: Int, priority: Int) {
    val task = Task(userId, taskId, priority)
    taskIdToTask[task.taskId] = task
    orderedTasks.add(task)
  }

  fun edit(taskId: Int, newPriority: Int) {
    val task = removeTask(taskId)
    addTask(task.userId, taskId, newPriority)
  }

  fun rmv(taskId: Int) {
    removeTask(taskId)
  }

  private fun removeTask(taskId: Int): Task {
    val task = taskIdToTask[taskId] ?: error("No task with id: $taskId")
    taskIdToTask.remove(taskId)
    orderedTasks.remove(task)
    return task
  }

  fun execTop(): Int {
    if (orderedTasks.isEmpty()) {
      return -1
    }
    val highestPriorityTask = orderedTasks.last()
    removeTask(highestPriorityTask.taskId)
    return highestPriorityTask.userId
  }

  private data class Task(val userId: Int, val taskId: Int, val priority: Int)

}