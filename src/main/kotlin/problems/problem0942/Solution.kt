package problems.problem0942

import java.util.*

class Solution {
  fun diStringMatch(s: String): IntArray {
    val queue = LinkedList<Int>()
    for (i in 0..s.length) {
      queue.addLast(i)
    }

    val nums = mutableListOf<Int>()
    for (i in s.indices) {
      if (s[i] == 'I') {
        nums.add(queue.removeFirst())
      }
      else {
        nums.add(queue.removeLast())
      }
    }
    nums.add(queue.removeLast())
    return nums.toIntArray()
  }
}