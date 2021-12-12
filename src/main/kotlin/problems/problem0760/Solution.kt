package problems.problem0760

import java.util.*

class Solution {
  fun anagramMappings(nums1: IntArray, nums2: IntArray): IntArray {
    val numToIndices = mutableMapOf<Int, LinkedList<Int>>()
    for (i in nums2.indices) {
      val num = nums2[i]
      val set = numToIndices[num] ?: LinkedList()
      set.add(i)
      numToIndices[num] = set
    }

    val mapping = IntArray(nums1.size)
    for (i in nums1.indices) {
      val num = nums1[i]
      val indices = numToIndices[num]!!
      val currentIndex = indices.removeLast()
      mapping[i] = currentIndex
    }
    return mapping
  }
}