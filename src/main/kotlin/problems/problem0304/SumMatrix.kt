package problems.problem0304

class NumMatrix(matrix: Array<IntArray>) {

  private val sumMatrix: Array<IntArray>

  init {
    val height = matrix.size
    val width = matrix[0].size
    sumMatrix = Array(height) { IntArray(width) }
    for (i in matrix.indices) {
      var sum = 0
      for (j in matrix[i].indices) {
        sum += matrix[i][j]
        sumMatrix[i][j] = sum
      }
    }
  }

  fun sumRegion(row1: Int, col1: Int, row2: Int, col2: Int): Int {
    val numRows = row2 - row1
    var runningSum = 0
    for (row in row1..row2) {
      val rightSum = sumMatrix[row][col2]
      val leftSum = if (col1 == 0) 0 else sumMatrix[row][col1 - 1]
      runningSum += rightSum - leftSum
    }
    return runningSum
  }

}