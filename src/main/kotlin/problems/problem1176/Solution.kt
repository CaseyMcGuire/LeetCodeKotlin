package problems.problem1176

class Solution {
  fun dietPlanPerformance(calories: IntArray, k: Int, lower: Int, upper: Int): Int {
    val sumArray = getSumArray(calories)
    var points = 0
    for (i in calories.indices) {
      val end = i + k - 1
      if (end >= calories.size) {
        break
      }
      var total = if (i == 0) sumArray[end] else sumArray[end] - sumArray[i - 1]
      if (total < lower) {
        points--
      }
      else if (total > upper){
        points++
      }
    }
    return points
  }

  private fun getSumArray(array: IntArray): IntArray {
    val sumArray = IntArray(array.size)
    for (i in array.indices) {
      if (i == 0) {
        sumArray[i] = array[i]
      }
      else {
        sumArray[i] = array[i] + sumArray[i - 1]
      }
    }
    return sumArray
  }
}