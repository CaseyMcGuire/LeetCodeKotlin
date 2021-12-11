package problems.problem0459

class Solution {
  fun repeatedSubstringPattern(s: String): Boolean {
    val maxWindowSize = s.length / 2
    for (windowSize in 1..maxWindowSize) {
      val willFit = s.length % windowSize == 0
      if (!willFit) {
        continue
      }
      val substring = s.substring(0, windowSize)
      val numTimesDuplicated = s.length / windowSize
      val dupeList = mutableListOf<String>()
      for (i in 0 until numTimesDuplicated) {
        dupeList.add(substring)
      }
      val duplicatedString = dupeList.joinToString("")
      if (duplicatedString == s) {
        return true
      }
    }
    return false
  }
}