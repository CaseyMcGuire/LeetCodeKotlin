package problems.problem0190

class Solution {
  // you need treat n as an unsigned value
  fun reverseBits(n:Int):Int {
    var reversed = 0
    for (i in 0 until 32) {
      val currentBit = (n ushr i) and 1
      if (currentBit == 0) {
        continue
      }
      val bitInCorrectPosition = (currentBit shl (31 - i))
      reversed = reversed or bitInCorrectPosition
    }
    return reversed
  }
}