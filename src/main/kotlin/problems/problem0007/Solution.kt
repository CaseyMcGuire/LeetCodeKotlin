package problems.problem0007

class Solution {
  fun reverse(x: Int): Int {
    if (x == Integer.MIN_VALUE) {
      return 0
    }
    val isNegative = x < 0;
    val xStr =
      if (isNegative)
        x.toString().substring(1, x.toString().length)
      else
        x.toString()
    val reversedNumStr =
      if (isNegative)
        "-" + xStr.reversed()
      else
        xStr.reversed()
    return reversedNumStr.toIntOrNull() ?: 0
  }
}

