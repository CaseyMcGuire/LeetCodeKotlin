package problems.problem0014

class Solution {
  fun longestCommonPrefix(strs: Array<String>): String {
    if (strs.isEmpty()) {
      return ""
    }
    val currentPrefix = StringBuilder()
    var currentIndex = 0
    while (true) {
      if (currentIndex >= strs[0].length) {
        break
      }
      val currentChar = strs[0][currentIndex]
      for (string in strs) {
        if (currentIndex >= string.length || string[currentIndex] != currentChar) {
          return currentPrefix.toString()
        }
      }
      currentIndex++
      currentPrefix.append(currentChar)
    }
    return currentPrefix.toString()
  }
}