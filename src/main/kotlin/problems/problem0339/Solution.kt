package problems.problem0339

import problems.problem0341.NestedInteger

class Solution {
  fun depthSum(nestedList: List<NestedInteger>): Int {
    var sum = 0
    fun recurse(nestedList: List<NestedInteger>, depth: Int) {
      for (list in nestedList) {
        if (list.isInteger()) {
          sum += list.getInteger()!! * depth
        }
        else {
          val nextList = list.getList()!!
          if (nextList.isNotEmpty()) {
            recurse(nextList, depth + 1)
          }
        }
      }
    }
    recurse(nestedList, 1)
    return sum
  }
}