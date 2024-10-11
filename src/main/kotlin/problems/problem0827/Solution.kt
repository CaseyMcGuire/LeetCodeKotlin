package problems.problem0827

class Solution {
  fun largestIsland(grid: Array<IntArray>): Int {
    val emptySpots = mutableSetOf<Coordinate>()
    val coordinateToIsland = mutableMapOf<Coordinate, Island>()
    var curId = 0

    fun recurse(c: Coordinate, curIsland: Island) {
      val alreadyVisited = emptySpots.contains(c) || coordinateToIsland.containsKey(c)
      if (alreadyVisited ||
        c.i !in grid.indices ||
        c.j !in grid[c.i].indices ||
        grid[c.i][c.j] == 0) {
        return
      }
      curIsland.size++
      coordinateToIsland[c] = curIsland

      for (neighbor in c.getNeighbors()) {
        recurse(neighbor, curIsland)
      }
    }

    for (i in grid.indices) {
      for (j in grid[i].indices) {
        val c = Coordinate(i, j)
        if (grid[c.i][c.j] == 0) {
          emptySpots.add(c)
        }
        else if (coordinateToIsland.containsKey(c)) {
          continue
        }
        else {
          val newIsland = Island(curId, 0)
          recurse(c, newIsland)
          curId++
        }
      }
    }

    if (emptySpots.isEmpty()) {
      return grid.size * grid[0].size
    }

    var max = 0

    for (c in emptySpots) {
      val neighboringIslands = mutableSetOf<Island>()
      for (neighbor in c.getNeighbors()) {
        val neighboringIsland = coordinateToIsland[neighbor]
        if (neighboringIsland != null) {
          neighboringIslands.add(neighboringIsland)
        }
      }
      val sum = neighboringIslands.map { it.size }.sum() + 1
      max = Math.max(sum, max)
    }

    return max

  }
}

data class Coordinate(val i: Int, val j: Int) {
  fun getNeighbors(): List<Coordinate> {
    return listOf(
      Coordinate(i + 1, j),
      Coordinate(i - 1, j),
      Coordinate(i, j - 1),
      Coordinate(i, j + 1)
    )
  }
}
data class Island(val id: Int, var size: Int)