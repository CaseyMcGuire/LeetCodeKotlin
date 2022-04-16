package problems.problem0621

import java.util.*

class Solution {
  fun leastInterval(tasks: CharArray, n: Int): Int {
    val taskToNum = mutableMapOf<Char, Int>()
    for (task in tasks) {
      taskToNum.merge(task, 1) { cur, acc -> cur + acc }
    }

    val pq = PriorityQueue<Task>(compareBy({ it.nextTime }))
    var curTime = 0
    for (entry in taskToNum.entries) {
      pq.add(Task(0, entry.value))
    }

    while (pq.isNotEmpty()) {
      val curTask = pq.poll()
      if (curTask.nextTime <= curTime) {
        curTime++
      }
      else {
        curTime = curTask.nextTime + 1
      }

      val nextTask = Task(curTask.nextTime + n + 1, curTask.numLeft - 1)
      if (nextTask.numLeft > 0) {
        pq.add(nextTask)
      }
    }
    return curTime
  }
}

data class Task(val nextTime: Int, val numLeft: Int)