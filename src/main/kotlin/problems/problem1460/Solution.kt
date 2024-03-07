package problems.problem1460

class Solution {
  fun canBeEqual(target: IntArray, arr: IntArray): Boolean {
    return target.toFrequencyMap() == arr.toFrequencyMap()
  }

  private fun IntArray.toFrequencyMap(): Map<Int, Int> {
    val map = mutableMapOf<Int, Int>()
    for (num in this) {
      map.merge(num, 1) { cur, acc -> cur + acc }
    }
    return map
  }
}