package problems.problem0347

import java.util.*

class Solution {
  fun topKFrequent(nums: IntArray, k: Int): IntArray {
    val frequencyMap = mutableMapOf<Int, Int>()
    for (num in nums) {
      frequencyMap.merge(num, 1) {cur, acc -> cur + acc}
    }

    val pq = PriorityQueue<NumAndFrequency>(compareByDescending({it.frequency}))
    for (entry in frequencyMap.entries) {
      pq.offer(NumAndFrequency(entry.key, entry.value))
    }

    val kMostFrequent = mutableListOf<Int>()
    for (i in 0 until k) {
      kMostFrequent.add(pq.poll().num)
    }
    return kMostFrequent.toIntArray()
  }
}

data class NumAndFrequency(val num: Int, val frequency: Int)