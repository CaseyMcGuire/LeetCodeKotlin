package problems.problem0125

class Solution {
  fun isPalindrome(s: String): Boolean {
    val updatedString = s.toLowerCase().filter { Character.isLetterOrDigit(it) }.toString()
    var start = 0
    var end = updatedString.length - 1
    while (start < end) {
      if (updatedString[start] != updatedString[end]) {
        return false
      }
      start++
      end--
    }
    return true
  }
}