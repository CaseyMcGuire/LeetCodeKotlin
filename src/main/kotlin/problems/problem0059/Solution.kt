package problems.problem0059

class Solution {
  fun generateMatrix(n: Int): Array<IntArray> {
    val matrix = Array(n) { IntArray(n) }
    var i = 1
    var direction = Direction.RIGHT
    var coordinate = Coordinate(0,0)
    val visited = mutableSetOf<Coordinate>(coordinate)
    while (i <= n * n) {
      visited.add(coordinate)
      matrix[coordinate.i][coordinate.j] = i
      var nextCoordinate = coordinate.getNextCoordinate(direction)
      if (visited.contains(nextCoordinate) || nextCoordinate.i !in 0 until n || nextCoordinate.j !in 0 until n) {
        direction = direction.getNextDirection()
        nextCoordinate = coordinate.getNextCoordinate(direction)
      }
      i++
      coordinate = nextCoordinate
    }

    return matrix
  }

}

data class Coordinate(val i: Int, val j: Int) {
  fun getNextCoordinate(direction: Direction): Coordinate {
    return when (direction) {
      Direction.RIGHT -> Coordinate(i, j + 1)
      Direction.LEFT -> Coordinate(i, j - 1)
      Direction.UP -> Coordinate(i - 1, j)
      Direction.DOWN -> Coordinate(i + 1, j)
    }
  }
}

enum class Direction {
  RIGHT,
  DOWN,
  LEFT,
  UP;

  fun getNextDirection(): Direction {
    return when(this) {
      RIGHT -> DOWN
      DOWN -> LEFT
      UP -> RIGHT
      LEFT -> UP
    }
  }
}