package problems.problem0350

class Solution {
  fun intersect(nums1: IntArray, nums2: IntArray): IntArray {
    val frequencyMap1 = mutableMapOf<Int, Int>()
    val frequencyMap2 = mutableMapOf<Int, Int>()

    for (i in nums1) {
      frequencyMap1.increment(i)
    }

    for (i in nums2) {
      frequencyMap2.increment(i)
    }

    val intersection = mutableListOf<Int>()

    for (entry in frequencyMap1.entries) {
      val key = entry.key
      val otherFrequency = frequencyMap2[key]
      if (otherFrequency == null) {
        continue
      }

      val smaller = if (otherFrequency > entry.value) entry.value else otherFrequency

      for (i in 0 until smaller) {
        intersection.add(key)
      }
    }
    return intersection.toIntArray()
  }

  private fun MutableMap<Int, Int>.increment(num: Int) {
    this.merge(num, 1) {cur, acc -> cur + acc}
  }
}