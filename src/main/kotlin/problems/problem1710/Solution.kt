package problems.problem1710

import java.util.*

class Solution {
  fun maximumUnits(boxTypes: Array<IntArray>, truckSize: Int): Int {
    val pq = PriorityQueue<BoxType>(compareByDescending({it.numberOfUnitsPerBox}))
    val boxTypesList = boxTypes.map { BoxType(it[0], it[1]) }
    for (boxType in boxTypesList) {
      pq.add(boxType)
    }

    var totalUnits = 0
    for (i in 0 until truckSize) {
      if (pq.isEmpty()) {
        break
      }
      val cur = pq.poll()
      totalUnits += cur.numberOfUnitsPerBox
      cur.numberOfBoxes--
      if (cur.numberOfBoxes > 0) {
        pq.add(cur)
      }
    }
    return totalUnits
  }
}

data class BoxType(var numberOfBoxes: Int, val numberOfUnitsPerBox: Int)