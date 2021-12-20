package problems.problem0688

class Solution {
  // generate all positions and do (num possible places) /
  fun knightProbability(n: Int, k: Int, row: Int, column: Int): Double {
    val curCoordinate = Coordinate(row, column)
    val cache = mutableMapOf<Cache, Double>()
    fun recurse(cur: Coordinate, curK: Int): Double {
      val cachedValue = cache[Cache(cur, curK)]
      if (cachedValue != null) {
        return cachedValue
      }
      if (curK > k) {
        return 0.0
      }
      if (cur.i !in 0 until n || cur.j !in 0 until n) {
        return 0.0
      }
      if (curK == k) {
        return 1.0
      }
      var rate = 0.0
      val moves = getMoves(cur)
      for (move in moves) {
        rate = rate + (1.0 / 8.0) * recurse(move, curK + 1)
      }
      cache[Cache(cur, curK)] = rate
      return rate
    }
    return recurse(curCoordinate, 0)
  }

  fun getMoves(cur: Coordinate): List<Coordinate> {
    val i = cur.i
    val j = cur.j
    return listOf(
      Coordinate(i - 2, j - 1),
      Coordinate(i - 1, j - 2),
      Coordinate(i + 1, j - 2),
      Coordinate(i + 2, j - 1),
      Coordinate(i - 2, j + 1),
      Coordinate(i - 1, j + 2),
      Coordinate(i + 2, j + 1),
      Coordinate(i + 1, j + 2)
    )
  }
}

data class Coordinate(val i: Int, val j: Int)
data class Cache(val c: Coordinate, val k: Int)