package problems.problem0081

class Solution {
  fun search(nums: IntArray, target: Int): Boolean {
    return search(nums, target, 0, nums.size - 1)
  }

  private fun search(nums: IntArray, target: Int, start: Int, end: Int): Boolean {
    var low = start
    var high = end

    while (low < high) {
      val middle = ((low.toLong() + high.toLong()) / 2L).toInt()

      if (nums[middle] == target || nums[high] == target || nums[low] == target) {
        return true
      }
      else if (nums[low] == nums[middle] && nums[middle] == nums[high]) {
        return search(nums, target, low, middle - 1) || search(nums, target, middle + 1, high)
      }
      else if (nums[middle] >= nums[low] && nums[middle] <= nums[high]) {
        if (target < nums[middle]) {
          high = middle - 1
        }
        else {
          low = middle + 1
        }
      }
      else if (nums[low] > nums[middle]) {
        if (target > nums[low] || target < nums[middle]) {
          high = middle - 1
        }
        else {
          low = middle + 1
        }
      }
      else { // nums[high] < nums[middle]
        if (target < nums[high] || target > nums[middle]) {
          low = middle + 1
        }
        else {
          high = middle - 1
        }
      }
    }
    return nums[low] == target
  }
}