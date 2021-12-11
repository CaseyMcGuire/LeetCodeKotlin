package problems.problem1025

class Solution {
  fun divisorGame(n: Int): Boolean {
    val cache = mutableMapOf<GameState, Boolean>()

    fun canAliceWin(cur: Int, isAlice: Boolean): Boolean {
      val divisors = getDivisors(cur)
      // if there are no moves left, Alice wins if it's Bob's turn
      val state = GameState(cur, isAlice)
      val cached = cache[state]
      if (cached != null) {
        return cached
      }


      if (divisors.isEmpty()) {
        val value = if (isAlice) {
          false
        }
        else {
          true
        }
        cache[state] = value
        return value
      }

      for (divisor in divisors) {
        val aliceWins = canAliceWin(cur - divisor, !isAlice)
        val isBob = !isAlice
        if (isBob) {
          if (!aliceWins) {
            cache[state] = false
            return false
          }
        }
        else {
          if (aliceWins) {
            cache[state] = true
            return true
          }
        }
      }
      val value = if (isAlice) false else true
      cache[state] = value
      return value
    }

    return canAliceWin(n, true)
  }

  private fun getDivisors(n: Int): List<Int> {
    if (n <= 0) {
      return emptyList()
    }
    val divisors = mutableListOf<Int>()
    for (i in 1 until n) {
      if (n % i == 0) {
        divisors.add(i)
      }
    }
    return divisors
  }
}

data class GameState(val n: Int, val isAlice: Boolean)