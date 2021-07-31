package problems.problem0054

class Solution {
  fun spiralOrder(matrix: Array<IntArray>): List<Int> {
    if (matrix.isEmpty()) {
      return listOf()
    }
    val visitedCoordinates = mutableSetOf<Pair<Int, Int>>()
    var currentDirection = Direction.RIGHT
    val spiralList = mutableListOf<Int>()
    var currentCoordinate = Pair(0, 0)
    visitedCoordinates.add(currentCoordinate)
    while (spiralList.size < matrix.size * matrix[0].size) {
      spiralList.add(matrix[currentCoordinate.first][currentCoordinate.second])
      var nextCoordinate = getNextCoordinate(currentCoordinate, currentDirection)
      if (
        nextCoordinate.first < 0 ||
        nextCoordinate.first >= matrix.size ||
        nextCoordinate.second < 0 ||
        nextCoordinate.second >= matrix[nextCoordinate.first].size ||
        !visitedCoordinates.add(nextCoordinate)
      ) {
        currentDirection = getNextDirection(currentDirection)
        nextCoordinate = getNextCoordinate(currentCoordinate, currentDirection)
        visitedCoordinates.add(nextCoordinate)
      }
      currentCoordinate = nextCoordinate
    }
    return spiralList
  }

  private fun getNextDirection(direction: Direction): Direction {
    return when (direction) {
      Direction.RIGHT -> Direction.DOWN
      Direction.DOWN -> Direction.LEFT
      Direction.LEFT -> Direction.UP
      Direction.UP -> Direction.RIGHT
    }
  }

  private fun getNextCoordinate(currentCoordinate: Pair<Int, Int>, direction: Direction): Pair<Int, Int> {
    return when (direction) {
      Direction.RIGHT -> Pair(currentCoordinate.first, currentCoordinate.second + 1)
      Direction.DOWN -> Pair(currentCoordinate.first + 1, currentCoordinate.second)
      Direction.LEFT -> Pair(currentCoordinate.first, currentCoordinate.second - 1)
      Direction.UP -> Pair(currentCoordinate.first - 1, currentCoordinate.second)
    }
  }
}

enum class Direction {
  RIGHT,
  DOWN,
  LEFT,
  UP
}

fun main(args: Array<String>) {
  //println(Solution().spiralOrder(arrayOf(intArrayOf(1,2,3, 4), intArrayOf(5,6,7,8), intArrayOf(9,10,11,12), intArrayOf(13,14,15,16))))
  println(Solution().spiralOrder(arrayOf(intArrayOf(1,2,3, 4,5 ), intArrayOf(6,7,8,9,10), intArrayOf(11,12,13,14,15), intArrayOf(16,17,18,19,20), intArrayOf(21,22,23,24,25))))
}