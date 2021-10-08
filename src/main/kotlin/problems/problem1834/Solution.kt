package problems.problem1834

import java.util.*

class Solution {
  fun getOrder(tasks: Array<IntArray>): IntArray {
    if (tasks.isEmpty()) {
      return intArrayOf()
    }
    val taskList = LinkedList(tasks.mapIndexed { i, elem -> Task(i, elem[0], elem[1]) }.sortedBy { it.enqueueTime })
    val pq = PriorityQueue<Task>(compareBy({it.processingTime }, {it.index}))
    val firstTime = taskList[0].enqueueTime

    while (taskList.isNotEmpty() && taskList[0].enqueueTime == firstTime) {
      pq.add(taskList.removeFirst())
    }

    var currentTask = pq.poll()

    var currentTime = currentTask.enqueueTime
    val orderedTasks = mutableListOf<Task>()
    while (true) {

      val nextTime = currentTime + currentTask.processingTime
      orderedTasks.add(currentTask)

      while (taskList.isNotEmpty() && taskList[0].enqueueTime <= nextTime) {
        pq.add(taskList.removeFirst())
      }

      if (pq.isNotEmpty()) {
        currentTask = pq.poll()
        currentTime = nextTime
      }
      else if (taskList.isNotEmpty()) {

        val headTime = taskList[0].enqueueTime
        while (taskList.isNotEmpty() && taskList[0].enqueueTime == headTime) {
          pq.add(taskList.removeFirst())
        }
        currentTask = pq.poll()
        currentTime = currentTask.enqueueTime
      }
      else {
        break
      }
    }

    return orderedTasks.map { it.index }.toIntArray()
  }
}

data class Task(val index: Int, val enqueueTime: Int, val processingTime: Int)