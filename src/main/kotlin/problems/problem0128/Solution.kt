package problems.problem0128

class Solution {
  fun longestConsecutive(nums: IntArray): Int {
    if (nums.isEmpty()) {
      return 0
    }
    val numSet = mutableSetOf<Int>()
    for (num in nums) {
      numSet.add(num)
    }

    val visited = mutableSetOf<Int>()
    fun recurse(num: Int): Int {
      if (visited.contains(num)) {
        return 0
      }
      if (!numSet.contains(num)) {
        return 0
      }
      visited.add(num)
      return 1 + recurse(num - 1) + recurse(num + 1)
    }
    return nums.map { recurse(it) }.max()!!
  }
}