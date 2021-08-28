package problems.problem0562

class Solution {
  fun longestLine(mat: Array<IntArray>): Int {
    var maxLineLengthSoFar = 0
    for (i in mat.indices) {
      for (j in mat[i].indices) {
        if (mat[i][j] == 0) {
          continue
        }
        val directions = getDirections(i, j, mat)
        if (directions.isEmpty()) {
          continue
        }
        val maxLineLength = directions.map {getLengthOfLine(i, j, it, mat)}.max()!!
        if (maxLineLength > maxLineLengthSoFar) {
          maxLineLengthSoFar = maxLineLength
        }
      }
    }
    return maxLineLengthSoFar
  }

  private fun getLengthOfLine(row: Int, column: Int, direction: Direction, mat: Array<IntArray>): Int {
    var currentCoordinate = Coordinate(row, column)
    var lengthSoFar = 0
    while (true) {
      if (currentCoordinate.i !in 0 until mat.size || currentCoordinate.j !in 0 until mat[0].size) {
        return lengthSoFar
      }
      if (mat[currentCoordinate.i][currentCoordinate.j] == 0) {
        return lengthSoFar
      }
      lengthSoFar++
      currentCoordinate = getNextCoordinate(currentCoordinate, direction)
    }

  }

  private fun getDirections(
    row: Int,
    column: Int,
    mat: Array<IntArray>
  ): MutableList<Direction> {
    val directions = mutableListOf<Direction>()
    if (column == 0 || mat[row][column - 1] == 0) {
      directions.add(Direction.HORIZONTAL)
    }
    if (row == 0 || mat[row - 1][column] == 0) {
      directions.add(Direction.VERTICAL)
    }
    if (column == 0 || row == 0 || mat[row - 1][column - 1] == 0) {
      directions.add(Direction.DIAGONAL)
    }
    if (row == 0 || column == mat[row].size - 1 || mat[row - 1][column + 1] == 0) {
      directions.add(Direction.ANTI_DIAGONAL)
    }
    return directions
  }

  private fun getNextCoordinate(
    coordinate: Coordinate,
    direction: Direction
  ): Coordinate {
    return when (direction) {
      Direction.HORIZONTAL -> Coordinate(coordinate.i, coordinate.j + 1)
      Direction.VERTICAL -> Coordinate(coordinate.i + 1, coordinate.j)
      Direction.DIAGONAL -> Coordinate(coordinate.i + 1, coordinate.j + 1)
      Direction.ANTI_DIAGONAL -> Coordinate(coordinate.i + 1, coordinate.j - 1)
    }
  }
}

data class Coordinate(val i: Int, val j: Int)

enum class Direction {
  HORIZONTAL,
  VERTICAL,
  DIAGONAL,
  ANTI_DIAGONAL
}