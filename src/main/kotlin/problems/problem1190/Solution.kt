package problems.problem1190

class Solution {
  fun reverseParentheses(s: String): String {
    val reversedString = StringBuilder()
    var i = 0
    while (i < s.length) {
      i = if (s[i] == '(') {
        val (reversedSubstring, index) = reverseString(s, i + 1)
        reversedString.append(reversedSubstring)
        index
      } else {
        reversedString.append(s[i])
        i + 1
      }
    }
    return reversedString.toString()
  }

  private fun reverseString(s: String, index: Int): Pair<StringBuilder, Int> {
    val reversedString = StringBuilder()
    var i = index
    while (s[i] != ')') {
      i = if (s[i] == '(') {
        val (reversedSubstring, newIndex) = reverseString(s, i + 1)
        reversedString.append(reversedSubstring)
        newIndex
      } else {
        reversedString.append(s[i])
        i + 1
      }
    }
    return Pair(reversedString.reverse(), i + 1)
  }
}