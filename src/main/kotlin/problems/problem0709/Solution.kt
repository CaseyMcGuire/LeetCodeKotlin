package problems.problem0709

class Solution {
  fun toLowerCase(s: String): String {
    return s.map { it.lowercaseChar() }.joinToString("")
  }
}