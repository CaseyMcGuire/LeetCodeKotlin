package problems.problem0322

class Solution {
  fun coinChange(coins: IntArray, amount: Int): Int {
    val amountToMinCoins = mutableMapOf<Int, Int>()

    fun recurse(curAmount: Int): Int {
      if (curAmount < 0) {
        return -1
      }
      else if (curAmount == 0) {
        return 0
      }

      val cached = amountToMinCoins[curAmount]
      if (cached != null) {
        return cached
      }

      var min = -1
      for (coin in coins) {
        val minCoins = recurse(curAmount - coin)
        if (min == -1) {
          if (minCoins != -1) {
            min = minCoins + 1
          }
        }
        else {
          if (minCoins != -1) {
            min = Math.min(min, minCoins + 1)
          }
        }
      }

      amountToMinCoins[curAmount] = min
      return min
    }

    return recurse(amount)
  }
}