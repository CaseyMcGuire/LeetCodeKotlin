package problems.problem0042

import java.util.*

class Solution {
  fun trap(A: IntArray): Int {
    val left = TreeMap<Int, Int>()
    val right = TreeMap<Int, Int>()
    for (i in A.indices.reversed()) {
      right.increment(A[i])
    }

    var total = 0
    for (i in 0 until A.size) {
      right.decrement(A[i])
      val leftLargest = left.lastEntry()
      val rightLargest = right.lastEntry()
      if (leftLargest != null && rightLargest != null) {
        val smaller = Math.min(leftLargest.key, rightLargest.key)
        if (smaller > A[i]) {
          total += smaller - A[i]
        }
      }
      left.increment(A[i])
    }

    return total
  }

  fun TreeMap<Int, Int>.increment(num: Int) {
    this.merge(num, 1) { cur, acc -> cur + acc }
  }

  fun TreeMap<Int, Int>.decrement(num: Int) {
    val curFrequency = this[num] ?: return
    if (curFrequency == 1) {
      this.remove(num)
    }
    else {
      this[num] = curFrequency - 1
    }
  }
}