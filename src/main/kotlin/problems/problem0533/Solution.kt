package problems.problem0533

class Solution {
  fun findBlackPixel(picture: Array<CharArray>, target: Int): Int {
    val rowToCols = mutableMapOf<Int, MutableSet<Int>>()
    val colToRows = mutableMapOf<Int, MutableSet<Int>>()
    for (i in picture.indices) {
      for (j in picture[i].indices) {
        if (picture[i][j] == 'B') {
          rowToCols.getOrPut(i) { mutableSetOf() }.add(j)
          colToRows.getOrPut(j) { mutableSetOf() }.add(i)
        }
      }
    }

    val rowToFrequency = mutableMapOf<MutableSet<Int>, Int>()
    for (entry in rowToCols) {
      rowToFrequency.merge(entry.value, 1) { cur, acc -> cur + acc }
    }

    val validRows =
      rowToFrequency.filter { it.value == target && it.key.size == target }
        .keys
    var total = 0
    for (row in validRows) {
      var matches = 0
      for (col in row) {
        if (colToRows[col]?.size == target) {
          matches++
        }
      }
      total += matches * target
    }
    return total
  }
}
