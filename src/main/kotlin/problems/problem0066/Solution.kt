package problems.problem0066

import java.util.*

class Solution {
  fun plusOne(digits: IntArray): IntArray {
    var carry = 1
    val newDigits = LinkedList<Int>()
    for (i in digits.size - 1 downTo 0) {
      val value = digits[i] + carry
      val elem = if (value == 10) {
        0
      } else {
        carry = 0
        value
      }
      newDigits.addFirst(elem)
    }
    if (carry == 1) {
      newDigits.addFirst(carry)
    }
    return newDigits.toIntArray()
  }
}