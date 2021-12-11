package problems.problem0080

class Solution {
  fun removeDuplicates(nums: IntArray): Int {
    if (nums.isEmpty()) {
      return 0
    }
    var i = 1
    var curElement = 1
    var previous = Element(nums[0], 1)
    while (curElement < nums.size) {
      val cur = nums[curElement]
      if (cur == previous.elem) {
        if (previous.frequency == 1) {
          nums[i] = cur
          previous.frequency++
          i++
        }
      }
      else {
        nums[i] = cur
        previous = Element(cur, 1)
        i++
      }
      curElement++
    }
    return i
  }
}

data class Element(val elem: Int, var frequency: Int)