package problems.problem0505

import java.util.*

class Solution {
  fun shortestDistance(maze: Array<IntArray>, start: IntArray, destination: IntArray): Int {
    val visited = mutableSetOf<Coordinate>()
    val queue = PriorityQueue<Path>(compareBy({it.pathLength}))
    val destinationCoordinate = Coordinate(destination[0], destination[1])
    queue.add(Path(Coordinate(start[0], start[1]), 0))
    while (queue.isNotEmpty()) {
      val curPath = queue.poll()
      val c = curPath.coordinate
      if (!visited.add(c)) {
        continue
      }
      if (c == destinationCoordinate) {
        return curPath.pathLength
      }
      val neighbors = getNeighbors(maze, curPath).filter { !visited.contains(it.coordinate) }
      neighbors.forEach { queue.add(it) }
    }
    return -1
  }

  private fun getNeighbors(maze: Array<IntArray>, path: Path): List<Path> {
    val neighbors = listOf(
      Coordinate(-1, 0),
      Coordinate(1, 0),
      Coordinate(0, -1),
      Coordinate(0, 1)
    )

    return neighbors.map { getNextCoordinateInDirection(maze, it.i, it.j, path) }.filterNotNull()
  }

  private fun getNextCoordinateInDirection(maze: Array<IntArray>, i: Int, j: Int, path: Path): Path? {
    val coordinate = path.coordinate
    var pathLength = path.pathLength + 1
    var cur = Coordinate(coordinate.i + i, coordinate.j + j)
    if (!maze.isValidCoordinate(cur)) {
      return null
    }
    while (true) {
      val next = Coordinate(cur.i + i, cur.j + j)
      if (!maze.isValidCoordinate(next)) {
        return Path(cur, pathLength)
      }
      pathLength++
      cur = next
    }
  }

  private fun Array<IntArray>.isValidCoordinate(coordinate: Coordinate): Boolean {
    val rowRange = 0 until this.size
    val columnRange = 0 until this[0].size
    return coordinate.i in rowRange && coordinate.j in columnRange && this[coordinate.i][coordinate.j] == 0
  }
}

data class Path(val coordinate: Coordinate, val pathLength: Int)
data class Coordinate(val i: Int, val j: Int)