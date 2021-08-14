package problems.problem0695

class Solution {
  fun maxAreaOfIsland(grid: Array<IntArray>): Int {
    val visitedCoordinates = mutableSetOf<Coordinate>()
    var maxSoFar = 0
    fun dfs(i: Int, j: Int): Int {
      if (i < 0 || i >= grid.size || j < 0 || j >= grid[i].size || grid[i][j] == 0 || !visitedCoordinates.add(Coordinate(i,j))) {
        return 0
      }
      return 1 + dfs(i + 1, j) + dfs(i - 1, j) + dfs(i, j + 1) + dfs(i, j - 1)
    }

    for (i in grid.indices) {
      for (j in grid[i].indices) {
        val islandSize = dfs(i, j)
        if (islandSize > maxSoFar) {
          maxSoFar = islandSize
        }
      }
    }
    return maxSoFar
  }

  data class Coordinate(val i: Int, val j: Int)
}