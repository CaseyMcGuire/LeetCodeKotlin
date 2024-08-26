package problems.problem3071

class Solution {
  fun minimumOperationsToWriteY(grid: Array<IntArray>): Int {
    if (grid.size == 1) {
      return 0
    }
    val yFrequency = mutableMapOf<Int, Int>()
    val otherFrequency = mutableMapOf<Int, Int>()

    for (i in grid.indices) {
      for (j in grid[i].indices) {
        if (isPartOfY(i, j, grid.size)) {
          yFrequency.merge(grid[i][j], 1) { cur, acc -> cur + acc }
        }
        else {
          otherFrequency.merge(grid[i][j], 1) { cur, acc -> cur + acc }
        }
      }
    }

    val totalY = yFrequency.entries.map { it.value }.sum()
    var min: Int? = null

    for (i in 0..2) {
      val otherElemsInY = yFrequency.entries.filter { it.key != i }.map { it.value }.sum()
      for (j in 0..2) {
        if (i == j) {
          continue
        }
        val totalChanges = (otherFrequency[i] ?: 0) + (otherFrequency[j] ?: 0) + otherElemsInY
        if (min == null || min > totalChanges) {
          min = totalChanges
        }
      }
    }
    return min!!
  }

  private fun isPartOfY(row: Int, col: Int, n: Int): Boolean {
    val midPoint = n / 2
    if (row >= midPoint) {
      return col == midPoint
    }
    return row == col || row + col == n - 1
  }
}