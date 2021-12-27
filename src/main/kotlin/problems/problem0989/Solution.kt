package problems.problem0989

import java.math.BigInteger

class Solution {
  fun addToArrayForm(num: IntArray, k: Int): List<Int> {
    val str = StringBuilder()
    for (n in num) {
      str.append(n)
    }

    val convertedNum = BigInteger(str.toString())
    val sum = convertedNum.add(BigInteger(k.toString()))
    val sumStr = sum.toString()
    val result = mutableListOf<Int>()
    for (c in sumStr) {
      result.add(Character.getNumericValue(c))
    }
    return result
  }
}