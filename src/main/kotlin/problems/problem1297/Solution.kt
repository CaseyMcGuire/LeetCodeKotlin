package problems.problem1297

class Solution {
  fun maxFreq(s: String, maxLetters: Int, minSize: Int, maxSize: Int): Int {
    val substringToFrequency = mutableMapOf<String, Int>()
    for (size in minSize..maxSize) {
      for (i in 0 until s.length - size + 1) {
        val substring = s.substring(i, i + size)
        if (!substring.hasFewerLetters(maxLetters)) {
          continue
        }
        substringToFrequency.merge(substring, 1) { cur, acc -> cur + acc }
      }
    }
    val entries = substringToFrequency.entries.toList().sortedBy { it.value }
    if (entries.isEmpty()) {
      return 0
    }
    return entries.last().value
  }

  private fun String.hasFewerLetters(max: Int): Boolean {
    val uniqueLetters = mutableSetOf<Char>()
    for (c in this) {
      uniqueLetters.add(c)
    }
    return uniqueLetters.size <= max
  }
}