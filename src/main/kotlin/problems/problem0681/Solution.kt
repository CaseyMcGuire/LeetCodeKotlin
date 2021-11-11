package problems.problem0681

import java.util.*

class Solution {
  fun nextClosestTime(time: String): String {
    val splitTime = time.split(":")
    val hours = splitTime[0].toInt()
    val minutes = splitTime[1].toInt()
    val digits = TreeSet(time.filter { it != ':' }.map { Character.getNumericValue(it) })
    val h1 = Character.getNumericValue(splitTime[0][0])
    val h2 = Character.getNumericValue(splitTime[0][1])
    val m1 = Character.getNumericValue(splitTime[1][0])
    val m2 = Character.getNumericValue(splitTime[1][1])
    val smallest = digits.first()
    // first, figure out if we can just increment minutes
    if (minutes < 59) {
      // can we increment single minute
      val largerM2 = digits.higher(m2)
      if (largerM2 != null) {
        return "$h1$h2:$m1$largerM2"
      }
      // can we increment second minute
      val largerM1 = digits.headSet(6, false).higher(m1)
      if (largerM1 != null) {
        return "$h1$h2:$largerM1$smallest"
      }
    }

    if (hours < 23) {
      val largerH2 = digits.higher(h2)
      if (largerH2 != null) {
        return "$h1$largerH2:$smallest$smallest"
      }

      if (hours < 20) {
        val largerH1 = digits.headSet(2, true).higher(h1)
        if (largerH1 != null) {
          return "$largerH1$smallest:$smallest$smallest"
        }
      }
    }

    return "$smallest$smallest:$smallest$smallest"
  }
}