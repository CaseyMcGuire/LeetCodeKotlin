package problems.problem2390

import java.util.*

class Solution {
  fun removeStars(s: String): String {
    val letters = LinkedList<Char>()
    for (c in s) {
      if (c == '*') {
        letters.removeLast()
      }
      else {
        letters.add(c)
      }
    }
    return letters.joinToString("")
  }
}