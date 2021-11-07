package problems.problem1198

import java.util.*

class Solution {
  fun smallestCommonElement(mat: Array<IntArray>): Int {
    var intersection: Set<Int>? = null
    for (row in mat.indices) {
      val currentRow = mutableSetOf<Int>()
      for (column in mat[row].indices) {
        currentRow.add(mat[row][column])
      }
      if (intersection == null) {
        intersection = currentRow
      }
      else {
        intersection = intersection.intersect(currentRow)
      }
    }
    if (intersection == null || intersection.isEmpty()) {
      return -1
    }
    return TreeSet(intersection!!).pollFirst()
  }
}