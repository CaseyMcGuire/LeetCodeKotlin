package problems.problem0238

import java.util.*

class Solution {
  fun productExceptSelf(nums: IntArray): IntArray {
    val backStack = ArrayDeque<Int>()
    val frontStack = ArrayDeque<Int>()
    for (i in nums.size - 1 downTo 1) {
      val previous = if (backStack.isNotEmpty()) backStack.peek() else 1
      val currentNum = previous * nums[i]
      backStack.push(currentNum)
    }
    val products = mutableListOf<Int>()
    for (i in nums.indices) {
      val frontNum = if (frontStack.isNotEmpty()) frontStack.peek() else 1
      val backNum = if (backStack.isNotEmpty()) backStack.peek() else 1
      products.add(frontNum * backNum)
      frontStack.push(nums[i] * frontNum)
      if (backStack.isNotEmpty()) {
        backStack.pop()
      }
    }
    return products.toIntArray()
  }
}