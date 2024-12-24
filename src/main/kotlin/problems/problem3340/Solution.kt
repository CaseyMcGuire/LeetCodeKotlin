package problems.problem3340

class Solution {
  fun isBalanced(num: String): Boolean {
    var numEvens = 0
    var numOdds = 0
    for ((i, c) in num.withIndex()) {
      val cNum = c.digitToInt()
      if (i % 2 == 0) {
        numEvens += cNum
      }
      else {
        numOdds += cNum
      }
    }
    return numEvens == numOdds
  }
}