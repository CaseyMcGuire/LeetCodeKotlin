package problems.problem1219

class Solution {
  fun getMaximumGold(grid: Array<IntArray>): Int {

    var maxSoFar = 0
    val visited = mutableSetOf<Coordinate>()
    fun dfs(curCoordinate: Coordinate, currentAmount: Int) {

      if (curCoordinate.i !in 0 until grid.size || curCoordinate.j !in 0 until grid[0].size || grid[curCoordinate.i][curCoordinate.j] == 0)  {
        return
      }
      if (!visited.add(curCoordinate)) {
        return
      }
      val newAmount = currentAmount + grid[curCoordinate.i][curCoordinate.j]
      if (newAmount > maxSoFar) {
        maxSoFar = newAmount
      }

      val neighbors = curCoordinate.getNeighbors()
      for (neighbor in neighbors) {
        dfs(neighbor, newAmount)
      }
      visited.remove(curCoordinate)
    }

    for (i in grid.indices) {
      for (j in grid[i].indices) {
        dfs(Coordinate(i, j), 0)
      }
    }
    return maxSoFar
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