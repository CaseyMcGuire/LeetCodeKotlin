package problems.problem0153

class Solution {
  fun findMin(nums: IntArray): Int {
    var high = nums.size - 1
    val arr = nums
    var low = 0
    while (true) {
      val middle = (high + low) / 2
      val isSmallerThanLeft = middle == 0 || nums[middle - 1] > nums[middle]
      val isBiggerThanRight = middle == nums.size - 1 || nums[middle + 1] > nums[middle]
      if (isSmallerThanLeft && isBiggerThanRight) {
        return nums[middle]
      }
      if (nums[middle] > nums[high]) {
        low = middle + 1
      }
      else {
        high = middle - 1
      }
    }
  }
}

fun main(args: Array<String>) {
  println(Solution().findMin(intArrayOf(2,1)))
}