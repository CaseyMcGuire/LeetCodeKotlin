package problems.problem1011

class Solution {
  fun shipWithinDays(weights: IntArray, days: Int): Int {
    var low = weights.max()
    var high = weights.sum()
    while (low < high) {
      val middle = ((low.toBigInteger() + high.toBigInteger()) / 2.toBigInteger()).toInt()
      if (canShipWithinDays(middle, weights, days)) {
        high = middle - 1
      }
      else {
        low = middle + 1
      }
    }

    return if (canShipWithinDays(low, weights, days)) {
      low
    }
    else {
      low + 1
    }
  }

  private fun canShipWithinDays(capacity: Int, weights: IntArray, maxDays: Int): Boolean {
    var weightOnCurrentDay = 0
    var curDay = 1
    for (weight in weights) {

      if (weightOnCurrentDay + weight > capacity) {
        curDay++
        weightOnCurrentDay = weight
      } else {
        weightOnCurrentDay += weight
      }
    }

    return maxDays >= curDay
  }
}