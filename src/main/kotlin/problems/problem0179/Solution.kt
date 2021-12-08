package problems.problem0179

class Solution {
  fun largestNumber(nums: IntArray): String {
    val comparator = Comparator<String> { a, b -> compare(a, b) }
    val numStr = nums.map { it.toString() }.sortedWith(comparator)
    val valueStr = numStr.joinToString("")
    return if (valueStr == "0") valueStr
    else if (valueStr.startsWith("0")) {
      val dropped = valueStr.dropWhile { it == '0'}
      if (dropped.isEmpty()) "0"
      else dropped
    }
    else valueStr
  }

  private fun compare(first: String, second: String): Int {
    val firstSecond = (first + second).toLong()
    val secondFirst = (second + first).toLong()
    return if (firstSecond == secondFirst) 0
    else if (firstSecond > secondFirst) -1
    else 1
  }

}