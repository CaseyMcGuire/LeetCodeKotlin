package problems.problem0191

class Solution {
  // you need treat n as an unsigned value
  fun hammingWeight(n:Int):Int {
    var num = n
    var numBits = 0
    while (num != 0) {
      val hasOneBit = (num and 1) == 1
      if (hasOneBit) {
        numBits++
      }
      num = num ushr 1

    }
    return numBits
  }
}