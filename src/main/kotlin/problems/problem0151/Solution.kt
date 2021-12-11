package problems.problem0151

class Solution {
  fun reverseWords(s: String): String {
    val words = s.split(" ").filter { it.isNotEmpty() }
    return words.reversed().joinToString(" ")
  }
}