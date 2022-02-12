package problems.problem1605

class Solution {
  fun restoreMatrix(rowSum: IntArray, colSum: IntArray): Array<IntArray> {
    // any element is at most Math.min(rowSum[i], colSum[j])
    val matrix = Array(rowSum.size) { IntArray(colSum.size) }
    fun recurse(row: Int, col: Int): Boolean {
      // we're at the end of a row
      val curRowSum = rowSum[row]
      val curColSum = colSum[col]
      if (row == rowSum.size - 1 && col == colSum.size - 1) {
        if (curRowSum == curColSum) {
          matrix[row][col] = curRowSum

          return true
        }
        return false
      }
      else if (col == colSum.size - 1) {
        // setting the row to this sum will result in the col going over
        if (curRowSum > curColSum) {
          return false
        }
        val remainingColSum = curColSum - curRowSum
        matrix[row][col] = curRowSum
        rowSum[row] = 0
        colSum[col] = remainingColSum
        if (recurse(row + 1, 0)) {
          return true
        }
      }
      else if (row == rowSum.size - 1 ) {
        if (curColSum > curRowSum) {
          return false
        }
        val remainingRowSum = curRowSum - curColSum
        matrix[row][col] = curColSum
        rowSum[row] = remainingRowSum
        colSum[col] = 0
        if (recurse(row, col + 1)) {
          return true
        }
      }
      else {
        val largestValue = Math.min(rowSum[row], colSum[col])
        for (i in largestValue downTo 0) {
          val remainingRowSum = curRowSum - i
          val remainingColSum = curColSum - i
          matrix[row][col] = i
          rowSum[row] = remainingRowSum
          colSum[col] = remainingColSum
          if (recurse(row, col + 1)) {
            return true
          }
        }
      }
      rowSum[row] = curRowSum
      colSum[col] = curColSum
      return false
    }
    recurse(0, 0)
    return matrix
  }
}