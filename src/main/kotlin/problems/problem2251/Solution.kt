package problems.problem2251

import java.util.PriorityQueue

class Solution {
  fun fullBloomFlowers(flowerArr: Array<IntArray>, timePersonArrives: IntArray): IntArray {

    val timeToPersonIndices = mutableMapOf<Int, MutableList<Int>>()
    for (i in timePersonArrives.indices) {
      val time = timePersonArrives[i]
      val indices = timeToPersonIndices.getOrPut(time) { mutableListOf() }
      indices.add(i)
    }
    val flowers = flowerArr.map { Interval(it[0], it[1]) }
      .sortedWith(compareBy<Interval>({ it.start }, { it.end }))

    val currentFlowerRanges = PriorityQueue<Interval>(compareBy({ it.end }))
    val seenFlowers = IntArray(timePersonArrives.size)

    var flowerIndex = 0
    val arrivalTimes = timeToPersonIndices.keys.sorted()
    for (arrivalTime in arrivalTimes) {
      while (flowerIndex < flowers.size && flowers[flowerIndex].start <= arrivalTime) {
        currentFlowerRanges.add(flowers[flowerIndex])
        flowerIndex++
      }

      while (currentFlowerRanges.isNotEmpty() && currentFlowerRanges.peek().end < arrivalTime) {
        currentFlowerRanges.poll()
      }

      for (index in timeToPersonIndices[arrivalTime]!!) {
        seenFlowers[index] = currentFlowerRanges.size
      }
    }

    return seenFlowers
  }
}

data class Interval(val start: Int, val end: Int)