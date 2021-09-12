package problems.problem0073

class Solution {
  fun setZeroes(matrix: Array<IntArray>): Unit {
    val zeroRows = mutableSetOf<Int>()
    val zeroColumns = mutableSetOf<Int>()
    for (rowIndex in matrix.indices) {
      for (columnIndex in matrix[rowIndex].indices) {
        val value = matrix[rowIndex][columnIndex]
        if (value == 0) {
          zeroRows.add(rowIndex)
          zeroColumns.add(columnIndex)
        }
      }
    }
    for (zeroRow in zeroRows) {
      for (i in matrix[zeroRow].indices) {
        matrix[zeroRow][i] = 0
      }
    }
    for (zeroColumn in zeroColumns) {
      for (i in matrix.indices) {
        matrix[i][zeroColumn] = 0
      }
    }
  }
}