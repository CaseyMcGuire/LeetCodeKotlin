package problems.problem1359

import java.math.BigInteger

class Solution {
  fun countOrders(n: Int): Int {
    var total = 1.toBigInteger()
    for (i in 1..n) {
      total *= sumOfNums(i.toBigInteger() * 2.toBigInteger() - 1.toBigInteger())
    }
    return (total % (Math.pow(10.0, 9.0).toInt().toBigInteger() + 7.toBigInteger())).toInt()
  }

  fun sumOfNums(n: BigInteger): BigInteger {
    return (n * (n + 1.toBigInteger())) / 2.toBigInteger()
  }
}