package problems.problem1679

class Solution {
  fun maxOperations(nums: IntArray, k: Int): Int {
    val numToIndices = mutableMapOf<Int, MutableSet<Int>>()
    for (i in nums.indices) {
      val num = nums[i]
      val indices = numToIndices.getOrPut(num) { mutableSetOf() }
      indices.add(i)
    }

    var numOperations = 0
    for (entry in numToIndices.entries) {
      val curNum = entry.key
      val curIndices = entry.value
      val other = k - curNum
      if (other <= 0) {
        continue
      }
      else if (other == curNum) {
        numOperations += curIndices.size / 2
      }
      else {
        val otherIndices = numToIndices[other] ?: mutableSetOf()
        numOperations += Math.min(otherIndices.size, curIndices.size)
        otherIndices.clear()
        curIndices.clear()
      }

    }
    return numOperations
  }
}