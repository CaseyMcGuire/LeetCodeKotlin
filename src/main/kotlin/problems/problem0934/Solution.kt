package problems.problem0934

import java.util.LinkedList

class Solution {
  fun shortestBridge(grid: Array<IntArray>): Int {
    val firstIsland = getFirstIsland(grid)

    val visitedOrPending = firstIsland.toMutableSet()
    val nodes = firstIsland.map { Node(it, 0) }
    val queue = LinkedList<Node>(nodes)
    while (queue.isNotEmpty()) {
      val curNode = queue.removeFirst()
      val curCoordinate = curNode.coordinate
      if (curNode.distance != 0 && grid[curCoordinate.i][curCoordinate.j] == 1) {
        return curNode.distance - 1
      }

      for (neighbor in curCoordinate.getNeighbors()) {
        if (neighbor.i !in grid.indices ||
          neighbor.j !in grid[neighbor.i].indices ||
          visitedOrPending.contains(neighbor)) {
          continue
        }

        visitedOrPending.add(neighbor)
        queue.addLast(Node(neighbor, curNode.distance + 1))
      }
    }

    return -1
  }

  fun getFirstIsland(grid: Array<IntArray>): Set<Coordinate> {
    val island = mutableSetOf<Coordinate>()

    fun recurse(c: Coordinate) {
      if (c.i !in grid.indices ||
        c.j !in grid[c.i].indices ||
        island.contains(c) ||
        grid[c.i][c.j] == 0) {
        return
      }
      island.add(c)

      for (neighbor in c.getNeighbors()) {
        recurse(neighbor)
      }
    }

    for (i in grid.indices) {
      for (j in grid[i].indices) {
        if (grid[i][j] == 0) {
          continue
        }
        val c = Coordinate(i, j)
        recurse(c)
        return island
      }
    }

    throw IllegalArgumentException("Grid does not contain any islands")
  }
}

data class Node(val coordinate: Coordinate, val distance: Int)

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