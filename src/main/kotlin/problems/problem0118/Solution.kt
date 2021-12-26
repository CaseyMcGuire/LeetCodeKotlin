package problems.problem0118

class Solution {
  fun generate(numRows: Int): List<List<Int>> {
    if (numRows == 0) {
      return emptyList()
    }
    val triangle = mutableListOf(listOf(1))
    for (i in 1 until numRows) {
      val previousRow = triangle[i - 1]
      val numInRow = i + 1
      val curRow = mutableListOf<Int>()
      for (i in 0 until numInRow) {
        if (i == 0 || i == numInRow - 1) {
          curRow.add(1)
        }
        else {
          curRow.add(previousRow[i - 1] + previousRow[i])
        }
      }
      triangle.add(curRow)
    }

    return triangle
  }
}