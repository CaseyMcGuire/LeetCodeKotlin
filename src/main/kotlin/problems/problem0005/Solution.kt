package problems.problem0005

import java.lang.RuntimeException

class Solution {
  fun longestPalindrome(s: String): String {
    if (s.isEmpty()) {
      return ""
    }
    if (s.length == 1) {
      return s
    }
    for (i in s.length downTo 1 ) {
      val longestSubstring = checkPalindrome(s, i)
      if (longestSubstring != null) {
        return longestSubstring
      }
    }
    throw RuntimeException()
  }

  private fun checkPalindrome(s: String, length: Int): String? {
    for (i in 0 until s.length - length + 1) {
      var start = i
      var end = i + length - 1
      if (isPalindrome(s, start, end)) {
        return s.substring(start, end + 1)
      }
    }
    return null
  }

  private fun isPalindrome(s: String, start: Int, end: Int): Boolean {
    var beginning = start
    var ending = end
    while (beginning < ending) {
      if (s[beginning] != s[ending]) {
        return false
      }
      beginning++
      ending--
    }
    return true
  }
}

fun main(args: Array<String>) {
  print(Solution().longestPalindrome("babad"))
}