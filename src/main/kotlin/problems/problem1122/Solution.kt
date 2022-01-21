package problems.problem1122

class Solution {
  fun relativeSortArray(arr1: IntArray, arr2: IntArray): IntArray {
    val numToIndex = mutableMapOf<Int, Int>()
    for (i in arr2.indices) {
      numToIndex[arr2[i]] = i
    }

    return arr1.sortedWith(
      compareBy(
        { numToIndex[it] ?: Integer.MAX_VALUE },
        { it }
      )
    ).toIntArray()
  }
}