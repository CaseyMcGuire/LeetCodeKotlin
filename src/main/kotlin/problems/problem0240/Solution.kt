package problems.problem0240

import java.util.*

class Solution {
  fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
    for (row in matrix) {
      if (Arrays.binarySearch(row, target) >= 0) {
        return true
      }
    }
    return false
  }
}