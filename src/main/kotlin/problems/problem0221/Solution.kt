package problems.problem0221

class Solution {
  fun maximalSquare(matrix: Array<CharArray>): Int {
    var curMax = 0
    for (i in matrix.indices) {
      for (j in matrix[i].indices) {
        if (matrix[i][j] == '0') {
          continue
        }
        val squareArea = getSquareArea(matrix, i, j)
        curMax = Math.max(squareArea, curMax)
      }
    }
    return curMax
  }

  private fun getSquareArea(matrix: Array<CharArray>, i: Int, j: Int): Int {
    var curLength = 2
    var curMaxArea = 1
    while (true) {
      val rightColumn = j + curLength - 1
      val bottomRow = i + curLength - 1
      if (bottomRow !in 0 until matrix.size || rightColumn !in 0 until matrix[i].size) {
        return curMaxArea
      }
      for (k in i until bottomRow) {
        if (matrix[k][rightColumn] != '1') {
          return curMaxArea
        }
      }

      for (k in j until rightColumn) {
        if (matrix[bottomRow][k] != '1') {
          return curMaxArea
        }
      }

      if (matrix[bottomRow][rightColumn] != '1') {
        return curMaxArea
      }

      curMaxArea = curLength * curLength
      curLength++
    }
  }
}