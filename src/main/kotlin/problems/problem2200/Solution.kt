package problems.problem2200

class Solution {
  fun findKDistantIndices(nums: IntArray, key: Int, k: Int): List<Int> {
    val kDistantIndices = mutableSetOf<Int>()

    for (i in nums.indices) {
      val curNum = nums[i]
      if (curNum == key) {
        for (j in i downTo i - k) {
          if (j < 0 || kDistantIndices.contains(j)) {
            break
          }
          kDistantIndices.add(j)
        }

        for (j in i..i + k) {
          if (j >= nums.size) {
            break
          }
          kDistantIndices.add(j)
        }

      }
    }
    return kDistantIndices.toList().sorted()
  }

}