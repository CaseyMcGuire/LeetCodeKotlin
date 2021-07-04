package problems.problem0058

class Solution {
  fun lengthOfLastWord(s: String): Int {
    val words = s.trim().split(" ")
    if (words.isEmpty()) {
      return 0
    }
    return words.last().length
  }
}