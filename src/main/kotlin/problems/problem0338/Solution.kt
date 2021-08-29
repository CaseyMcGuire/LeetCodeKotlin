package problems.problem0338

class Solution {
  fun countBits(n: Int): IntArray {
    val bits = mutableListOf<Int>()
    for (i in 0 until n + 1) {
      bits.add(bitsInNum(i))
    }
    return bits.toIntArray()
  }

  fun bitsInNum(n: Int): Int {
    var num = n
    var numBits = 0
    while (num > 0) {
      val isOdd = num % 2 == 1
      if (isOdd) {
        numBits++
      }
      num = num shr 1
    }
    return numBits
  }
}