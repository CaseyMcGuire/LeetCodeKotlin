package problems.problem2365

class Solution {
  fun taskSchedulerII(tasks: IntArray, space: Int): Long {
    val taskToNextDay = mutableMapOf<Int, Long>()
    var curDay = 0L
    for (i in tasks.indices) {
      curDay++
      val nextDay = taskToNextDay[tasks[i]]
      if (nextDay != null && curDay < nextDay) {
        curDay = nextDay
      }
      taskToNextDay[tasks[i]] = curDay + space + 1
    }
    return curDay
  }
}