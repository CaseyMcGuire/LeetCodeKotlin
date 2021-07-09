package problems.problem0163

import java.util.*

class Solution {
  fun findMissingRanges(nums: IntArray, lower: Int, upper: Int): List<String> {
    val missingRanges = mutableListOf<String>()
    if (nums.isEmpty()) {
      return mutableListOf(formatRange(lower, upper))
    }
    if (nums[0] != lower) {
      missingRanges.add(formatRange(lower, nums[0] - 1))
    }
    val linkedList = LinkedList<Int>(nums.toList())
    var current: Int? = null
    while (linkedList.isNotEmpty()) {
      if (current == null) {
        current = linkedList.removeFirst()
        continue
      }
      val next = linkedList.removeFirst()
      if (current != next - 1) {

        missingRanges.add(formatRange(current + 1, next - 1))
      }
      current = next
    }

    if (nums[nums.size - 1] != upper) {
      missingRanges.add(formatRange(nums[nums.size - 1] + 1, upper))
    }

    return missingRanges
  }

  private fun formatRange(lower: Int, upper: Int): String {
    if (lower == upper) {
      return lower.toString()
    }
    return "$lower->$upper"
  }
}

fun main(args: Array<String>) {
  println(Solution().findMissingRanges(intArrayOf(0,9), 0, 9))
}