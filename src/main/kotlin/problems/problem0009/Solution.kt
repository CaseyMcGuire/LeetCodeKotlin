package problems.problem0009

class Solution {
  fun isPalindrome(x: Int): Boolean {
    if (x < 0) {
      return false
    }
    val xStr = x.toString()
    var start = 0
    var end = xStr.length - 1
    while (start < end) {
      if (xStr[start] != xStr[end]) {
        return false
      }
      start++
      end--
    }
    return true
  }
}