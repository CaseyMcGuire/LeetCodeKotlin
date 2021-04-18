package problems.problem1190

import java.util.*

class Solution {
  fun reverseParentheses(s: String): String {
    var currentList = mutableListOf<Char>()
    val subLists = ArrayDeque<MutableList<Char>>()
    var i = 0
    while (i < s.length) {
      when {
        s[i] == '(' -> {
          subLists.push(currentList)
          currentList = mutableListOf()
        }
        s[i] == ')' -> {
          val prevList = subLists.pop()
          prevList.addAll(currentList.asReversed())
          currentList = prevList
        }
        else -> {
          currentList.add(s[i])
        }
      }
      i += 1
    }
    return currentList.joinToString("")
  }
}