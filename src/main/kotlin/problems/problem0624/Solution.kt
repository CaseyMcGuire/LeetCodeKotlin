package problems.problem0624

import java.util.*

class Solution {
  fun maxDistance(arrays: List<List<Int>>): Int {
    val frequencyMap = TreeMap<Int, Int>()
    for (i in arrays.size - 1 downTo 1) {
      val array = arrays[i]
      frequencyMap.increment(array[0])
      frequencyMap.increment(array[array.size - 1])
    }

    var maxSoFar = Integer.MIN_VALUE
    for (i in 0 until arrays.size - 1) {
      val array = arrays[i]
      val smallest = array[0]
      val largest = array[array.size - 1]

      val smallestForward = frequencyMap.firstKey()
      val largestForward = frequencyMap.lastKey()

      val smallestToLargest = Math.abs(smallest - largestForward)
      val largestToSmallest = Math.abs(largest - smallestForward)
      if (smallestToLargest > maxSoFar) {
        maxSoFar = smallestToLargest
      }

      if (largestToSmallest > maxSoFar) {
        maxSoFar = largestToSmallest
      }

      val nextArray = arrays[i + 1]
      val nextLargest = nextArray[nextArray.size - 1]
      val nextSmallest = nextArray[0]
      frequencyMap.decrement(nextLargest)
      frequencyMap.decrement(nextSmallest)
    }

    return maxSoFar
  }


  private fun TreeMap<Int, Int>.increment(num: Int) {
    this.merge(num, 1) { cur, acc -> cur + acc }
  }

  private fun TreeMap<Int, Int>.decrement(num: Int) {
    val current = this[num]
    if (current == null) {
      return
    }
    if (current == 1) {
      this.remove(num)
    }
    else {
      this[num] = current - 1
    }
  }
}