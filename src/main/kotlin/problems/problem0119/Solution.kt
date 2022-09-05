package problems.problem0119

class Solution {
  fun getRow(rowIndex: Int): List<Int> {
    if (rowIndex == 0) {
      return listOf(1)
    }
    else if (rowIndex == 1) {
      return listOf(1,1)
    }
    var curRowIndex = 2
    var curRow = listOf(1,2,1)

    while (curRowIndex < rowIndex) {
      var nextRow = mutableListOf<Int>()
      for (i in 0 until curRow.size + 1) {
        if (i == 0) {
          nextRow.add(1)
        }
        else if (i == curRow.size) {
          nextRow.add(1)
        }
        else {
          nextRow.add(curRow[i - 1] + curRow[i])
        }
      }
      curRow = nextRow
      curRowIndex++
    }
    return curRow
  }
}