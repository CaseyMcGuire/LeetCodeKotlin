package problems.problem1293

import java.util.*

class Solution {
  fun shortestPath(grid: Array<IntArray>, k: Int): Int {

    val queue = LinkedList<Node>()
    queue.addFirst(Node(Coordinate(0, 0), k, 0))
    val visited = mutableSetOf<PathNode>()
    while (queue.isNotEmpty()) {
      val node = queue.removeLast()
      val pathNode = PathNode(node.coord, node.obstaclesLeft)
      if (!visited.add(pathNode)) {
        continue
      }
      val coordinate = node.coord
      val remaining = node.obstaclesLeft - grid[coordinate.i][coordinate.j]
      if (remaining < 0) {
        continue
      }
      if (coordinate.i == grid.size - 1 && coordinate.j == grid[coordinate.i].size - 1) {
        return node.distance
      }
      val neighbors = coordinate.getNeighbors()
        .filter { it.i in 0 until grid.size && it.j in 0 until grid[it.i].size }
      for (neighbor in neighbors) {
        val nextNode = Node(neighbor, remaining, node.distance + 1)
        queue.addFirst(nextNode)
      }

    }

    return -1

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
data class Node(val coord: Coordinate, var obstaclesLeft: Int, val distance: Int)
data class PathNode(val coord: Coordinate, val obstaclesLeft: Int)