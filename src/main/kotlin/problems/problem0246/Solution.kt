package problems.problem0246

class Solution {
  fun isStrobogrammatic(num: String): Boolean {
    if (num.isEmpty()) {
      return true
    }
    var i = 0
    var j = num.length - 1
    while (i <= j) {
      val flipped = getFlippedNumber(num[i])
      if (flipped != num[j]) {
        return false
      }
      i++
      j--
    }
    return true
  }

  fun getFlippedNumber(num: Char): Char? {
    return when(num) {
      '0' -> '0'
      '1' -> '1'
      '6' -> '9'
      '8' -> '8'
      '9' -> '6'
      else -> null
    }
  }
}