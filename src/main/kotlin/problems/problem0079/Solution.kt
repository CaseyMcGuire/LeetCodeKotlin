package problems.problem0079

class Solution {
  fun exist(board: Array<CharArray>, word: String): Boolean {
    val firstLetter = word[0]
    val visited = mutableSetOf<Coordinate>()
    fun findWord(index: Int, coordinate: Coordinate): Boolean {
      if (visited.contains(coordinate)) {
        return false
      }
      if (board[coordinate.i][coordinate.j] != word[index]) {
        return false
      }
      if (index == word.length - 1) {
        return true
      }
      visited.add(coordinate)
      val neighbors = getNeighboringCoordinates(coordinate, board.size, board[coordinate.i].size)
      var found = false
      for (neighbor in neighbors ) {
        val foundWord = findWord(index + 1, neighbor)
        if (foundWord) {
          found = true
          break
        }
      }
      visited.remove(coordinate)
      return found
    }

    for (i in board.indices) {
      for (j in board[i].indices) {
        val foundWord = findWord(0, Coordinate(i, j))
        if (foundWord) {
          return true
        }
      }
    }
    return false
  }

  private fun getNeighboringCoordinates(coordinate: Coordinate, maxI: Int, maxJ: Int): List<Coordinate> {
    val i = coordinate.i
    val j = coordinate.j
    val possibleCoordinates = mutableListOf<Coordinate>(
      Coordinate(i + 1, j),
      Coordinate(i - 1, j),
      Coordinate(i, j + 1),
      Coordinate(i, j - 1)
    )

    return possibleCoordinates.filter { it.i in 0 until maxI && it.j in 0 until maxJ }
  }
}


data class Coordinate(val i: Int, val j: Int)