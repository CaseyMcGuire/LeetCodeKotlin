package problems.problem0290

class Solution {
  fun wordPattern(pattern: String, s: String): Boolean {
    val words = s.split(' ')
    val mapping = mutableMapOf<Char, String>()
    val takenMappings = mutableSetOf<String>()
    if (words.size != pattern.length) {
      return false
    }
    for (i in 0 until pattern.length) {
      val existing = mapping[pattern[i]]
      if (existing != null) {
        if (existing != words[i]) {
          return false
        }
      }
      else {
        if (!takenMappings.add(words[i])) {
          return false
        }

        mapping[pattern[i]] = words[i]
      }
    }
    return true
  }
}