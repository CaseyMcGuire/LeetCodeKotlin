package problems.problem0371

class Solution {
  fun getSum(a: Int, b: Int): Int {
    var hasRemainder = false
    var sum = 0
    var i = 0
    while (i < 32) {
      var aPlace = (a shr i) and 1
      var bPlace = (b shr i) and 1
      var curPlace = 0
      if (aPlace == 1 && bPlace == 1) {
        if (hasRemainder) {
          curPlace = 1
        }
        else {
          hasRemainder = true
        }
      }
      else {
        var bAndAPlace = aPlace xor bPlace
        if (bAndAPlace == 1) {
          if (!hasRemainder) {
            curPlace = 1
            hasRemainder = false
          }
        }
        else {
          if (hasRemainder) {
            curPlace = 1
            hasRemainder = false
          }
        }
      }

      curPlace = curPlace shl i
      sum = sum or curPlace
      i++
    }
    return sum
  }
}