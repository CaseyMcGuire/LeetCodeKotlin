package problems.problem0807

class Solution {
  fun maxIncreaseKeepingSkyline(grid: Array<IntArray>): Int {
    val rowToMax = mutableMapOf<Int, Int>()
    val colToMax = mutableMapOf<Int, Int>()

    for (i in grid.indices) {
      for (j in grid[i].indices) {
        val value = grid[i][j]
        rowToMax[i] = Math.max(rowToMax[i] ?: 0, value)
        colToMax[j] = Math.max(colToMax[j] ?: 0, value)
      }
    }

    var total = 0
    for (i in grid.indices) {
      for (j in grid[i].indices) {
        total += Math.min(rowToMax[i]!!, colToMax[j]!!) - grid[i][j]
      }
    }
    return total
  }
}