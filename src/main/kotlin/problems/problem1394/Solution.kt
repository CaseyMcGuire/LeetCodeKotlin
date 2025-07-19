package problems.problem1394

import java.util.TreeSet

class Solution {
  fun findLucky(arr: IntArray): Int {
    val equalValues = TreeSet<Int>()
    val numToFrequency = mutableMapOf<Int, Int>()

    for (num in arr) {
      numToFrequency.merge(num, 1) { cur, acc -> cur + acc }
      if (numToFrequency[num]!! == num) {
        equalValues.add(num)
      }
      else {
        equalValues.remove(num)
      }
    }
    return equalValues.lastOrNull() ?: -1
  }
}