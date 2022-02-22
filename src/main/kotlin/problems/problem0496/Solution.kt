package problems.problem0496

import java.util.*

class Solution {
  fun nextGreaterElement(nums1: IntArray, nums2: IntArray): IntArray {
    val stack = ArrayDeque<Int>()
    val numToIndex = mutableMapOf<Int, Int>()
    for (i in nums1.indices) {
      numToIndex[nums1[i]] = i
    }

    val arr = IntArray(nums1.size)

    for (i in nums2.indices.reversed()) {
      val elem = nums2[i]
      while (stack.isNotEmpty() && stack.peek() <= elem) {
        stack.pop()
      }
      val largerElement = if (stack.isEmpty()) -1 else stack.peek()
      val index = numToIndex[elem]
      if (index != null) {
        arr[index] = largerElement
      }
      stack.push(elem)
    }

    return arr
  }
}