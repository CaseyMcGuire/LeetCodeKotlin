package problems.problem0498

class Solution {
  fun findDiagonalOrder(mat: Array<IntArray>): IntArray {
    val diagonalOrder = mutableListOf<Int>()
    fun recurse(row: Int, column: Int, up: Boolean) {
      diagonalOrder.add(mat[row][column])
      if (row == mat.size - 1 && column == mat[0].size - 1) {
        return
      }

      val (nextRow, nextColumn) = if (up) {
        Pair(row - 1, column + 1)
      }
      else {
        Pair(row + 1, column - 1)
      }

      if (nextRow in 0 until mat.size && nextColumn in 0 until mat[0].size) {
        recurse(nextRow, nextColumn, up)
      }
      else if (up) {
        if (column == mat[0].size - 1) {
          recurse(row + 1, column, false)
        }
        else {
          recurse(row, column + 1, false)
        }
      }
      else {
        if (row == mat.size - 1) {
          recurse(row, column + 1, true)
        }
        else {
          recurse(row + 1, column, true)
        }
      }

    }
    recurse(0, 0, true)
    return diagonalOrder.toIntArray()
  }

}