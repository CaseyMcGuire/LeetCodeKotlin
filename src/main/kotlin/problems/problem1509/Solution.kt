package problems.problem1509

class Solution {
  fun minDifference(nums: IntArray): Int {
    if (nums.size <= 3) {
      return 0
    }
    val sortedList = nums.toList().sorted()
    var start = 0
    var end = sortedList.size - 3 - 1
    var minDifferenceSoFar: Int = sortedList[end] - sortedList[start]
    while (end < sortedList.size) {
      val currentDifference = sortedList[end] - sortedList[start]
      if (currentDifference < minDifferenceSoFar) {
        minDifferenceSoFar = currentDifference
      }
      start++
      end++
    }
    return minDifferenceSoFar
  }
}