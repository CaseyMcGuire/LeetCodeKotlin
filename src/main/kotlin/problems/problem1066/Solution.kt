package problems.problem1066

class Solution {
  fun assignBikes(workers: Array<IntArray>, bikes: Array<IntArray>): Int {
    val workerSet = workers.map { Coordinate(it[0], it[1]) }.toMutableSet()
    val bikeSet = bikes.map { Coordinate(it[0], it[1]) }.toMutableSet()
    val cache = mutableMapOf<BikeAndWorkers, Int>()
    fun recurse(): Int {
      if (bikeSet.isEmpty() || workerSet.isEmpty()) {
        return 0
      }
      val workerCopy = workerSet.toSet()
      val bikeCopy = bikeSet.toSet()
      var minSoFar: Int? = null
      for (bike in bikeCopy) {
        var minForBike: Int? = null
        val bikeAndWorkers = BikeAndWorkers(bike, workerCopy)
        val cached = cache[bikeAndWorkers]

        if (cached != null) {
          if (minSoFar == null || cached < minSoFar) {
            minSoFar = cached
          }
          continue
        }

        for (worker in workerCopy) {
          val distance = getManhattanDistance(worker, bike)
          workerSet.remove(worker)
          bikeSet.remove(bike)
          val remainingDistance = recurse()
          val totalDistance = distance + remainingDistance
          workerSet.add(worker)
          bikeSet.add(bike)
          if (minForBike == null || totalDistance < minForBike) {
            minForBike = totalDistance
          }
        }
        cache[bikeAndWorkers] = minForBike!!
        if (minSoFar == null || minForBike < minSoFar) {
          minSoFar = minForBike
        }
      }
      return minSoFar!!
    }
    return recurse()
  }

  private fun getManhattanDistance(first: Coordinate, second: Coordinate): Int {
    return Math.abs(first.i - second.i) + Math.abs(first.j - second.j)
  }
}

data class Coordinate(val i: Int, val j: Int)
data class BikeAndWorkers(val bike: Coordinate, val workers: Set<Coordinate>)

fun main(args: Array<String>) {
  println(Solution().assignBikes(arrayOf(intArrayOf(0,0), intArrayOf(1,1), intArrayOf(2,0)), arrayOf(intArrayOf(1,0), intArrayOf(2,2), intArrayOf(2,1))))
}