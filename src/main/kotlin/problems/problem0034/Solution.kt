package problems.problem0034

class Solution {
  fun searchRange(nums: IntArray, target: Int): IntArray {
    val initialIndex = nums.binarySearch(target)
    if (initialIndex < 0) {
      return intArrayOf(-1, -1)
    }
    var currentStarting = initialIndex
    var currentEnding = initialIndex
    var foundEnding = false
    var foundStarting = false
    while (true) {
      if (!foundEnding) {
        if (currentEnding == nums.size - 1) {
          foundEnding = true
        }
        else {
          val targetEnding = nums.binarySearch(target, currentEnding + 1, nums.size)
          if (targetEnding < 0) {
            foundEnding = true
          }
          else {
            currentEnding = targetEnding
          }
        }
      }

      if (!foundStarting) {
        if (currentStarting == 0) {
          foundStarting = true
        }
        else {
          val targetStarting = nums.binarySearch(target, 0, currentStarting)
          if (targetStarting < 0) {
            foundStarting = true
          }
          else {
            currentStarting = targetStarting
          }
        }
      }
      if (foundStarting && foundEnding) {
        break
      }
    }
    return intArrayOf(currentStarting, currentEnding)
  }
}

fun main(args: Array<String>) {
  println(Solution().searchRange(intArrayOf(5, 7, 7, 8, 8, 10), 8))
}