package problems.problem1526

class Solution {
  fun minNumberOperations(target: IntArray): Int {
    var currentOperations = 0

    fun recurse(interval: Interval, value: Int) {
      val minimumValue = getMinimumValue(target, interval)
      currentOperations += minimumValue - value
      val subarrays = getSubarraysAboveTargetValue(target, interval, minimumValue)
      subarrays.forEach {recurse(it, minimumValue)}
    }
    getSubarraysAboveTargetValue(target, Interval(0, target.size), 0).forEach {recurse(it, 0)}
    return currentOperations
  }

  fun getMinimumValue(target: IntArray, interval: Interval): Int {
    var currentMin = target[interval.start]
    for (i in interval.start + 1 until interval.end) {
      if (target[i] < currentMin) {
        currentMin = target[i]
      }
    }
    return currentMin
  }

  fun getSubarraysAboveTargetValue(target: IntArray, currentInterval: Interval, value: Int): List<Interval> {
    var currentStart = currentInterval.start
    val intervals = mutableListOf<Interval>()
    for (i in currentInterval.start until currentInterval.end) {
      if (target[i] <= value) {
        // no interval here
        if (currentStart == i) {
          currentStart++
        }
        else {
          intervals.add(Interval(currentStart, i))
          currentStart = i
        }
      }
      else if (i == currentInterval.end - 1) {
        intervals.add(Interval(currentStart, currentInterval.end))
      }
    }
    return intervals
  }
}

data class Interval(val start: Int, val end: Int)


fun main(args: Array<String>) {
  println(Solution().minNumberOperations(intArrayOf(3,1,1,2)))
}