package problems.problem0402

class Solution {
  fun removeKdigits(num: String, k: Int): String {
    if (k >= num.length) {
      return "0"
    }


    var remaining = k
    val nums = num.map { it.digitToInt() }
    val numBuilder = mutableListOf<Int>()
    for (i in nums.indices) {
      if (numBuilder.isEmpty()) {
        numBuilder.add(nums[i])
        continue
      }

      while (numBuilder.isNotEmpty() && numBuilder.last() > nums[i] && remaining > 0) {
        remaining--
        numBuilder.removeLast()
      }
      numBuilder.add(nums[i])
    }

    val toReturn = numBuilder.dropLast(remaining).dropWhile { it == 0 }.joinToString("")
    if (toReturn.isEmpty()) {
      return "0"
    }
    else {
      return toReturn
    }
  }
}