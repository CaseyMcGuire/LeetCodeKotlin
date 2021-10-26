package problems.problem0289

class Solution {
  fun gameOfLife(board: Array<IntArray>): Unit {
    if (board.isEmpty() || board[0].isEmpty()) {
      return
    }
    val newBoard = Array(board.size) { IntArray(board[0].size) }
    for (i in board.indices) {
      for (j in board[i].indices) {
        val numNeighbors = board.getLiveNeighbors(i, j)
        if (board[i][j] == 1 && numNeighbors in 2..3) {
          newBoard[i][j] = 1
        }
        else if (board[i][j] == 0 && numNeighbors == 3) {
          newBoard[i][j] = 1
        }
      }
    }

    for (i in board.indices) {
      for (j in board[i].indices) {
        board[i][j] = newBoard[i][j]
      }
    }
  }

  private fun Array<IntArray>.getLiveNeighbors(i: Int, j: Int): Int {
    var numNeighbors = 0
    for (k in -1..1) {
      for (l in -1..1) {
        if (k == 0 && l == 0) {
          continue
        }
        val newI = i + k
        val newJ = j + l
        if (!(newI in 0 until this.size) || !(newJ in 0 until this[0].size)) {
          continue
        }
        if (this[newI][newJ] == 1) {
          numNeighbors++
        }
      }
    }
    return numNeighbors
  }
}