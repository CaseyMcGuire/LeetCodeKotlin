package problems.problem0062

class Solution {
  fun uniquePaths(m: Int, n: Int): Int {
    val cache = mutableMapOf<Coordinate, Int>()

    fun dfs(coord: Coordinate) {
      // base case
      if (coord == Coordinate(m - 1, n - 1)) {
        cache[Coordinate(m - 1, n - 1)] = 1
        return
      }

      // outside bounds of board
      if (coord.i == m || coord.j == n) {
        return
      }

      // already visited
      if (cache[coord] != null) {
        return
      }
      val right = Coordinate(coord.i + 1, coord.j)
      val left = Coordinate(coord.i, coord.j + 1)
      dfs(right)
      dfs(left)
      val rightVal = cache[right] ?: 0
      val leftVal = cache[left] ?: 0
      cache[coord] = rightVal + leftVal
    }

    val start = Coordinate(0, 0)
    dfs(start)

    return cache[start]!!
  }
}

data class Coordinate(val i: Int, val j: Int)