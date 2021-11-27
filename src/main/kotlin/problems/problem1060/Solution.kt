package problems.problem1060

class Solution {
  fun missingElement(nums: IntArray, k: Int): Int {
    val gaps = mutableListOf<Int>()
    for (i in nums.indices) {
      if (i == 0) {
        gaps.add(0)
      }
      else {
        gaps.add(nums[i] - nums[i - 1] + gaps[i - 1] - 1)
      }
    }
    if (gaps[gaps.size - 1] < k) {
      return nums[nums.size - 1] + k - gaps[gaps.size - 1]
    }

    var low = 0
    var high = gaps.size - 1
    while (low <= high) {
      val mid = ((low.toLong() + high.toLong()) / 2L).toInt()
      val found = k in gaps[mid]..gaps[mid + 1]
      val difference = high - low
      if (difference < 10) {
        break
      }

      if (found) {
        return nums[mid] + k - gaps[mid]
      }
      if (k in gaps[mid]..gaps[high]) {
        low = mid + 1
      }
      else {
        high = mid - 1
      }
    }
    for (i in low..high) {
      if (k in gaps[i]..gaps[i + 1]) {
        return nums[i] + k - gaps[i]
      }
    }
    return -1
  }
}