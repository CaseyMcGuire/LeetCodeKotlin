package problems.problem1619

class Solution {
  fun trimMean(arr: IntArray): Double {
    arr.sort()
    val numToRemove = (arr.size.toDouble() * 0.05).toInt()
    var total = 0
    for (i in numToRemove until arr.size - (numToRemove)) {
      total += arr[i]
    }

    return total.toDouble() / (arr.size - (2 * numToRemove)).toDouble()
  }
}