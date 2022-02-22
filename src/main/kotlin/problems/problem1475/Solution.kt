package problems.problem1475

import java.util.*

class Solution {
  fun finalPrices(prices: IntArray): IntArray {
    val discount = IntArray(prices.size)
    val priceToIndices = TreeMap<Int, MutableSet<Int>>()
    for (i in prices.indices) {
      discount[i] = prices[i]
      val smallerPrices = priceToIndices.tailMap(prices[i], true)
      val nums = mutableListOf<Int>()
      for (entry in smallerPrices.entries) {
        nums.add(entry.key)
        for (index in entry.value) {
          discount[index] = discount[index] - prices[i]
        }
      }

      for (num in nums) {
        priceToIndices.remove(num)
      }

      priceToIndices.addToSet(prices[i], i)
    }

    return discount
  }

  private fun TreeMap<Int, MutableSet<Int>>.addToSet(key: Int, value: Int) {
    val existingSet = this[key] ?: mutableSetOf()
    existingSet.add(value)
    this[key] = existingSet
  }
}