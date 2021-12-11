package problems.problem0064

import java.util.*

class Solution {
  fun minPathSum(grid: Array<IntArray>): Int {
    if (grid.isEmpty() || grid[0].isEmpty()) {
      return 0
    }
    val sumGrid = Array(grid.size) { IntArray(grid[0].size) }
    val height = sumGrid.size
    val width = sumGrid[0].size
    sumGrid[height - 1][width - 1] = grid[height - 1][width - 1]
    val queue = LinkedList<Coordinate>()
    val visited = mutableSetOf<Coordinate>()
    queue.addLast(Coordinate(height - 1, width - 1))
    while (queue.isNotEmpty()) {
      val curCoordinate = queue.removeFirst()
      if (!visited.add(curCoordinate)) {
        continue
      }
      val i = curCoordinate.i
      val j = curCoordinate.j
      var value = grid[curCoordinate.i][curCoordinate.j]
      val canGoDown = curCoordinate.i < height - 1
      val canGoRight = curCoordinate.j < width - 1

      if (i > 0) {
        queue.addLast(Coordinate(i - 1, j))
      }
      if (j > 0) {
        queue.addLast(Coordinate(i, j - 1))
      }

      if (canGoDown && canGoRight) {
        value = Math.min(value + sumGrid[i + 1][j], value + sumGrid[i][j + 1])

      }
      else if (canGoDown) {
        value = value + sumGrid[i + 1][j]
      }
      else if (canGoRight) {
        value = value + sumGrid[i][j + 1]
      }
      sumGrid[i][j] = value
    }
    return sumGrid[0][0]
  }
}

data class Coordinate(val i: Int, val j: Int)