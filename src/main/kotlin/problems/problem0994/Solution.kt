package problems.problem0994

class Solution {
  fun orangesRotting(grid: Array<IntArray>): Int {
    var rottenOranges = getAllOranges(grid, 2)
    val rowRange = 0 until grid.size
    val columnRange = 0 until grid[0].size
    var minute = 0
    while (rottenOranges.isNotEmpty()) {
      val nextRottenOranges = mutableListOf<Coordinate>()
      for (orange in rottenOranges) {
        val neighbors = orange.getNeighbors().filter { it.i in rowRange && it.j in columnRange }
        for (neighbor in neighbors) {
          if (grid[neighbor.i][neighbor.j] == 1) {
            nextRottenOranges.add(neighbor)
          }
        }
      }
      for (orange in nextRottenOranges) {
        grid[orange.i][orange.j] = 2
      }
      rottenOranges = nextRottenOranges
      if (rottenOranges.isNotEmpty()) {
        minute++
      }
    }
    if (getAllOranges(grid, 1).isNotEmpty()) {
      return -1
    }
    return minute
  }

  fun getAllOranges(grid: Array<IntArray>, type: Int): List<Coordinate> {
    val oranges = mutableListOf<Coordinate>()
    for (i in grid.indices) {
      for (j in grid[i].indices) {
        if (grid[i][j] == type) {
          oranges.add(Coordinate(i, j))
        }
      }
    }
    return oranges
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