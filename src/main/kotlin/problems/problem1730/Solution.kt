package problems.problem1730

import java.util.*

class Solution {
  fun getFood(grid: Array<CharArray>): Int {
    val myLocation = findMyLocation(grid)

    val queue = LinkedList<Path>()
    val visited = mutableSetOf<Coordinate>()
    val rowRange = 0 until grid.size
    val columnRange = 0 until grid[0].size
    queue.addLast(Path(myLocation, 0))
    while (queue.isNotEmpty()) {
      val cur = queue.removeFirst()
      if (!visited.add(cur.c)) {
        continue
      }
      val coordinate = cur.c
      if (grid[coordinate.i][coordinate.j] == '#') {
        return cur.pathLength
      }

      val neighbors = coordinate.getNeighbors()
        .filter { it.i in rowRange && it.j in columnRange && !visited.contains(it) && grid[it.i][it.j] != 'X' }
      for (neighbor in neighbors) {
        queue.addLast(Path(neighbor, cur.pathLength + 1))
      }
    }

    return -1
  }

  fun findMyLocation(grid: Array<CharArray>): Coordinate {
    for (i in grid.indices) {
      for (j in grid[i].indices) {
        if (grid[i][j] == '*') {
          return Coordinate(i, j)
        }
      }
    }
    return Coordinate(-1, -1)
  }
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
data class Path(val c: Coordinate, val pathLength: Int)