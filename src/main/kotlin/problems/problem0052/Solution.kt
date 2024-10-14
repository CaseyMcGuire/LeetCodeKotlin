package problems.problem0052

class Solution {
  fun totalNQueens(n: Int): Int {
    val diagonals = mutableMapOf<Coordinate, Int>()
    val visitedRows = mutableSetOf<Int>()
    val visitedColumns = mutableSetOf<Int>()

    fun recurse(row: Int, column: Int, numQueens: Int): Int {
      if (numQueens == n) {
        return 1
      }
      var sum = 0
      for (i in row until n) {
        val start = if (i == row) column else 0
        for (j in start until n) {
          val c = Coordinate(i, j)
          if (visitedRows.contains(i) || visitedColumns.contains(j) || diagonals.containsKey(c)) {
            continue
          }
          visitedRows.add(i)
          visitedColumns.add(j)
          val diagonalsForCoordinate = c.getDiagonals(n)
          diagonalsForCoordinate.forEach { diagonals.increment(it) }
          sum += recurse(i, j, numQueens + 1)
          visitedRows.remove(i)
          visitedColumns.remove(j)
          diagonalsForCoordinate.forEach { diagonals.decrement(it) }
        }
      }
      return sum
    }
    return recurse(0, 0, 0)
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
}

data class Coordinate(val i: Int, val j: Int) {

  companion object {
    val DIRECTIONS = listOf(
      Coordinate(1, 1),
      Coordinate(-1, 1),
      Coordinate(1, -1),
      Coordinate(-1, -1)
    )
  }

  fun getDiagonals(n: Int): List<Coordinate> {
    val diagonals = mutableListOf<Coordinate>()
    for (c in Coordinate.DIRECTIONS) {
      var cur = Coordinate(i + c.i, j + c.j)
      while (cur.i in 0 until n && cur.j in 0 until n) {
        diagonals.add(cur)
        cur = Coordinate(cur.i + c.i, cur.j + c.j)
      }
    }
    return diagonals
  }
}