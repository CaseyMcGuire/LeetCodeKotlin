package problems.problem1056

import java.util.*

class Solution {
  fun confusingNumber(n: Int): Boolean {
    val rotatedNum = LinkedList<Char>()
    val nStr = n.toString()
    for (c in nStr) {
      val rotated = getRotatedDigit(c)
      if (rotated == null) {
        return false
      }
      rotatedNum.addFirst(rotated)
    }
    val rotatedInt = rotatedNum.joinToString("").toInt()
    return rotatedInt != n
  }

  private fun getRotatedDigit(digit: Char): Char? {
    return when (digit) {
      '0' -> '0'
      '1' -> '1'
      '6' -> '9'
      '8' -> '8'
      '9' -> '6'
      else -> null
    }
  }
}