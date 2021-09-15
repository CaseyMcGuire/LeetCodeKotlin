package problems.problem0704

class Solution {
  fun search(nums: IntArray, target: Int): Int {
    var high = nums.size - 1
    var low = 0
    while (true) {
      val middle = (low + high) / 2

      if (nums[middle] == target) {
        return middle
      }
      else if (high <= low) {
        break
      }
      else if (nums[middle] > target) {
        high = middle - 1
      }
      else { //num[middle] < target
        low = middle + 1
      }
    }
    return -1
  }
}