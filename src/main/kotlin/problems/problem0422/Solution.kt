package problems.problem0422

class Solution {
  fun validWordSquare(words: List<String>): Boolean {
    if (words.isEmpty()) {
      return true
    }
    val numRows = words.size
    val numColumns = words.sortedByDescending { it.length }[0].length
    val max = Math.max(numRows, numColumns)
    for (i in 0 until max) {
      val colString = getColumnString(words, i)
      val rowString = words[i]
      if (colString != rowString) {
        return false
      }
    }
    return true
  }

  private fun getColumnString(words: List<String>, col: Int): String {
    val builder = StringBuilder()

    var i = 0
    while (i < words.size) {
      val row = words[i]
      if (col >= row.length) {
        break
      }
      builder.append(words[i][col])
      i++
    }
    return builder.toString()
  }

}