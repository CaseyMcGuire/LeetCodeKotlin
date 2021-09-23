package problems.problem0070

class Solution {
  fun climbStairs(n: Int): Int {
    if (n == 1) {
      return 1
    }
    else if (n == 2) {
      return 2
    }
    val result = IntArray(n)
    result[result.size - 1] = 1
    result[result.size - 2] = 2
    var i = result.size - 3
    while (i >= 0) {
      result[i] = result[i + 1] + result[i + 2]
      i--
    }
    return result[0]
  }
}