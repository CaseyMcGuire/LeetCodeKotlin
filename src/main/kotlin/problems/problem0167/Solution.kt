package problems.problem0167

class Solution {
  fun twoSum(numbers: IntArray, target: Int): IntArray {
    if (numbers.size <= 1) {
      return intArrayOf()
    }

    for ((i, elem) in numbers.withIndex()) {
      val remainder = target - elem
      val otherIndex = numbers.binarySearch(remainder, i + 1, numbers.size)
      if (otherIndex >= 0) {
        return intArrayOf(i + 1, otherIndex + 1)
      }
    }

    throw RuntimeException()
  }
}