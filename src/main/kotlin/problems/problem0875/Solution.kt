package problems.problem0875

class Solution {
  fun minEatingSpeed(piles: IntArray, h: Int): Int {
    if (piles.isEmpty()) {
      return -1
    }

    var max = piles.max()!!
    var min = 1
    var minSoFar = max
    while (max >= min) {
      val middle = min + ((max - min) / 2)
      if (canEatPileInTime(middle, piles, h)) {
        max = middle - 1
        if (middle < minSoFar) {
          minSoFar = middle
        }
      }
      else {
        min = middle + 1
      }
    }
    return minSoFar
  }

  private fun canEatPileInTime(k: Int, piles: IntArray, h: Int): Boolean {
    var hoursElapsed = 0
    for (pile in piles) {
      val numWholeHours = pile / k
      val partialHour = if (pile % k != 0) 1 else 0
      val totalHours = numWholeHours + partialHour
      hoursElapsed += totalHours
      if (hoursElapsed > h) {
        return false
      }
    }
    return true
  }
}

fun main(args: Array<String>) {
  println(Solution().minEatingSpeed(intArrayOf(3,6,7,11), 8))
}