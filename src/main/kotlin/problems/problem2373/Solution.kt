package problems.problem2373

class Solution {
  fun largestLocal(grid: Array<IntArray>): Array<IntArray> {
    val matrix = Array(grid.size - 2) { IntArray(grid.size - 2) }
    for (i in 1 until grid.size - 1) {
      for (j in 1 until grid[i].size - 1) {
        matrix[i - 1][j - 1] = grid.largestAround(i, j)
      }
    }
    return matrix
  }

  private fun Array<IntArray>.largestAround(row: Int, column: Int): Int {
    var largest = this[row][column]
    for (i in row - 1..row + 1) {
      for (j in column - 1..column + 1) {
        largest = Math.max(largest, this[i][j])
      }
    }
    return largest
  }
}