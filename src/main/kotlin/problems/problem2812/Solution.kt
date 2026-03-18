package problems.problem2812

import java.util.PriorityQueue

class Solution {
  fun maximumSafenessFactor(grid: List<List<Int>>): Int {
    val coordinateToThiefDistance = getCoordinateToThiefDistance(grid)
    var maxSoFar: Int? = null
    val pq = PriorityQueue<Coordinate>(
      compareByDescending({ coordinateToThiefDistance[it] ?: error("Coordinate not found: ${it}") })
    )
    pq.add(Coordinate(0, 0))
    val visitedOrPending = mutableSetOf(Coordinate(0, 0))
    val terminal = Coordinate(grid.size - 1, grid[0].size - 1)

    while (pq.isNotEmpty()) {
      val next = pq.poll()
      val distance = coordinateToThiefDistance[next] ?: error("Coordinate not found: ${next}")
      if (maxSoFar == null || maxSoFar > distance) {
        maxSoFar = distance
      }

      if (next == terminal) {
        return maxSoFar
      }

      for (neighbor in next.getNeighbors()) {
        if (neighbor.i !in grid.indices ||
          neighbor.j !in grid[neighbor.i].indices ||
          visitedOrPending.contains(neighbor)) {
          continue
        }
        visitedOrPending.add(neighbor)
        pq.add(neighbor)
      }
    }
    error("No path to terminal")
  }

  private fun getCoordinateToThiefDistance(grid: List<List<Int>>): Map<Coordinate, Int> {
    val thiefCoordinates = mutableSetOf<Coordinate>()
    for (i in grid.indices) {
      for (j in grid[i].indices) {
        if (grid[i][j] == 1) {
          thiefCoordinates.add(Coordinate(i, j))
        }
      }
    }

    val coordinateToThiefDistance = mutableMapOf<Coordinate, Int>()
    val visitedOrPending = mutableSetOf<Coordinate>()
    val queue = ArrayDeque<Node>()
    for (thief in thiefCoordinates) {
      visitedOrPending.add(thief)
      queue.addLast(Node(thief, 0))
    }

    while (queue.isNotEmpty()) {
      val (cur, distance) = queue.removeFirst()
      coordinateToThiefDistance[cur] = distance

      for (neighbor in cur.getNeighbors()) {
        if (neighbor.i !in grid.indices ||
          neighbor.j !in grid[neighbor.i].indices ||
          grid[neighbor.i][neighbor.j] != 0 ||
          visitedOrPending.contains(neighbor)) {
          continue
        }
        visitedOrPending.add(neighbor)
        queue.addLast(Node(neighbor, distance + 1))
      }
    }
    return coordinateToThiefDistance
  }

  data class Coordinate(val i: Int, val j: Int) {

    fun getNeighbors(): List<Coordinate> {
      return listOf(
        Coordinate(i + 1, j),
        Coordinate(i - 1, j),
        Coordinate(i, j + 1),
        Coordinate(i, j - 1)
      )
    }
  }

  data class Node(val cur: Coordinate, val distance: Int)
}