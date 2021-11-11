package problems.problem0074

import java.util.*

class Solution {
  fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
    for (row in matrix) {
      if (target in row[0]..row[row.size - 1]) {
        return Arrays.binarySearch(row, target) >= 0
      }
    }
    return false
  }
}