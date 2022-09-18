package problems.problem0120

class Solution {
  fun minimumTotal(triangle: List<List<Int>>): Int {
    if (triangle.isEmpty()) {
      return -1
    }
    var prevRow = triangle[0]
    var nextRow = mutableListOf<Int>()
    for (i in 1 until triangle.size) {
      val curRow = triangle[i]
      for (j in curRow.indices) {
        if (j == 0) {
          nextRow.add(curRow[j] + prevRow[j])
        }
        else if (j == curRow.size - 1) {
          nextRow.add(curRow[j] + prevRow[j - 1])
        }
        else {
          val leftSum = curRow[j] + prevRow[j - 1]
          val rightSum = curRow[j] + prevRow[j]
          nextRow.add(Math.min(leftSum, rightSum))
        }
      }
      prevRow = nextRow
      nextRow = mutableListOf<Int>()
    }
    return prevRow.min()!!
  }
}