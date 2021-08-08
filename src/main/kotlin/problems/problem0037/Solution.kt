package problems.problem0037

class Solution {

  fun solveSudoku(board: Array<CharArray>): Unit {
    val possibleRowValues = getPossibleValuesForRow(board)
    val possibleColumnValues = getPossibleValuesForColumns(board)
    val possibleSquareValues = getPossibleValuesForSquares(board)
    fun recurse(board: Array<CharArray>, i: Int, j: Int): Boolean {
      val squareCoordinate = (i / 3) * 3 + (j / 3)
      val isBlank = board[i][j] == '.'
      if (!isBlank) {
        if (i == 8 && j == 8) {
          return true
        }
        val (nextRow, nextColumn) = getNextCoordinate(i, j)
        return recurse(board, nextRow, nextColumn)
      }
      val possibleValuesForRow = possibleRowValues[i]
      val possibleValuesForColumn = possibleColumnValues[j]
      val possibleValuesForSquare = possibleSquareValues[squareCoordinate]
      val possibleValues = possibleValuesForRow.intersect(possibleValuesForColumn).intersect(possibleValuesForSquare)
      if (possibleValues.isEmpty()) {
        return false
      }
      for (possibleValue in possibleValues) {
        board[i][j] = possibleValue
        if (i == 8 && j == 8) {
          return true
        }
        val removedFromRows = possibleValuesForRow.remove(possibleValue)
        val removedFromColumns = possibleValuesForColumn.remove(possibleValue)
        val removedFromSquare = possibleValuesForSquare.remove(possibleValue)
        val (nextRow, nextColumn) = getNextCoordinate(i, j)
        val isSolved = recurse(board, nextRow, nextColumn)
        if (isSolved) {
          return true
        }
        board[i][j] = '.'
        if (removedFromRows) {
          possibleValuesForRow.add(possibleValue)
        }
        if (removedFromColumns) {
          possibleValuesForColumn.add(possibleValue)
        }
        if (removedFromSquare) {
          possibleValuesForSquare.add(possibleValue)
        }
      }
      return false
    }
    recurse(board, 0, 0)
    println("laskdjf")
  }



  private fun getNextCoordinate(row: Int, column: Int): Pair<Int, Int> {
    if (column == 8) {
      return Pair(row + 1, 0)
    }
    return Pair(row, column + 1)
  }

  private fun getPossibleValuesForRow(board: Array<CharArray>): List<MutableSet<Char>> {
    val possibleRowValues = mutableListOf<MutableSet<Char>>()
    for (row in board) {
      val valuesForRow = HashSet(POSSIBLE_VALUES)
      for (value in row) {
        valuesForRow.remove(value)
      }
      possibleRowValues.add(valuesForRow)
    }
    return possibleRowValues
  }

  private fun getPossibleValuesForColumns(board: Array<CharArray>): List<MutableSet<Char>> {
    val possibleColumnValues = mutableListOf<MutableSet<Char>>()
    for (i in board.indices) {
      val valuesForColumn = HashSet(POSSIBLE_VALUES)
      for (j in board[i].indices) {
        valuesForColumn.remove(board[j][i])
      }
      possibleColumnValues.add(valuesForColumn)
    }
    return possibleColumnValues
  }

  private fun getPossibleValuesForSquares(board: Array<CharArray>): List<MutableSet<Char>> {
    val possibleValuesForSquares = mutableListOf<MutableSet<Char>>()
    for (i in 0 until 3) {
      for (j in 0 until 3) {
        possibleValuesForSquares.add(
          getPossibleValuesForSquare(board, i * 3, j * 3)
        )
      }
    }
    return possibleValuesForSquares
  }

  private fun getPossibleValuesForSquare(board: Array<CharArray>, startRow: Int, startColumn: Int): MutableSet<Char> {
    val possibleValuesForSquares = HashSet(POSSIBLE_VALUES)
    for (i in startRow until 3 + startRow) {
      for (j in startColumn until 3 + startColumn) {
        possibleValuesForSquares.remove(board[i][j])
      }
    }
    return possibleValuesForSquares
  }

  companion object {
    val POSSIBLE_VALUES = listOf('1', '2', '3', '4', '5', '6', '7', '8', '9')
  }
}

fun main(args: Array<String>) {
  println(Solution().solveSudoku(
    arrayOf(
      charArrayOf('5','3','.','.','7','.','.','.','.'),
      charArrayOf('6','.','.','1','9','5','.','.','.'),
      charArrayOf('.','9','8','.','.','.','.','6','.'),
      charArrayOf('8','.','.','.','6','.','.','.','3'),
      charArrayOf('4','.','.','8','.','3','.','.','1'),
      charArrayOf('7','.','.','.','2','.','.','.','6'),
      charArrayOf('.','6','.','.','.','.','2','8','.'),
      charArrayOf('.','.','.','4','1','9','.','.','5'),
      charArrayOf('.','.','.','.','8','.','.','7','9')
    )
  ))
}