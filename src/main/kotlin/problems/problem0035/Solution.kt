package problems.problem0035

class Solution {
  fun searchInsert(nums: IntArray, target: Int): Int {
    var low = 0
    var high = nums.size - 1

    while (low <= high) {
      val mid = ((low.toLong() + high.toLong()) / 2L).toInt()
      val elem = nums[mid]
      if (elem == target) {
        return mid
      }
      else if (target > elem) {
        low = mid + 1
      }
      else{
        high = mid - 1
      }

    }
    return low
  }
}