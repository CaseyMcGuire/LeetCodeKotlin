package problems.problem0351

class Solution {
  fun numberOfPatterns(m: Int, n: Int): Int {
    val visited = mutableSetOf<Coordinate>()
    var total = 0
    fun recurse(coordinate: Coordinate, length: Int) {
      if (length in m..n) {
        total++
      }
      if (length > n) {
        return
      }
      visited.add(coordinate)
      val nextCoordinates = getNextCoordinates(coordinate, visited)
      for (c in nextCoordinates) {
        recurse(c, length + 1)
      }
      visited.remove(coordinate)
    }
    for (i in 0 until 3) {
      for (j in 0 until 3) {
        recurse(Coordinate(i, j), 1)
      }
    }
    return total
  }

  private fun getNextCoordinates(coordinate: Coordinate, visited: MutableSet<Coordinate>): List<Coordinate> {
    val nextCoordinates = mutableListOf<Coordinate>()
    for (i in -1..1) {
      for (j in -1..1) {
        if (i == 0 && j == 0) {
          continue
        }
        val next = getCoordinateDirection(i, j, coordinate, visited)
        if (next != null) {
          nextCoordinates.add(next)
        }
      }
    }
    val knightJumps = listOf(
      Coordinate(1, 2),
      Coordinate(2, 1),
      Coordinate(-1, -2),
      Coordinate(-2, -1),
      Coordinate(1, -2),
      Coordinate(-1, 2),
      Coordinate(2, -1),
      Coordinate(-2, 1)
    )
    for (jump in knightJumps) {
      val next = getCoordinateDirection(jump.i, jump.j, coordinate, visited)
      if (next != null) {
        nextCoordinates.add(next)
      }
    }
    return nextCoordinates
  }

  private fun getCoordinateDirection(i: Int, j: Int, coordinate: Coordinate, visited: MutableSet<Coordinate>): Coordinate? {
    val range = 0 until 3
    var cur = Coordinate(coordinate.i + i, coordinate.j + j)
    while (visited.contains(cur)) {
      cur = Coordinate(cur.i + i, cur.j + j)
    }
    if (cur.i !in range || cur.j !in range || visited.contains(cur)) {
      return null
    }
    return cur
  }
}

data class Coordinate(val i: Int, val j: Int)