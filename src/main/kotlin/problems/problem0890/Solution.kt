package problems.problem0890

class Solution {
  fun findAndReplacePattern(words: Array<String>, pattern: String): List<String> {
    return words.filter { matchesPattern(it, pattern) }
  }

  fun matchesPattern(word: String, pattern: String): Boolean {
    if (word.length != pattern.length) {
      return false
    }
    val charMap = mutableMapOf<Char, Char>()
    val patternChars = mutableSetOf<Char>()
    for (i in word.indices) {
      val existingMapping = charMap[word[i]]
      if (existingMapping == null) {
        if (patternChars.contains(pattern[i])) {
          return false
        }
        patternChars.add(pattern[i])
        charMap[word[i]] = pattern[i]
      }
      else {
        if (existingMapping != pattern[i]) {
          return false
        }
      }
    }

    return true
  }
}