package problems.problem1057

import java.util.*

class Solution {
  fun assignBikes(workers: Array<IntArray>, bikes: Array<IntArray>): IntArray {
    val workerList = workers.map { Coordinate(it[0], it[1]) }
    val bikeList = bikes.map { Coordinate(it[0], it[1]) }
    val pq = PriorityQueue<WorkerBikePair>(compareBy({it.distance}, {it.workerIndex}, {it.bikeIndex}))
    for (i in workerList.indices) {
      for (j in bikeList.indices) {
        val worker = workerList[i]
        val bike = bikeList[j]
        val distance = calculateManhattanDistance(worker, bike)
        pq.add(WorkerBikePair(i, j, worker, bike, distance))
      }
    }

    val assignments = mutableListOf<WorkerBikePair>()
    val assignedBikes = mutableSetOf<Int>()
    val assignedWorkers = mutableSetOf<Int>()

    while (assignments.size < workers.size) {
      val curPair = pq.poll()
      if (assignedBikes.contains(curPair.bikeIndex) || assignedWorkers.contains(curPair.workerIndex)) {
        continue
      }

      assignments.add(curPair)
      assignedBikes.add(curPair.bikeIndex)
      assignedWorkers.add(curPair.workerIndex)
    }

    val assignmentsSorted = assignments.sortedBy { it.workerIndex }
    return assignmentsSorted.map { it.bikeIndex }.toIntArray()
  }

  private fun calculateManhattanDistance(first: Coordinate, second: Coordinate): Int {
    return Math.abs(first.i - second.i) + Math.abs(first.j - second.j)
  }
}

data class WorkerBikePair(val workerIndex: Int, val bikeIndex: Int, val worker: Coordinate, val bike: Coordinate, val distance: Int)
data class Coordinate(val i: Int, val j: Int)