package problems.problem0033

class Solution {
  fun search(nums: IntArray, target: Int): Int {
    var high = nums.size - 1
    var low = 0
    while (high >= low) {
      val mid = (high + low) / 2
      if (nums[mid] == target) {
        return mid
      }
      else if (nums[mid] < nums[high]) {
        if (target in nums[mid]..nums[high]) {
          low = mid + 1
        }
        else {
          high = mid - 1
        }
      }
      else {
        if (target in nums[low]..nums[mid]) {
          high = mid - 1
        }
        else {
          low = mid + 1
        }
      }
    }
    return -1
  }
}
