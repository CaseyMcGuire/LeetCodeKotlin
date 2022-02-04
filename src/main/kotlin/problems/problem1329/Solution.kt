package problems.problem1329

class Solution {
  fun diagonalSort(mat: Array<IntArray>): Array<IntArray> {
    if (mat.size == 0 || mat[0].size == 0) {
      return emptyArray()
    }
    val sortedMat = Array<IntArray>(mat.size) { IntArray(mat[0].size) }
    var currentCorner = Coordinate(mat.size - 1, 0)
    var hitTop = false
    while (true) {
      if (!sortDiagonal(currentCorner, mat, sortedMat)) {
        break
      }

      if (hitTop) {
        currentCorner = Coordinate(currentCorner.i, currentCorner.j + 1)
      }
      else {
        val next = Coordinate(currentCorner.i - 1, currentCorner.j)
        if (!next.isInBoard(mat)) {
          hitTop = true
          currentCorner = Coordinate(currentCorner.i, currentCorner.j + 1)
        }
        else {
          currentCorner = next
        }
      }

    }
    return sortedMat
  }

  private fun sortDiagonal(start: Coordinate, mat: Array<IntArray>, sortedMat: Array<IntArray>): Boolean {
    if (!start.isInBoard(mat)) {
      return false
    }
    val coordinates = mutableListOf<Coordinate>()
    val nums = mutableListOf<Int>()
    var curCoordinate = start
    while (curCoordinate.isInBoard(mat)) {
      coordinates.add(curCoordinate)
      nums.add(mat[curCoordinate.i][curCoordinate.j])
      curCoordinate = Coordinate(curCoordinate.i + 1, curCoordinate.j + 1)
    }

    nums.sort()
    for (i in coordinates.indices) {
      val coordinate = coordinates[i]
      sortedMat[coordinate.i][coordinate.j] = nums[i]
    }

    return true
  }

}

data class Coordinate(val i: Int, val j: Int) {
  fun isInBoard(board: Array<IntArray>): Boolean {
    return this.i in 0 until board.size && this.j in 0 until board[0].size
  }
}