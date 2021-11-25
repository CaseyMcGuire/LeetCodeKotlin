package problems.problem0694

class Solution {
  fun numDistinctIslands(grid: Array<IntArray>): Int {
    val visited = mutableSetOf<Coordinate>()
    val distinctIslands = mutableSetOf<MutableSet<Coordinate>>()
    fun dfs(c: Coordinate, startCoordinate: Coordinate, curSet: MutableSet<Coordinate>) {
      val zeroIndexedCoordinate = Coordinate(c.i - startCoordinate.i, c.j - startCoordinate.j)
      if (curSet.contains(zeroIndexedCoordinate)) {
        return
      }
      curSet.add(zeroIndexedCoordinate)
      visited.add(c)

      val validNeighbors = getNeighbors(c).filter { grid.isValid(it) }
      for (neighbor in validNeighbors) {
        dfs(neighbor, startCoordinate, curSet)
      }
    }

    for (i in grid.indices) {
      for (j in grid[i].indices) {
        val startCoordinate = Coordinate(i, j)
        if (visited.contains(startCoordinate) || grid[i][j] == 0) {
          continue
        }
        val islandCoords = mutableSetOf<Coordinate>()
        dfs(startCoordinate, startCoordinate, islandCoords)
        distinctIslands.add(islandCoords)
      }
    }
    return distinctIslands.size
  }

  private fun Array<IntArray>.isValid(c: Coordinate): Boolean {
    val rowRange = 0 until this.size
    val columnRange = 0 until this[0].size
    return c.i in rowRange && c.j in columnRange && this[c.i][c.j] == 1
  }

  private fun getNeighbors(c: Coordinate): List<Coordinate> {
    val i = c.i
    val j = c.j
    return listOf(
      Coordinate(i + 1, j),
      Coordinate(i - 1, j),
      Coordinate(i, j + 1),
      Coordinate(i, j - 1)
    )
  }
}


data class Coordinate(val i: Int, val j: Int)