package problems.problem0788

class Solution {
  fun rotatedDigits(n: Int): Int {
    var count = 0
    for (i in 1..n) {
      if (isGood(i)) {
        count++
      }
    }
    return count
  }

  private fun isGood(n: Int): Boolean {
    val nStr = n.toString()
    var haveAtLeastOne = false
    for (c in nStr) {
      if (c == '3' || c == '4' || c == '7') {
        return false
      }
      if (c == '2' || c == '5' || c == '6' || c == '9') {
        haveAtLeastOne = true
      }
    }
    return haveAtLeastOne
  }
}