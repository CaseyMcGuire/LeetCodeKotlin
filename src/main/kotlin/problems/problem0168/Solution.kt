package problems.problem0168

class Solution {
  fun convertToTitle(columnNumber: Int): String {
    var sum = columnNumber
    var i = 0
    val title = mutableListOf<Char>()
    val letters = ('A'..'Z').toList()
    while (sum > 0) {
      sum--
      val letterIndex = sum % 26
      title.add(letters[letterIndex])
      sum /= 26
      i++
    }
    return title.reversed().joinToString("")
  }
}