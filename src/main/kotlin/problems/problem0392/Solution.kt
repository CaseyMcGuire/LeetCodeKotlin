package problems.problem0392

class Solution {
  fun isSubsequence(s: String, t: String): Boolean {
    if (s.isEmpty()) {
      return true
    }
    if (s.length > t.length) {
      return false
    }

    var i = 0
    var j = 0
    while (j < t.length) {
      if (s[i] == t[j]) {
        i++
        if (i == s.length) {
          return true
        }
      }
      j++
    }
    return false
  }
}