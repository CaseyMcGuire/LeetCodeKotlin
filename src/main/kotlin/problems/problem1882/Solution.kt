package problems.problem1882

import java.util.*

class Solution {
  fun assignTasks(servers: IntArray, tasks: IntArray): IntArray {
    val serverList = servers.mapIndexed { index, elem ->  Server(elem, index) }
    val taskList = tasks.mapIndexed { index, elem -> Task(elem, index) }
    val freeServers = PriorityQueue<Server>(compareBy({it.weight}, {it.index}))
    val processingTasks = PriorityQueue<ProcessingTask>(compareBy({ it.completionTime }))
    for (server in serverList) {
      freeServers.add(server)
    }
    val taskQueue = LinkedList<Task>()
    val taskServerAssignment = IntArray(tasks.size)

    var currentTime = 0
    while (true) {
      if (currentTime < tasks.size) {
        taskQueue.add(taskList[currentTime])
      }

      // get servers that have completed processing
      while (processingTasks.isNotEmpty() && processingTasks.peek().completionTime <= currentTime) {
        freeServers.add(processingTasks.poll().server)
      }

      // assign tasks to free servers
      while (freeServers.isNotEmpty() && taskQueue.isNotEmpty()) {
        val nextTask = taskQueue.removeFirst()
        val freeServer = freeServers.poll()
        taskServerAssignment[nextTask.index] = freeServer.index
        processingTasks.add(ProcessingTask(currentTime + nextTask.processingTime, freeServer))
      }

      if (currentTime < tasks.size) {
        currentTime++
      }
      else if (taskQueue.isNotEmpty() && processingTasks.isNotEmpty()) {
        currentTime = processingTasks.peek().completionTime
      }
      else {
        break
      }
    }
    return taskServerAssignment
  }
}

data class Server(val weight: Int, val index: Int)
data class Task(val processingTime: Int, val index: Int)
data class ProcessingTask(val completionTime: Int, val server: Server)