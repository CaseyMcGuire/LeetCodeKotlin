package problems.problem1209
class Solution {
  fun removeDuplicates(s: String, k: Int): String {
    val charCounts = mutableListOf<CharCount>()
    for (c in s) {
      if (charCounts.isEmpty()) {
        charCounts.add(CharCount(c, 1))
      }
      else {
        val lastCharCount = charCounts.last()
        if (lastCharCount.char == c) {
          lastCharCount.count++
        }
        else {
          charCounts.add(CharCount(c, 1))
        }
      }

      if (charCounts.last().count == k) {
        charCounts.removeLast()
      }
    }

    val builder = StringBuilder()
    for (charCount in charCounts) {
      for (i in 0 until charCount.count) {
        builder.append(charCount.char)
      }
    }

    return builder.toString()
  }
}

data class CharCount(val char: Char, var count: Int)
