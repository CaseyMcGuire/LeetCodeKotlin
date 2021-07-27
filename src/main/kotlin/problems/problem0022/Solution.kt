package problems.problem0022

class Solution {
  fun generateParenthesis(n: Int): List<String> {
    val parentheses = mutableListOf<String>()
    fun recurse(numLeftParens: Int, numOpenParens: Int, currentString: StringBuilder) {
      if (currentString.length == n * 2) {
        parentheses.add(currentString.toString())
      }
      else {
        if (numLeftParens < n) {
          currentString.append('(')
          recurse(numLeftParens + 1, numOpenParens + 1, currentString)
          currentString.deleteCharAt(currentString.length - 1)
        }
        if (numOpenParens > 0) {
          currentString.append(')')
          recurse(numLeftParens, numOpenParens - 1, currentString)
          currentString.deleteCharAt(currentString.length - 1)
        }
      }
    }
    recurse(0, 0, StringBuilder())
    return parentheses
  }
}

fun main(args: Array<String>) {
  println(Solution().generateParenthesis(3))
}