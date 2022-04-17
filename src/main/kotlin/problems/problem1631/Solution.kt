package problems.problem1631

import java.util.*

class Solution {
  // needed help with this one...
  fun minimumEffortPath(heights: Array<IntArray>): Int {
    val coordinateToMaxDifference = mutableMapOf<Coordinate, Int>()
    val visited = mutableSetOf<Coordinate>()
    val start = Coordinate(0, 0)
    coordinateToMaxDifference[start] = 0
    val queue = PriorityQueue<Path>(compareBy({ it.difference }))
    queue.add(Path(start, 0))
    while (queue.isNotEmpty()) {
      val cur = queue.poll().coordinate
      visited.add(cur)
      val maxDifference = coordinateToMaxDifference[cur]!!
      if (cur == Coordinate(heights.size - 1, heights[0].size - 1)) {
        return coordinateToMaxDifference[cur]!!
      }
      val neighbors = cur.getNeighbors()
        .filter { it.i in heights.indices && it.j in heights[0].indices && !visited.contains(it) }

      for (neighbor in neighbors) {
        val difference = Math.max(
          maxDifference,
          Math.abs(heights[cur.i][cur.j] - heights[neighbor.i][neighbor.j])
        )

        val neighborMaxDifference = coordinateToMaxDifference[neighbor]
        if (neighborMaxDifference == null || neighborMaxDifference > difference) {
          coordinateToMaxDifference[neighbor] = difference
          queue.add(Path(neighbor, difference))
        }
      }
    }

    return coordinateToMaxDifference[Coordinate(heights.size - 1, heights[0].size - 1)]!!
  }
}

data class Path(val coordinate: Coordinate, val difference: Int)

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