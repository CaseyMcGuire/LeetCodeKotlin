package problems.problem0130

class Solution {
  fun solve(board: Array<CharArray>): Unit {
    val visitedCoordinates = mutableSetOf<Coordinate>()
    for (i in board.indices) {
      for (j in board[i].indices) {
        val curCoordinate = Coordinate(i, j)
        if (visitedCoordinates.contains(curCoordinate)) {
          continue
        }
        if (board[i][j] == 'O') {
          dfs(curCoordinate, board, visitedCoordinates)
        }
      }
    }
  }

  private fun dfs(coordinate: Coordinate, board: Array<CharArray>, visited: MutableSet<Coordinate>) {
    var meetsBorder = false
    val curVisited = mutableSetOf<Coordinate>()
    fun recurse(cur: Coordinate) {
      if (cur.i !in 0 until board.size || cur.j !in 0 until board[cur.i].size) {
        return
      }
      if (board[cur.i][cur.j] == 'X') {
        return
      }
      if (!curVisited.add(cur)) {
        return
      }
      if (cur.i == 0 || cur.j == 0 || cur.i == board.size - 1 || cur.j == board[cur.i].size - 1) {
        meetsBorder = true
      }
      cur.getNeighbors().forEach { recurse(it) }
    }
    recurse(coordinate)

    if (!meetsBorder) {
      for (c in curVisited) {
        board[c.i][c.j] = 'X'
      }
    }
    visited.addAll(curVisited)
  }
}

data class Coordinate(val i: Int, val j: Int) {
  fun getNeighbors(): List<Coordinate> {
    return listOf(
      Coordinate(this.i + 1, this.j),
      Coordinate(this.i - 1, this.j),
      Coordinate(this.i, this.j + 1),
      Coordinate(this.i, this.j - 1)
    )
  }
}