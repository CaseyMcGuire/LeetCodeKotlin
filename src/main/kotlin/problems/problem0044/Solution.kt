package problems.problem0044

class Solution {
  fun isMatch(s: String, p: String): Boolean {
    val cache = mutableMapOf<CachedValue, Boolean>()
    fun recurse(i: Int, j: Int): Boolean {
      val cachedValue = CachedValue(i, j)
      val found = cache[cachedValue]
      if (found != null) {
        return found
      }
      if (i == s.length && j == p.length) {
        return true
      }
      else if (i == s.length) {
        for (h in j until p.length) {
          if (p[h] != '*') {
            return false
          }
        }
        return true
      }
      else if (j == p.length) {
        return false
      }

      val matches = if (p[j] == '?') {
        val next = recurse(i + 1, j + 1)
        cache[cachedValue] = next
        next
      }
      else if (p[j] == '*') {
        val first = recurse(i, j + 1)
        cache[cachedValue] = first
        val second = recurse(i + 1, j)
        cache[cachedValue] = second
        val third = recurse(i + 1, j + 1)
        cache[cachedValue] = third
        first || second || third
      }
      else {
        val next = s[i] == p[j] && recurse(i + 1, j + 1)
        cache[cachedValue] = next
        next
      }
      return matches
    }
    return recurse(0, 0)
  }
}

data class CachedValue(val i: Int, val j: Int)