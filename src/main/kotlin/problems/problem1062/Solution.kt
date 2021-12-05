package problems.problem1062

class Solution {
  fun longestRepeatingSubstring(s: String): Int {
    if (s.isEmpty() || s.length == 1) {
      return 0
    }
    val substrings = mutableSetOf<String>()
    for (windowSize in s.length - 1 downTo 0) {
      for (i in 0 until s.length - windowSize + 1) {
        val substring = s.substring(i, i + windowSize)
        if (!substrings.add(substring)) {
          return windowSize
        }
      }
      substrings.clear()
    }
    return 0
  }
}