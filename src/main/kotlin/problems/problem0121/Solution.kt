package problems.problem0121

class Solution {
  fun maxProfit(prices: IntArray): Int {
    if (prices.isEmpty() || prices.size == 1) {
      return 0
    }
    var maxPriceSoFar = prices[prices.size - 1]
    var maxProfitSoFar = 0
    for (i in prices.size - 2 downTo 0) {
      val maxProfit = maxPriceSoFar - prices[i]
      if (maxProfitSoFar < maxProfit) {
        maxProfitSoFar = maxProfit
      }
      if (prices[i] > maxPriceSoFar) {
        maxPriceSoFar = prices[i]
      }
    }
    return maxProfitSoFar
  }
}