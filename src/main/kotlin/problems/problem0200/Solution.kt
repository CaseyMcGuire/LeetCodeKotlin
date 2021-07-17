package problems.problem0200

class Solution {
  fun numIslands(grid: Array<CharArray>): Int {
    val visitedCoordinates = mutableSetOf<Pair<Int, Int>>()

    fun recurse(i: Int, j: Int): Unit {
      if (i < 0 || i >= grid.size || j  < 0 || j >= grid[i].size) {
        return
      }
      val currentCoordinate = Pair(i, j)
      if (grid[i][j] == '0' || visitedCoordinates.contains(currentCoordinate)) {
        return
      }
      visitedCoordinates.add(currentCoordinate)
      recurse(i + 1, j)
      recurse(i - 1, j)
      recurse(i, j + 1)
      recurse(i, j - 1)
    }
    var numIslands = 0
    for (i in grid.indices) {
      for (j in grid[i].indices) {
        val currentCoordinate = Pair(i, j)
        if (grid[i][j] == '1' && !visitedCoordinates.contains(currentCoordinate)) {
          numIslands++
        }
        recurse(i, j)
      }
    }
    return numIslands
  }
}

