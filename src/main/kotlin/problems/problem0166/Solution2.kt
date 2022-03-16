package problems.problem0166

class Solution2 {
  fun fractionToDecimal(numerator: Int, denominator: Int): String {
    if (numerator == 0) {
      return "0"
    }
    val shouldBeNegative = numerator < 0 && denominator >= 0 || denominator < 0 && numerator >= 0
    return fractionToDecimalLong(Math.abs(numerator.toLong()), Math.abs(denominator.toLong()), shouldBeNegative)
  }

  private fun fractionToDecimalLong(numerator: Long, denominator: Long, shouldBeNegative: Boolean): String {
    val decimal = StringBuilder()
    if (shouldBeNegative) {
      decimal.append("-")
    }
    val wholePart = numerator / denominator
    decimal.append(wholePart)
    var remainder = numerator % denominator
    if (remainder == 0L) {
      return decimal.toString()
    }

    remainder *= 10L

    decimal.append(".")
    val remainderToLastSeenIndex = mutableMapOf<Long, Int>()
    while (remainder > 0) {
      val lastSeenIndex = remainderToLastSeenIndex[remainder]
      if (lastSeenIndex != null) {
        decimal.insert(lastSeenIndex, "(")
        decimal.append(")")
        break
      }
      remainderToLastSeenIndex[remainder] = decimal.length
      val result = remainder / denominator
      decimal.append(result)
      remainder = remainder % denominator
      remainder *= 10L
    }

    return decimal.toString()
  }
}