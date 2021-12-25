package problems

import java.math.BigInteger

class Solution {
  fun knightDialer(n: Int): Int {
    val cache = mutableMapOf<Cache, BigInteger>()
    fun recurse(cur: Int, curCoordinate: Coordinate): BigInteger {
      if (cur == n - 1) {
        return BigInteger("1")
      }
      val cachedKey = Cache(curCoordinate, cur)
      val cachedValue = cache[cachedKey]
      if (cachedValue != null) {
        return cachedValue
      }

      val moves = getKnightMoves(curCoordinate).filter { isOnKeypad(it) }
      var sum = BigInteger("0")
      for (move in moves) {
        sum = sum.add(recurse(cur + 1, move))
      }
      cache[cachedKey] = sum
      return sum
    }

    var totalSum = BigInteger("0")
    for (i in 0 until 4) {
      for (j in 0 until 3) {
        if (i == 3 && j != 1) {
          continue
        }
        totalSum = totalSum.add(recurse(0, Coordinate(i, j)))
      }
    }

    val modulo = Math.pow(10.0, 9.0).toInt() + 7
    return totalSum.mod(BigInteger(modulo.toString())).toInt()
  }

  private fun isOnKeypad(coordinate: Coordinate): Boolean {
    val i = coordinate.i
    val j = coordinate.j
    if (i == 3) {
      return j == 1
    }
    else {
      val range = 0 until 3
      return i in range && j in range
    }
  }

  private fun getKnightMoves(coordinate: Coordinate): List<Coordinate> {
    val i = coordinate.i
    val j = coordinate.j
    return listOf(
      Coordinate(i - 2, j - 1),
      Coordinate(i - 1, j - 2),
      Coordinate(i + 1, j - 2),
      Coordinate(i + 2, j - 1),
      Coordinate(i + 2, j + 1),
      Coordinate(i + 1, j + 2),
      Coordinate(i - 1, j + 2),
      Coordinate(i - 2, j + 1)
    )
  }
}

data class Coordinate(val i: Int, val j: Int)
data class Cache(val c: Coordinate, val jumps: Int)