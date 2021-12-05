package problems.problem0161

class Solution {
  fun isOneEditDistance(s: String, t: String): Boolean {
    if (Math.abs(s.length - t.length) > 1)  {
      return false
    }
    if (s.length == 0 && t.length > 0 || t.length == 0 && s.length > 0) {
      return true
    }
    var first = 0
    var second = 0
    var hasReplaced = false
    while (true) {
      // the one to change is at the end of either string
      if (s.length == first && t.length > second || t.length == second && s.length > first) {
        return true
      }
      else if (s.length == first && t.length == second) {
        break
      }
      if (s[first] != t[second]) {
        if (hasReplaced) {
          return false
        }
        else {
          hasReplaced = true
        }
        if (s.length == t.length) {
          first++
          second++
        }
        else if (s.length < t.length) {
          second++
        }
        else {
          first++
        }
      }
      else {
        first++
        second++
      }
    }
    return hasReplaced
  }
}