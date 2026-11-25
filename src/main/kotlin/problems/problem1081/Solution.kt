package problems.problem1081

class Solution {
  fun smallestSubsequence(s: String): String {
    val charToLastIndex = mutableMapOf<Char, Int>()
    for (i in s.indices) {
      charToLastIndex[s[i]] = i
    }

    val str = mutableListOf<Char>()
    val present = mutableSetOf<Char>()
    for (i in s.indices) {
      val c = s[i]
      if (present.contains(c)) {
        continue
      }

      while (str.isNotEmpty()) {
        val last = str.last()
        val lastIndex = charToLastIndex[last]!!
        if (last > c && lastIndex > i) {
          present.remove(str.removeLast())
        }
        else {
          break
        }
      }

      if (present.add(c)) {
        str.add(c)
      }
    }
    return str.joinToString("")
  }
}