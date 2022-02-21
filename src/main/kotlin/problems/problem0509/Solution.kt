package problems.problem0509

import java.util.*

class Solution {
  fun fib(n: Int): Int {
    if (n == 0) {
      return 0
    }
    if (n == 1) {
      return 1
    }

    val nums = LinkedList<Int>()
    nums.addLast(0)
    nums.addLast(1)

    for (i in 2 until n) {
      val sum = nums.sum()
      nums.removeFirst()
      nums.addLast(sum)
    }
    return nums.sum()
  }
}