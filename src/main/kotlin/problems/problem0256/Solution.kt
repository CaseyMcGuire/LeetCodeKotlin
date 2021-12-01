package problems.problem0256

class Solution {
  fun minCost(costs: Array<IntArray>): Int {

    val minCostAtIndex = mutableMapOf<MinCost, Int>()

    fun recurse(index: Int, previousColor: Int): Int {
      if (index == costs.size) {
        return 0
      }
      val possibleColorIndices = costs[index].indices.filter { it != previousColor }
      var min: Int? = null
      for (color in possibleColorIndices) {
        val cost = MinCost(index, color)
        val value = minCostAtIndex[cost] ?: recurse(index + 1, color) + costs[index][color]
        minCostAtIndex[cost] = value
        if (min == null || min > value) {
          min = value
        }
      }
      return min!!
    }

    val minimumCost = listOf(0,1,2).map { recurse(0, -1) }.min()!!
    return minimumCost
  }
}

data class MinCost(val index: Int, val color: Int)