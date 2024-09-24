package problems.problem0051

class Solution {
  fun solveNQueens(n: Int): List<List<String>> {
    val rows = mutableSetOf<Int>()
    val columns = mutableSetOf<Int>()
    val validQueens = mutableListOf<List<Coordinate>>()
    val queens = mutableListOf<Coordinate>()
    val diagonals = mutableMapOf<Coordinate, Int>()

    fun backtrack(i: Int, j: Int) {

      if (queens.size == n) {
        validQueens.add(queens.toList())
        return
      }
      else if (i == n) {
        return
      }
      else if (j == n) {
        backtrack(i + 1, 0)
        return
      }
      val next = Coordinate(i, j)
      if (!rows.contains(next.i) &&
        !columns.contains(next.j) &&
        !diagonals.containsKey(next)) {
        rows.add(next.i)
        columns.add(next.j)
        val diagonal = getDiagonals(next.i, next.j, n)
        diagonals.incrementAll(diagonal)
        queens.add(next)
        backtrack(i, j + 1)
        queens.removeLast()
        rows.remove(next.i)
        columns.remove(next.j)
        diagonals.decrementAll(diagonal)
      }
      backtrack(i, j + 1)
    }
    backtrack(0, 0)

    val boards = mutableListOf<List<String>>()

    for (validQueen in validQueens) {
      val board = MutableList(n) { MutableList(n) { "." } }
      for (c in validQueen) {
        board[c.i][c.j] = "Q"
      }
      boards.add(board.map { it.joinToString("") })
    }

    return boards
  }

  fun getDiagonals(i: Int, j: Int, n: Int): Set<Coordinate> {
    val set = mutableSetOf<Coordinate>()

    fun recurse(ii: Int, jj: Int, iter: Int) {
      val rowIndex = (ii * iter) + i
      val columnIndex = (jj * iter) + j
      if (rowIndex !in 0 until n || columnIndex !in 0 until n) {
        return
      }
      set.add(Coordinate(rowIndex, columnIndex))
      recurse(ii, jj, iter + 1)
    }

    for (k in -1..1 step 2) {
      for (h in -1..1 step 2) {
        recurse(k, h, 0)
      }
    }

    return set
  }

  fun MutableMap<Coordinate, Int>.increment(c: Coordinate) {
    this.merge(c, 1) { cur, acc -> cur + acc }
  }

  fun MutableMap<Coordinate, Int>.decrement(c: Coordinate) {
    val frequency = this[c] ?: return
    if (frequency == 1) {
      this.remove(c)
    }
    else {
      this[c] = frequency - 1
    }
  }

  fun MutableMap<Coordinate, Int>.decrementAll(coordinates: Set<Coordinate>) {
    coordinates.forEach { this.decrement(it) }
  }

  fun MutableMap<Coordinate, Int>.incrementAll(coordinates: Set<Coordinate>) {
    coordinates.forEach { this.increment(it) }
  }
}

data class Coordinate(val i: Int, val j: Int)