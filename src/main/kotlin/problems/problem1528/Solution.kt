package problems.problem1528

class Solution {
  fun restoreString(s: String, indices: IntArray): String {
    val shuffledString = CharArray(s.length)
    for (i in s.indices) {
      shuffledString[indices[i]] = s[i]
    }
    return shuffledString.joinToString("")
  }
}