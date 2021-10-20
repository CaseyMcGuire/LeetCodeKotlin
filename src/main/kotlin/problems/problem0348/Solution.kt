package problems.problem0348

class TicTacToe(val n: Int) {

  private val board = Array(n) { IntArray(n) }

  fun move(row: Int, col: Int, player: Int): Int {
    board[row][col] = player

    var isWinner = true
    // check row
    for (column in board[row]) {
      if (column != player) {
        isWinner = false
        break
      }
    }

    if (isWinner) {
      return player
    }

    isWinner = true
    // check column
    for (row in board) {
      if (row[col] != player) {
        isWinner = false
        break
      }
    }

    if (isWinner) {
      return player
    }


    isWinner = true
    var left = Coordinate(row - 1, col - 1)
    var right = Coordinate(row + 1, col + 1)

    if (col == row && checkDownDiagonal(player)) {
      return player
    }

    if (row + col == n - 1 && checkUpDiagonal(player)) {
      return player
    }
    return 0
  }

  fun checkDownDiagonal(player: Int): Boolean {
    var coord = Coordinate(0, 0)
    while (coord.isValid(n)) {
      if (board[coord.row][coord.col] != player) {
        return false
      }
      coord = Coordinate(coord.row + 1, coord.col + 1)
    }
    return true
  }

  fun checkUpDiagonal(player: Int): Boolean {
    var coord = Coordinate(n - 1, 0)
    while (coord.isValid(n)) {
      if (board[coord.row][coord.col] != player) {
        return false
      }
      coord = Coordinate(coord.row - 1, coord.col + 1)
    }
    return true
  }
}

data class Coordinate(val row: Int, val col: Int) {
  fun isValid(n: Int): Boolean {
    return row in 0 until n && col in 0 until n
  }

}