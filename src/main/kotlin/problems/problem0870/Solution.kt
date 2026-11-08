package problems.problem0870

import java.util.PriorityQueue
import java.util.TreeMap

class Solution {
  fun advantageCount(nums1: IntArray, nums2: IntArray): IntArray {
    val numsToFrequency = TreeMap<Int, Int>()
    for (num in nums1) {
      numsToFrequency.increment(num)
    }

    val pq = PriorityQueue<NumAndIndex>(compareBy({ it.num }, { it.index }))
    for (i in nums2.indices) {
      pq.add(NumAndIndex(nums2[i], i))
    }

    val permutation = IntArray(nums2.size) { 0 }
    while (pq.isNotEmpty()) {
      val next = pq.poll()
      val higher = numsToFrequency.higherEntry(next.num)
      if (higher == null) {
        val first = numsToFrequency.firstEntry()
        permutation[next.index] = first.key
        numsToFrequency.decrement(first.key)
      }
      else {
        permutation[next.index] = higher.key
        numsToFrequency.decrement(higher.key)
      }
    }

    return permutation
  }

  private fun TreeMap<Int, Int>.increment(num: Int) {
    this.merge(num, 1) { cur, acc -> cur + acc }
  }

  private fun TreeMap<Int, Int>.decrement(num: Int) {
    val frequency = this[num] ?: error("No entry for num: $num")
    if (frequency == 1) {
      this.remove(num)
    }
    else {
      this[num] = frequency - 1
    }
  }

  data class NumAndIndex(val num: Int, val index: Int)
}