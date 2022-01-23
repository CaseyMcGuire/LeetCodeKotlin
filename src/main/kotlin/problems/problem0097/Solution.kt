package problems.problem0097

class Solution {
  fun isInterleave(s1: String, s2: String, s3: String): Boolean {
    val firstLength = s1.length
    val secondLength = s2.length
    if (firstLength + secondLength != s3.length) {
      return false
    }

    val cache = mutableSetOf<CacheItem>()

    fun recurse(i: Int, j: Int): Boolean {
      if (i == s1.length && j == s2.length) {
        return true
      }
      val item = CacheItem(i, j)
      if (!cache.add(item)) {
        return false
      }
      val c1 = if (i < s1.length) s1[i] else null
      val c2 = if (j < s2.length) s2[j] else null
      val cur = s3[i + j]
      if (c1 != cur && c2 != cur) {
        return false
      }
      if (c1 == cur) {
        if (recurse(i + 1, j)) {
          return true
        }
      }

      if (c2 == cur) {
        if (recurse(i, j + 1)) {
          return true
        }
      }
      return false
    }

    return recurse(0, 0)
  }
}

data class CacheItem(val i: Int, val j: Int)