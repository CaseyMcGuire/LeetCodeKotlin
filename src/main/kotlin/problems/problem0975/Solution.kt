package problems.problem0975

import java.util.*
import kotlin.collections.HashMap

class Solution {
  fun oddEvenJumps(arr: IntArray): Int {
    if (arr.isEmpty()) {
      return 0
    }
    val cache = HashMap<Jump, Boolean>()
    val valueToIndex = TreeMap<Int, Int>()

    var total = 1
    valueToIndex[arr[arr.size - 1]] = arr.size - 1
    cache[Jump(true, arr.size - 1)] = true
    cache[Jump(false, arr.size - 1)] = true
    for (i in arr.size - 2 downTo 0) {
      var currentIndex = i
      val nextIndexIfOddJump = valueToIndex.ceilingEntry(arr[currentIndex])?.value
      val nextIndexIfEvenJump = valueToIndex.floorEntry(arr[currentIndex])?.value
      if (nextIndexIfOddJump == null) {
        cache[Jump(true, i)] = false
      }
      else {
        cache[Jump(true, i)] = cache[Jump(false, nextIndexIfOddJump)] == true
      }

      if (nextIndexIfEvenJump == null) {
        cache[Jump(false, i)] = false
      }
      else {
        cache[Jump(false, i)] = cache[Jump(true, nextIndexIfEvenJump)] == true
      }
      if (cache[Jump(true, i)] == true) {
        total += 1
      }
      valueToIndex[arr[i]] = i
    }
    return total
  }
}

data class Jump(val isOdd: Boolean, val index: Int)




fun main(args: Array<String>) {
  println(Solution().oddEvenJumps(intArrayOf(10,13,12,14,15)))
  println(Solution().oddEvenJumps(intArrayOf(2,3,1,1,4)))
}
