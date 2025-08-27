package problems.problem0529

class Solution {
  fun updateBoard(board: Array<CharArray>, click: IntArray): Array<CharArray> {

    fun traverse(coordinate: Coordinate) {
      if (!board.isUnrevealedEmptySquareAt(coordinate)) {
        return
      }
      val numAdjacentBombs = board.getNumAdjacentBombsAt(coordinate)
      if (numAdjacentBombs != 0) {
        board.setValueAt(coordinate, numAdjacentBombs.digitToChar())
      }
      else {
        board.setValueAt(coordinate, 'B')
        for (neighbor in coordinate.getNeighbors()) {
          traverse(neighbor)
        }
      }
    }

    val coordinate = Coordinate(click[0], click[1])
    if (board.getValueAt(coordinate) == 'M') {
      board.setValueAt(coordinate, 'X')
    }
    else if (board.isUnrevealedEmptySquareAt(coordinate)) {
      traverse(coordinate)
    }

    return board
  }

  private fun Array<CharArray>.getNumAdjacentBombsAt(c: Coordinate): Int {
    var numBombs = 0
    for (neighbor in c.getNeighbors()) {
      if (this.getValueAt(neighbor) == 'M') {
        numBombs++
      }
    }
    return numBombs
  }

  private fun Array<CharArray>.getValueAt(c: Coordinate): Char? {
    if (c.i !in this.indices || c.j !in this[c.i].indices) {
      return null
    }
    return this[c.i][c.j]
  }

  private fun Array<CharArray>.setValueAt(c: Coordinate, char: Char) {
    this[c.i][c.j] = char
  }

  private fun Array<CharArray>.isUnrevealedEmptySquareAt(c: Coordinate): Boolean {
    return this.getValueAt(c) == 'E'
  }
}

data class Coordinate(val i: Int, val j: Int) {
  fun getNeighbors(): List<Coordinate> {
    val neighbors = mutableListOf<Coordinate>()
    for (deltaI in -1..1) {
      for (deltaJ in -1..1) {
        if (deltaI == 0 && deltaJ == 0) {
          continue
        }
        neighbors.add(Coordinate(i + deltaI, j + deltaJ))
      }
    }
    return neighbors
  }
}