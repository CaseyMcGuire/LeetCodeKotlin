package problems.problem0723

class Solution {
  fun candyCrush(board: Array<IntArray>): Array<IntArray> {
    while (crushCandies(board)) {
      clearCandies(board)
    }
    return board
  }

  private fun clearCandies(board: Array<IntArray>) {
    val numColumns = board[0].size
    for (column in 0 until numColumns) {
      var cur = board.size - 1
      for (row in board.size - 1 downTo 0) {
        val elem = board[row][column]
        if (elem != 0) {
          board[cur][column] = elem
          if (cur != row) {
            board[row][column] = 0
          }
          cur--
        }
      }
    }
  }

  private fun crushCandies(board: Array<IntArray>): Boolean {
    val downCandies = mutableSetOf<Coordinate>()
    val rightCandies = mutableSetOf<Coordinate>()
    for (i in board.indices) {
      for (j in board[i].indices) {
        if (board[i][j] == 0) {
          continue
        }
        val currentCoordinate = Coordinate(i, j)
        if (!downCandies.contains(currentCoordinate)) {
          val down = getDownCandies(currentCoordinate, board)
          downCandies.addAll(down)
        }
        if (!rightCandies.contains(currentCoordinate)) {
          val right = getRightCandies(currentCoordinate, board)
          rightCandies.addAll(right)
        }
      }
    }
    if (downCandies.isEmpty() && rightCandies.isEmpty()) {
      return false
    }
    val allCoords = mutableSetOf<Coordinate>()
    allCoords.addAll(downCandies)
    allCoords.addAll(rightCandies)
    for (coordinate in allCoords) {
      board[coordinate.i][coordinate.j] = 0
    }
    return true
  }

  private fun getDownCandies(coord: Coordinate, board: Array<IntArray>): MutableSet<Coordinate> {
    val elem = board[coord.i][coord.j]
    val visited = mutableSetOf<Coordinate>(coord)
    var i = coord.i + 1
    while (i < board.size && board[i][coord.j] == elem) {
      visited.add(Coordinate(i, coord.j))
      i++
    }
    if (visited.size < 3) {
      return mutableSetOf()
    }
    return visited
  }
}

private fun getRightCandies(coord: Coordinate, board: Array<IntArray>): MutableSet<Coordinate> {
  val elem = board[coord.i][coord.j]
  val visited = mutableSetOf<Coordinate>(coord)
  var j = coord.j + 1
  while (j < board[coord.i].size && board[coord.i][j] == elem) {
    visited.add(Coordinate(coord.i, j))
    j++
  }
  if (visited.size < 3) {
    return mutableSetOf()
  }
  return visited
}

data class Coordinate(val i: Int, val j: Int)

fun main(args: Array<String>) {
  println(Solution().candyCrush(
    arrayOf(
      intArrayOf(4,3,7,9),
      intArrayOf(4,4,4,5),
      intArrayOf(4,1,2,3),
      intArrayOf(1,2,2,2)
    )
  ))
}