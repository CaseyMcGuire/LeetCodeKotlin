package problems.problem1254

class Solution {
  fun closedIsland(grid: Array<IntArray>): Int {
    val visitedCoordinates = mutableSetOf<Coordinate>()
    var numClosed = 0
    for (i in grid.indices) {
      for (j in grid[i].indices) {
        val isIsland = grid[i][j] == 0
        if (isIsland) {
          if (!visitedCoordinates.contains(Coordinate(i, j))) {
            val coordinates = getIsland(grid, i, j)
            visitedCoordinates.addAll(coordinates)
            val isClosed = isClosed(grid, coordinates)
            if (isClosed) {
              numClosed++
            }
          }
        }
      }
    }
    return numClosed
  }

  private fun isClosed(grid: Array<IntArray>, coordinates: Set<Coordinate>): Boolean {
    for (coordinate in coordinates) {
      if (coordinate.i == 0 || coordinate.j == 0 || coordinate.i == grid.size - 1 || coordinate.j == grid[coordinate.i].size - 1) {
        return false
      }
    }
    return true
  }

  private fun getIsland(grid: Array<IntArray>, i: Int, j: Int): Set<Coordinate> {
    val coordinates = mutableSetOf<Coordinate>()
    fun recurse(curI: Int, curJ: Int) {
      if (curI < 0 || curI >= grid.size || curJ < 0 || curJ >= grid[curI].size || grid[curI][curJ] == 1) {
        return
      }
      val currentCoordinate = Coordinate(curI, curJ)
      if (!coordinates.add(currentCoordinate)) {
        return
      }
      recurse(curI + 1, curJ)
      recurse(curI - 1, curJ)
      recurse(curI, curJ + 1)
      recurse(curI, curJ - 1)
    }
    recurse(i, j)
    return coordinates
  }
}

data class Coordinate(val i: Int, val j: Int)

fun main(args: Array<String>) {
  println(Solution().closedIsland(
    arrayOf(
      intArrayOf(1,1,1,1,1,1,1,0),
        intArrayOf(1,0,0,0,0,1,1,0),
        intArrayOf(1,0,1,0,1,1,1,0),
        intArrayOf(1,0,0,0,0,1,0,1),
        intArrayOf(1,1,1,1,1,1,1,0)
    )
  ))
}


