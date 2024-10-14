package problems.problem0032

class Solution {
  fun longestValidParentheses(s: String): Int {
    val openParens = mutableListOf<Int>()
    var longestSoFar = 0
    val longestAtIndex = mutableMapOf<Int, Int>()

    for (i in s.indices) {
      val paren = s[i]
      if (paren == '(') {
        openParens.add(i)
      }
      else {
        if (openParens.isNotEmpty()) {
          val openIndex = openParens.removeLast()
          val size = i - openIndex + 1

          val previousRunSize = longestAtIndex[openIndex - 1] ?: 0
          val totalValidSize = previousRunSize + size
          longestSoFar = Math.max(longestSoFar, totalValidSize)
          longestAtIndex[i] = totalValidSize
        }
      }
    }

    return longestSoFar
  }
}