package problems.problem0373

import java.util.*

class Solution {
  fun kSmallestPairs(nums1: IntArray, nums2: IntArray, k: Int): List<List<Int>> {
    val pq = PriorityQueue<IndexPair>(compareBy({ nums1[it.first] + nums2[it.second] }))
    val min = Math.min(k, nums1.size)
    // think of each initial pair as the head of an ascending linked list
    for (i in 0 until min) {
      pq.add(IndexPair(i, 0))
    }
    val smallestPairs = mutableListOf<List<Int>>()
    while (pq.isNotEmpty() && smallestPairs.size < k) {
      val smallest = pq.poll()
      smallestPairs.add(listOf(nums1[smallest.first], nums2[smallest.second]))
      val newSecond = smallest.second + 1
      if (newSecond < nums2.size) {
        pq.add(IndexPair(smallest.first, newSecond))
      }
    }
    return smallestPairs
  }
}

data class IndexPair(val first: Int, val second: Int)