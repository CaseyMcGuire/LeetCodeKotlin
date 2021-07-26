package problems.problem0036

class Solution {
  fun isValidSudoku(board: Array<CharArray>): Boolean {
    return checkColumns(board) && checkRows(board) && checkSquares(board)
  }

  private fun checkRows(board: Array<CharArray>): Boolean {
    for (row in board) {
      val set = mutableSetOf<Char>()
      for (elem in row) {
        if (elem == '.') {
          continue
        }
        else if (!set.add(elem)) {
          return false
        }
      }
    }
    return true
  }

  private fun checkColumns(board: Array<CharArray>): Boolean {
    for (i in board.indices) {
      val set = mutableSetOf<Char>()
      for (j in board.indices) {
        val elem = board[j][i]
        if (elem == '.') {
          continue
        }
        else if (!set.add(elem)) {
          return false
        }
      }
    }
    return true
  }

  private fun checkSquares(board: Array<CharArray>): Boolean {
    for (i in 0..2) {
      for (j in 0..2) {
        val offsetRow = i * 3
        val offsetColumn = j * 3
        val set = mutableSetOf<Char>()
        for (k in 0..2) {
          for (h in 0..2) {
            val row = offsetRow + k
            val column = offsetColumn + h
            val elem = board[row][column]
            if (elem == '.') {
              continue
            }
            else if (!set.add(elem)) {
              return false
            }
          }
        }
      }
    }
    return true
  }


}

fun main(args: Array<String>) {
  println(Solution().isValidSudoku(
    arrayOf(
      charArrayOf('.', '.', '.', '.', '5', '.', '.', '1', '.'),
      charArrayOf('.', '4', '.', '3', '.', '.', '.', '.', '.'),
      charArrayOf( '.', '.', '.', '.', '.', '3', '.', '.', '.'),
      charArrayOf( '.', '.', '.', '.', '.', '.', '.', '.', '.'),
      charArrayOf( '.', '.', '.', '.', '.', '.', '.', '.', '.'),
      charArrayOf( '.', '.', '.', '.', '.', '.', '.', '.', '.'),
      charArrayOf( '.', '.', '.', '.', '.', '.', '.', '.', '.'),
      charArrayOf( '.', '.', '.', '.', '.', '.', '.', '.', '.'),
      charArrayOf( '.', '.', '.', '.', '.', '.', '.', '.', '.')
    )
  ))
}