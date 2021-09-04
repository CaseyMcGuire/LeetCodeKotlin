package problems.problem0739

import java.util.*

class Solution {
  fun dailyTemperatures(temperatures: IntArray): IntArray {
    val temperatureToMostRecentDay = TreeMap<Int, Int>()
    val higherTemps = LinkedList<Int>()

    var day = temperatures.size - 1
    for (temperature in temperatures.reversed()) {
      val mostRecentDayWithHigherTemperature = temperatureToMostRecentDay.tailMap(temperature, false).values.min()
      if (mostRecentDayWithHigherTemperature == null) {
        higherTemps.addFirst(0)
      }
      else {
        higherTemps.addFirst(mostRecentDayWithHigherTemperature - day)
      }

      temperatureToMostRecentDay[temperature] = day
      day--
    }
    return higherTemps.toIntArray()
  }
}