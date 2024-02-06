package problems.problem1541

class Solution {
  fun minInsertions(s: String): Int {
    var i = 0
    var openLeftParens = 0
    var missingParens = 0
    while (i < s.length) {
      if (s[i] == '(') {
        openLeftParens++
        i++
      }
      else {
        val isDoubleRightParen = i + 1 < s.length && s[i + 1] == ')'
        if (isDoubleRightParen) {
          if (openLeftParens > 0) {
            openLeftParens--
          }
          else {
            missingParens++
          }
          i += 2
        }
        else {
          if (openLeftParens > 0) {
            openLeftParens--
            missingParens++
          }
          else {
            missingParens += 2
          }
          i++
        }
      }
    }

    missingParens += (openLeftParens * 2)

    return missingParens
  }
}