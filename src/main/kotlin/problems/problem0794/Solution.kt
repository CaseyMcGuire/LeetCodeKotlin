package problems.problem0794

class Solution {
  fun validTicTacToe(board: Array<String>): Boolean {
    val xCoords = getCoordinatesForSign(board, 'X')
    val oCoords = getCoordinatesForSign(board, 'O')
    var numX = 0
    var numO = 0

    if (xCoords.size - oCoords.size !in 0..1) {
      return false
    }

    val xRowWins = xCoords.groupBy { it.i }.filter { it.value.size == 3 }
    val oRowWins = oCoords.groupBy { it.i }.filter { it.value.size == 3 }
    val xColWins = xCoords.groupBy { it.j }.filter { it.value.size == 3 }
    val oColWins = oCoords.groupBy { it.j }.filter { it.value.size == 3 }


    val numXWins = xRowWins.size + xColWins.size + getNumWinningDiagonals(board, 'X')
    val numOWins = oRowWins.size + oColWins.size + getNumWinningDiagonals(board, 'O')
    if (numXWins > 0 && numOWins > 0) {
      return false
    }
    if (numXWins > 0) {
      return oCoords.size == xCoords.size - 1
    }
    else if (numOWins > 0) {
      return oCoords.size == xCoords.size
    }

    return true
  }

  private fun getCoordinatesForSign(board: Array<String>, sign: Char): List<Coordinate> {
    val coords = mutableListOf<Coordinate>()
    for (i in board.indices) {
      for (j in board[i].indices) {
        if (board[i][j] == sign) {
          coords.add(Coordinate(i, j))
        }
      }
    }
    return coords
  }
}

private fun getNumWinningDiagonals(board: Array<String>, sign: Char): Int {
  val leftToRightDiagonal = mutableListOf<Coordinate>(
    Coordinate(0, 0),
    Coordinate(1, 1),
    Coordinate(2, 2)
  )

  val rightToLeftDiagonal = mutableListOf<Coordinate>(
    Coordinate(0, 2),
    Coordinate(1 ,1),
    Coordinate(2, 0)
  )

  var numLeft = 0
  var numRight = 0
  for (i in leftToRightDiagonal.indices) {
    val leftCoordinate = leftToRightDiagonal[i]
    numLeft += if (board[leftCoordinate.i][leftCoordinate.j] == sign) 1 else 0
    val rightCoordinate = rightToLeftDiagonal[i]
    numRight += if (board[rightCoordinate.i][rightCoordinate.j] == sign) 1 else 0
  }

  return (numLeft / 3) + (numRight / 3)
}

// OXX
// XOX
// OXO

data class Coordinate(val i: Int, val j: Int)