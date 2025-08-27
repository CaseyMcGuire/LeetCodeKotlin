package problems.problem0074

import java.util.*

class Solution {
  fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
    var low = 0
    var high = matrix.size - 1
    while (low <= high) {
      val middle = ((low.toLong() + high.toLong()) / 2L).toInt()
      val row = matrix[middle]
      if (target in row.first()..row.last()) {
        return row.binarySearch(target) >= 0
      }
      else if (target > row.last()) {
        low = middle + 1
      }
      else {
        high = middle - 1
      }
    }
    return false
  }
}