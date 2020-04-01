package problems.problem0003

class Solution {
  fun lengthOfLongestSubstring(s: String): Int {
    if (s.isEmpty()) {
      return 0
    }
    val characterToLastIndex = mutableMapOf<Char, Int>()
    var previousRemoveIndex = 0
    var currentMax = 1
    for (index in s.indices) {
      val currentChar = s[index]
      val previousIndex = characterToLastIndex[currentChar]
      if (previousIndex === null) {
        characterToLastIndex[currentChar] = index
        if (characterToLastIndex.size > currentMax) {
          currentMax = characterToLastIndex.size
        }
      }
      else {
        for (i in previousRemoveIndex..previousIndex) {
          characterToLastIndex.remove(s[i])
        }
        previousRemoveIndex = previousIndex + 1
        characterToLastIndex[currentChar] = index
      }
    }
    return currentMax
  }
}
