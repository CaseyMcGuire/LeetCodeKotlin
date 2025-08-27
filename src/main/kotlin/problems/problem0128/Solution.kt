package problems.problem0128

class Solution {
  fun longestConsecutive(nums: IntArray): Int {
    val numSet = mutableSetOf<Int>()
    for (num in nums) {
      numSet.add(num)
    }


    var longestSoFar = 0
    for (num in numSet) {
      if (numSet.contains(num - 1)) {
        continue
      }

      var curLength = 1
      var curNum = num + 1
      while (numSet.contains(curNum)) {
        curLength++
        curNum++
      }

      longestSoFar = Math.max(curLength, longestSoFar)
    }
    return longestSoFar
  }
}