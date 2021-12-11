package problems.problem1021

class Solution {
  fun removeOuterParentheses(s: String): String {
    val primitives = mutableListOf<String>()
    var numLeftParens = 0
    var currentPrimitive = StringBuilder()
    for (i in s.indices) {
      val elem = s[i]
      if (elem == '(') {
        numLeftParens++
        currentPrimitive.append(elem)
      }
      else {
        currentPrimitive.append(elem)
        numLeftParens--
        if (numLeftParens == 0) {
          primitives.add(currentPrimitive.toString())
          currentPrimitive = StringBuilder()
        }
      }
    }
    val removeOuterPrimitives = primitives.map { it.substring(1, it.length - 1) }
    return removeOuterPrimitives.joinToString("")
  }
}