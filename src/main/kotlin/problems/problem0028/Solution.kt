package problems.problem0028

class Solution {
  fun strStr(haystack: String, needle: String): Int {
    if (needle.isEmpty()) {
      return 0
    }
    return firstIndexOf(haystack, needle)
  }

  private fun firstIndexOf(s: String, substring: String): Int {
    for (i in s.indices) {
      if (i + substring.length > s.length) {
        return -1
      }
      var matches = true
      for (j in substring.indices) {
        if (s[i + j] != substring[j]) {
          matches = false
          break
        }
      }
      if (matches) {
        return i
      }
    }
    return -1
  }
}