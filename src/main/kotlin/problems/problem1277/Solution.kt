package problems.problem1277

class Solution {
  fun countSquares(matrix: Array<IntArray>): Int {
    var numSquaresFound = 0
    for (i in matrix.indices) {
      for (j in matrix[i].indices) {
        numSquaresFound += countSquaresAtIndex(matrix, i, j)
      }
    }
    return numSquaresFound
  }

  private fun countSquaresAtIndex(matrix: Array<IntArray>, i: Int, j: Int): Int {
    if (matrix[i][j] == 0) {
      return 0
    }
    var numMatrices = 0
    var targetCoordinate = Coordinate(i, j)
    while (targetCoordinate.i < matrix.size && targetCoordinate.j < matrix[targetCoordinate.i].size) {
      var bottomCoordinate = Coordinate(targetCoordinate.i, j)
      var rightCoordinate = Coordinate(i, targetCoordinate.j)
      if (matrix[targetCoordinate.i][targetCoordinate.j] == 0) {
        return numMatrices
      }
      while (bottomCoordinate != targetCoordinate && rightCoordinate != targetCoordinate) {
        if (matrix[bottomCoordinate.i][bottomCoordinate.j] == 0 || matrix[rightCoordinate.i][rightCoordinate.j] == 0) {
          return numMatrices
        }
        bottomCoordinate = Coordinate(targetCoordinate.i, bottomCoordinate.j + 1)
        rightCoordinate = Coordinate(rightCoordinate.i + 1, targetCoordinate.j)
      }


      targetCoordinate = Coordinate(targetCoordinate.i + 1, targetCoordinate.j + 1)
      numMatrices += 1
    }
    return numMatrices
  }

  data class Coordinate(val i: Int, val j: Int)
}

fun main(args: Array<String>) {
  /*println(Solution().countSquares(
    arrayOf(
      intArrayOf(0,1,1,1),
      intArrayOf(1,1,1,1),
    intArrayOf(0,1,1,1)
    )
  ))*/

  println(Solution().countSquares(
    arrayOf(
      intArrayOf(0,0,0),
      intArrayOf(0,1,0),
      intArrayOf(0,1,0),
      intArrayOf(1,1,1),
      intArrayOf(1,1,0)
    )
  ))
}