package problems.problem0291

class Solution {
  fun wordPatternMatch(pattern: String, s: String): Boolean {
    val chars = pattern.toCharArray()
    val mappings = mutableMapOf<Char, String>()
    val matches = mutableSetOf<String>()
    fun recurse(charIndex: Int, curIndex: Int): Boolean {
      if (curIndex > s.length) {
        return false
      }
      if (charIndex == chars.size) {
        return curIndex == s.length
      }

      val curChar = chars[charIndex]
      val existingMapping = mappings[curChar]
      if (existingMapping != null) {
        if (existingMapping.length + curIndex > s.length) {
          return false
        }
        val subString = s.substring(curIndex, curIndex + existingMapping.length)
        if (subString != existingMapping) {
          return false
        }

        return recurse(charIndex + 1, curIndex + existingMapping.length)
      } else {

        for (i in 1..s.length - curIndex) {
          val end = curIndex + i
          val substring = s.substring(curIndex, curIndex + i)
          if (matches.contains(substring)) {
            continue
          }
          val c = pattern[charIndex]
          mappings[c] = substring
          matches.add(substring)
          val foundMatch = recurse(charIndex + 1, end)
          if (foundMatch) {
            return true
          }
          mappings.remove(c)
          matches.remove(substring)
        }
        return false
      }

    }

    return recurse(0, 0)

  }
}