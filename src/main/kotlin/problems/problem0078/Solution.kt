package problems.problem0078

import java.util.*

class Solution {
  fun subsets(nums: IntArray): List<List<Int>> {
    val subsets = mutableListOf<List<Int>>()
    fun recurse(index: Int, currentList: Deque<Int>) {
      if (index == nums.size) {
        subsets.add(currentList.toList())
        return
      }
      currentList.push(nums[index])
      recurse(index + 1, currentList)
      currentList.pop()
      recurse(index + 1, currentList)
    }
    recurse(0, ArrayDeque())
    return subsets
  }
}

fun main(args: Array<String>) {
  println(Solution().subsets(intArrayOf(1,2,3)))
}