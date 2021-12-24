package problems.problem0027

class Solution {
  fun removeElement(nums: IntArray, `val`: Int): Int {
    var numOccurrences = 0
    var cur = 0
    for (i in nums.indices) {
      if (nums[i] != `val`) {
        nums[cur] = nums[i]
        cur++
      }
      else {
        numOccurrences++
      }
    }
    return nums.size - numOccurrences
  }
}