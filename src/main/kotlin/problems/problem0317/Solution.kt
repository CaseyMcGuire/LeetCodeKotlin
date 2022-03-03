package problems.problem0317

import java.util.*

class Solution {
  fun shortestDistance(grid: Array<IntArray>): Int {
    val buildings = mutableListOf<Coordinate>()
    var totalVisited = mutableSetOf<Coordinate>()
    for (i in grid.indices) {
      for (j in grid[i].indices) {
        val elem = grid[i][j]
        val coordinate = Coordinate(i, j)
        totalVisited.add(coordinate)
        if (elem == 1) {
          buildings.add(coordinate)
        }
      }
    }


    val spaceToDistance = mutableMapOf<Coordinate, Int>()
    var visited = totalVisited.toSet()
    for (building in buildings) {
      val curVisited = bfs(building, grid, spaceToDistance)
      visited = visited intersect curVisited
    }

    var minSoFar: Int? = null
    for (entry in spaceToDistance.entries) {
      if (!visited.contains(entry.key)) {
        continue
      }
      if (minSoFar == null || minSoFar > entry.value) {
        minSoFar = entry.value
      }
      if (minSoFar == 2) {
        println(entry.key)
      }
    }

    return minSoFar ?: -1
  }

  private fun bfs(c: Coordinate,
                  grid: Array<IntArray>,
                  distances: MutableMap<Coordinate, Int>): Set<Coordinate> {
    val queue = LinkedList<Item>()
    for (neighbor in c.getNeighbors()) {
      queue.addLast(Item(neighbor, 1))
    }
    var size = 0
    val visited = mutableSetOf<Coordinate>()
    while (queue.isNotEmpty()) {
      val cur = queue.removeFirst()
      val curCoordinate = cur.c
      val distance = cur.distance
      if (curCoordinate.i !in grid.indices ||
        curCoordinate.j !in grid[curCoordinate.i].indices) {
        continue
      }

      if (!visited.add(curCoordinate)) {
        continue
      }

      val elem = grid[curCoordinate.i][curCoordinate.j]
      if (elem != 0) {
        continue
      }
      distances.merge(curCoordinate, distance) { cur, acc -> cur + acc }
      val neighbors = curCoordinate.getNeighbors()
      for (neighbor in neighbors) {
        queue.addLast(Item(neighbor, distance + 1))
      }
    }
    return visited
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
data class Item(val c: Coordinate, val distance: Int)