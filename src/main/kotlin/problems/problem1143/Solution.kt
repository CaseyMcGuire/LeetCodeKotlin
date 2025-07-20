package problems.problem1143

class Solution {
  fun longestCommonSubsequence(text1: String, text2: String): Int {
    val cache = mutableMapOf<CacheKey, Int>()
    fun recurse(i: Int, j: Int): Int {
      if (i >= text1.length || j >= text2.length) {
        return 0
      }
      val key = CacheKey(i, j)
      val value = cache[key]
      if (value != null) {
        return value
      }

      val result = if (text1[i] == text2[j]) {
        1 + recurse(i + 1, j + 1)
      }
      else {
        Math.max(recurse(i + 1, j), recurse(i, j + 1))
      }
      cache[key] = result
      return result
    }
    return recurse(0, 0)
  }
}

data class CacheKey(val i: Int, val j: Int)