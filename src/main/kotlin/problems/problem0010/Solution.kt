package problems.problem0010

class Solution {
  fun isMatch(s: String, p: String): Boolean {

    fun recurse(i: Int, j: Int): Boolean {
      if (i == s.length && j == p.length) {
        return true
      }
      else if (i == s.length) {
        if (j < p.length - 1 && p[j + 1] == '*') {
          return recurse(i, j + 2)
        }
        else {
          return false
        }
      }
      else if (j == p.length) {
        return false
      }
      val first = s[i]
      val second = p[j]
      if (j < p.length - 1 && p[j + 1] == '*') {
        if (p[j] == s[i] || p[j] == '.') {
          return recurse(i + 1, j) || recurse(i, j + 2)
        }
        else {
          return recurse(i, j + 2)
        }
      }
      else if (p[j] == '.') {
        return recurse(i + 1, j + 1)
      }
      else {
        if (first != second) {
          return false
        }
        return recurse(i + 1, j + 1)
      }

    }
    return recurse(0, 0)
  }

  private fun onlyWildCard(s: String, i: Int): Boolean {
    val remainingLength = s.length - i
    if (remainingLength % 2 != 0) {
      return false
    }
    var cur = i
    while (true) {
      if (cur == s.length) {
        return true
      }
      if (s[cur + 1] != '*') {
        return false
      }
      cur += 2
    }
  }
}