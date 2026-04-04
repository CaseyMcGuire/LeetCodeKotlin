package problems.problem0565

class Solution {
  fun arrayNesting(nums: IntArray): Int {
    val visitedIndices = mutableSetOf<Int>()
    var longestSoFar = 0
    for (i in nums.indices) {
      var curLength = 0
      var curIndex = i
      while (!visitedIndices.contains(curIndex)) {
        visitedIndices.add(curIndex)
        curIndex = nums[curIndex]
        curLength++
      }
      longestSoFar = Math.max(longestSoFar, curLength)

    }
    return longestSoFar
  }
}