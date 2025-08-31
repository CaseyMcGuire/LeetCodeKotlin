package problems.problem0918

import java.util.TreeMap

class Solution {
  fun maxSubarraySumCircular(nums: IntArray): Int {
    if (nums.isEmpty()) {
      return 0
    }
    val prefixSum = IntArray(nums.size)
    val leftPrefixSum = TreeMap<Int, Int>()
    val rightPrefixSum = TreeMap<Int, Int>()

    for (i in nums.indices) {
      val curSum = if (i == 0) {
        nums[i]
      }
      else {
        nums[i] + prefixSum[i - 1]
      }
      prefixSum[i] = curSum
      rightPrefixSum.increment(curSum)
    }

    var largestSoFar = nums.first()
    for (i in nums.indices) {
      val largestRight = rightPrefixSum.lastKey()
      val rightSum = largestRight!! - (prefixSum.getOrNull(i - 1) ?: 0)

      val sumToEnd = prefixSum.last() - (prefixSum.getOrNull(i - 1) ?: 0)
      val largestLeftKey = leftPrefixSum.lastEntry()?.key ?: 0
      val largestWrappedSum = sumToEnd + largestLeftKey
      val largerSum = Math.max(rightSum, largestWrappedSum)
      largestSoFar = Math.max(largerSum, largestSoFar)

      rightPrefixSum.decrement(prefixSum[i])
      leftPrefixSum.increment(prefixSum[i])
    }
    return largestSoFar
  }

  private fun TreeMap<Int, Int>.increment(num: Int) {
    this.merge(num, 1) { cur, acc -> cur + acc }
  }

  private fun TreeMap<Int, Int>.decrement(num: Int) {
    val frequency = this[num]
      ?: return

    if (frequency == 1) {
      this.remove(num)
    }
    else {
      this[num] = frequency - 1
    }
  }
}