package problems.problem0008

class Solution {
  fun myAtoi(str: String): Int {
    val trimmedStr = str.trim()
    if (trimmedStr.isEmpty()) {
      return 0
    }
    val isNegative = trimmedStr.startsWith("-")
    val hasStartingSign = trimmedStr.startsWith("-") || trimmedStr.startsWith("+")
    val startIndex = if (hasStartingSign) 1 else 0
    var i = startIndex
    while (i < trimmedStr.length && Character.isDigit(trimmedStr[i])) {
      i++
    }

    val numStr = trimmedStr.substring(startIndex, i)
      .dropWhile { it == '0' }
    if (numStr.isEmpty()) {
      return 0
    }

    val numStrWithSign = if (trimmedStr.startsWith("-")) "-$numStr" else numStr
    val largest = if (numStrWithSign.startsWith("-")) Integer.MIN_VALUE.toString() else Integer.MAX_VALUE.toString()
    if (numStrWithSign.length > largest.length) {
      return Integer.parseInt(largest)
    }
    else if (numStrWithSign.length == largest.length) {
      val sorted = listOf(numStrWithSign, largest).sorted()
      return if (sorted[0] == numStrWithSign) Integer.parseInt(numStrWithSign) else Integer.parseInt(largest)
    }
    else {
      return Integer.parseInt(numStrWithSign)
    }

  }
}