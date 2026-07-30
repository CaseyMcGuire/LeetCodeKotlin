package problems.problem0096

class Solution {
  fun numTrees(n: Int): Int {
    val cache = mutableMapOf<Int, Int>()

    // num way arrange on left

    fun recurse(num: Int): Int {
      if (num == 0) {
        return 1
      }

      if (num == 1) {
        return 1
      }
      val cachedValue = cache[num]
      if (cachedValue != null) {
        return cachedValue
      }

      var sum = 0
      for (i in 1..num) {
        sum += recurse(i - 1) * recurse(num - i)
      }
      cache[num] = sum
      return sum
    }
    return recurse(n)
  }
}