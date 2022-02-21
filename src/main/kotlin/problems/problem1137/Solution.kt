package problems.problem1137

class Solution {
  fun tribonacci(n: Int): Int {

    val cache = mutableMapOf<Int, Int>()

    fun recurse(cur: Int): Int {
      if (cur == 0) {
        return 0
      }
      if (cur == 1 || cur == 2) {
        return 1
      }
      val cached = cache[cur]
      if (cached != null) {
        return cached
      }

      val value = recurse(cur - 1) + recurse(cur - 2) + recurse(cur - 3)
      cache[cur] = value
      return value
    }
    return recurse(n)
  }
}