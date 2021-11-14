package problems.problem0364

import problems.problem0341.NestedInteger

class Solution {
  fun depthSumInverse(nestedList: List<NestedInteger>): Int {
    val depthToNums = mutableMapOf<Int, MutableList<Int>>()
    var maxDepth = 1
    fun recurse(nestedInteger: NestedInteger, depth: Int) {
      if (nestedInteger.isInteger()) {
        if (depth > maxDepth) {
          maxDepth = depth
        }
        val nums = depthToNums.getOrDefault(depth, mutableListOf())
        nums.add(nestedInteger.getInteger()!!)
        depthToNums[depth] = nums
      }
      else {
        val list = nestedInteger.getList()
        for (nested in list!!) {
          recurse(nested, depth + 1)
        }
      }
    }
    for (nested in nestedList) {
      recurse(nested, 1)
    }

    var sum = 0
    for (entry in depthToNums.entries) {
      val depth = entry.key
      val nums = entry.value
      val weight = maxDepth - depth + 1
      for (num in nums) {
        sum += num * weight
      }
    }
    return sum
  }
}