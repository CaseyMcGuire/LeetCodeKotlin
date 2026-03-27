package problems.problem0063

class Solution {
  fun uniquePathsWithObstacles(obstacleGrid: Array<IntArray>): Int {
    val cache = mutableMapOf<Coordinate, Int>()

    fun dfs(coordinate: Coordinate): Int {
      val cachedResult = cache[coordinate]
      if (cachedResult != null) {
        return cachedResult
      }

      if (!coordinate.isInBounds(obstacleGrid) ||
        obstacleGrid.getAt(coordinate) == 1) {
        return 0
      }

      if (coordinate == Coordinate(obstacleGrid.size - 1, obstacleGrid[0].size - 1)) {
        return 1
      }

      var total = 0
      for (neighbor in coordinate.getNeighbors()) {
        total += dfs(neighbor)
      }
      cache[coordinate] = total
      return total
    }
    return dfs(Coordinate(0, 0))
  }

  private fun Array<IntArray>.getAt(c: Coordinate): Int {
    return this[c.i][c.j]
  }

  data class Coordinate(val i: Int, val j: Int) {
    fun getNeighbors(): List<Coordinate> {
      return listOf(
        Coordinate(i + 1, j),
        Coordinate(i, j + 1),
      )
    }

    fun isInBounds(grid: Array<IntArray>): Boolean {
      return this.i in grid.indices && this.j in grid[this.i].indices
    }
  }
}