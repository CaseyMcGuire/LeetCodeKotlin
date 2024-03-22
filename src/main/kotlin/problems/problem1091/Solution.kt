package problems.problem1091

import java.util.*

class Solution {
  fun shortestPathBinaryMatrix(grid: Array<IntArray>): Int {
    if (grid[0][0] == 1) {
      return -1
    }
    val visited = mutableSetOf<Coordinate>()
    val queue = LinkedList<Path>()
    queue.addLast(Path(Coordinate(0, 0), 1))
    while (queue.isNotEmpty()) {
      val curPath = queue.removeFirst()
      val coordinate = curPath.c
      if (coordinate.i == grid.size - 1 &&
        coordinate.j == grid[coordinate.i].size - 1) {
        if (grid[coordinate.i][coordinate.j] == 0) {
          return curPath.pathLength
        }
        else {
          return -1
        }
      }
      val neighbors = coordinate.getNeighbors()
      for (c in neighbors) {
        if (c.i !in grid.indices ||
          c.j !in grid[c.i].indices ||
          grid[c.i][c.j] == 1 ||
          !visited.add(c)) {
          continue
        }
        queue.addLast(Path(c, curPath.pathLength + 1))
      }
    }
    return -1
  }
}

data class Coordinate(val i: Int, val j: Int) {
  fun getNeighbors(): List<Coordinate> {
    return listOf(
      Coordinate(i - 1, j),
      Coordinate(i + 1, j),
      Coordinate(i, j + 1),
      Coordinate(i, j - 1),
      Coordinate(i - 1, j - 1),
      Coordinate(i + 1, j - 1),
      Coordinate(i - 1, j + 1),
      Coordinate(i + 1, j + 1)
    )
  }
}
data class Path(val c: Coordinate, val pathLength: Int)