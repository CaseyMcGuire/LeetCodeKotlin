package problems.problem0220

import java.util.*

class Solution {
  fun containsNearbyAlmostDuplicate(nums: IntArray, k: Int, t: Int): Boolean {
    return containsNearbyAlmostDuplicateImpl(nums.map {it.toLong()}.toLongArray(), k.toLong(), t.toLong())
  }

  fun containsNearbyAlmostDuplicateImpl(nums: LongArray, k: Long, t: Long): Boolean {
    val window = TreeSet<Long>()
    for (i in 0 until nums.size) {
      if (i >= nums.size) {
        return false
      }

      val num = t + nums[i]
      val smallerNum = window.floor(num)
      val other = nums[i] - t
      if (smallerNum != null && smallerNum >= other) {
        return true
      }
      window.add(nums[i])
      if (i >= k) {
        window.remove(nums[i - k.toInt()])
      }
    }
    return false
  }
}

fun main(args: Array<String>) {
  println(Solution().containsNearbyAlmostDuplicate(intArrayOf(1,5,9,1,5,9), 2, 3))
}