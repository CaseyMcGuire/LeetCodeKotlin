package problems.problem0766

class Solution {
  fun isToeplitzMatrix(matrix: Array<IntArray>): Boolean {
    if (matrix.isEmpty() || matrix[0].isEmpty()) {
      return true
    }
    var curCoordinate = Coordinate(matrix.size - 1, 0)
    var goingRight = false
    while (isInMatrix(curCoordinate, matrix)) {
      if (!checkDiagonal(curCoordinate, matrix)) {
        return false
      }
      if (goingRight) {
        curCoordinate = Coordinate(0, curCoordinate.j + 1)
        if (!isInMatrix(curCoordinate, matrix)) {
          return true
        }
      }
      else {
        val next = Coordinate(curCoordinate.i - 1, 0)
        if (!isInMatrix(next, matrix)) {
          goingRight = true
          curCoordinate = Coordinate(0, 1)
        }
        else {
          curCoordinate = next
        }
      }
    }

    return true
  }

  private fun isInMatrix(coordinate: Coordinate, matrix: Array<IntArray>): Boolean {
    return coordinate.i in matrix.indices && coordinate.j in matrix[0].indices
  }

  private fun checkDiagonal(start: Coordinate, matrix: Array<IntArray>): Boolean {
    val element = matrix[start.i][start.j]
    var curCoordinate = start

    while (isInMatrix(curCoordinate, matrix)) {
      if (matrix[curCoordinate.i][curCoordinate.j] != element) {
        return false
      }
      curCoordinate = Coordinate(curCoordinate.i + 1, curCoordinate.j + 1)
    }
    return true
  }
}

data class Coordinate(val i: Int, val j: Int)