package problems.problem0258

class Solution {
  fun addDigits(num: Int): Int {
    if (num < 10) {
      return num
    }
    val sum = num.toString().map { it.digitToInt() }.sum()
    return addDigits(sum)
  }
}