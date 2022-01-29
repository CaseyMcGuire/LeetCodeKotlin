package problems.problem0202

class Solution {
  fun isHappy(n: Int): Boolean {
    if (n == 1) {
      return true
    }
    val seenNumbers = mutableSetOf<Int>()
    var curNumber = n
    while (true) {
      val squaredDigits = getSquareOfDigits(curNumber)
      if (squaredDigits == 1) {
        return true
      }
      else if (!seenNumbers.add(squaredDigits)) {
        return false
      }
      curNumber = squaredDigits
    }
  }

  private fun getSquareOfDigits(n: Int): Int {
    return n.toString().toCharArray().map { Character.getNumericValue(it) }.map { Math.pow(it.toDouble(), 2.0).toInt() }.sum()
  }
}