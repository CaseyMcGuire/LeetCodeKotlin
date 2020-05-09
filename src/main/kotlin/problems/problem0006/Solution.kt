package problems.problem0006

import java.lang.StringBuilder

class Solution {
  fun convert(s: String, numRows: Int): String {
    if (numRows == 1) {
      return s
    }
    val rows = (0..numRows).map { ArrayList<Char>() }
    var currentRowIndex = -1
    var currentDirection = Direction.DOWN
    for (c in s.toCharArray()) {
      if (currentDirection === Direction.DOWN) currentRowIndex++
      else currentRowIndex--

      if (currentRowIndex == 0) currentDirection = Direction.DOWN
      else if (currentRowIndex == numRows - 1) currentDirection = Direction.UP

      rows[currentRowIndex].add(c)
    }
    val builder = StringBuilder()
    for (row in rows) {
      for (element in row) {
        builder.append(element)
      }
    }
    return builder.toString()
  }


  enum class Direction {
    DOWN,
    UP
  }
}