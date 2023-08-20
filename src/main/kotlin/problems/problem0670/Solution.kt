package problems.problem0670

import java.util.*

class Solution {
  fun maximumSwap(num: Int): Int {
    val numsToIndices = TreeMap<Char, LinkedList<Int>>()
    val numStr = num.toString().toMutableList()

    for (i in numStr.indices) {
      val digit = numStr[i]
      val indices = numsToIndices[digit] ?: LinkedList<Int>()
      indices.add(i)
      numsToIndices[digit] = indices
    }

    for (i in numStr.indices) {
      val c = numStr[i]
      val larger = numsToIndices.tailMap(c, false)
      val highest = if (larger.isNotEmpty()) larger.lastKey() else null
      if (highest != null) {
        val indices = numsToIndices[highest]!!
        val lastIndex = indices[indices.size - 1]
        val last = numStr[lastIndex]
        numStr[lastIndex] = c
        numStr[i] = last
        break
      }
      else {
        val indices = numsToIndices[c]!!
        indices.removeFirst()
        if (indices.isEmpty()) {
          numsToIndices.remove(c)
        }
        else {
          numsToIndices[c] = indices
        }
      }
    }

    return numStr.joinToString("").toInt()
  }
}