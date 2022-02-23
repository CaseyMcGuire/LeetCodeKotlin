package problems.problem0463

class Solution {
  // what counts as perimeter? a land/water border
  fun islandPerimeter(grid: Array<IntArray>): Int {
    val firstCoordinate = findFirstCoordinate(grid)
    val visited = mutableSetOf<Coordinate>()
    var totalPerimeter = 0
    val heightRange = 0 until grid.size
    val widthRange = 0 until grid[0].size
    fun recurse(curCoordinate: Coordinate) {
      // already visited this coordinate
      if (!visited.add(curCoordinate)) {
        return
      }

      val neighbors = curCoordinate.getNeighbors()

      for (neighbor in neighbors) {
        // we're bordering water
        if (neighbor.i !in heightRange || neighbor.j !in widthRange || grid[neighbor.i][neighbor.j] == 0) {
          totalPerimeter += 1
        }
        else {
          recurse(neighbor)
        }
      }
    }
    recurse(firstCoordinate)
    return totalPerimeter
  }

  private fun findFirstCoordinate(grid: Array<IntArray>): Coordinate {
    for (i in grid.indices) {
      for (j in grid[i].indices) {
        if (grid[i][j] == 1) {
          return Coordinate(i, j)
        }
      }
    }
    throw IllegalArgumentException("Must have at least one square of land")
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