package problems.problem0166

class Solution {
  fun fractionToDecimal(numerator: Int, denominator: Int): String {
    if (numerator == 0) {
      return "0"
    }
    val shouldBeNegative = numerator < 0 && denominator >= 0 || denominator < 0 && numerator >= 0
    return fractionToDecimalLong(Math.abs(numerator.toLong()), Math.abs(denominator.toLong()), shouldBeNegative)
  }

  private fun fractionToDecimalLong(numerator: Long, denominator: Long, shouldBeNegative: Boolean): String {
    val wholeResult = StringBuilder()
    var numeratorChars = StringBuilder()
    for (c in numerator.toString()) {
      numeratorChars.append(c)
      val curNumerator = numeratorChars.toLong()
      val divisionResult = divide(curNumerator, denominator)

      wholeResult.append(divisionResult.result)

      numeratorChars = StringBuilder()
      numeratorChars.append(divisionResult.remainder)
    }

    if (numeratorChars.isEmpty() || numeratorChars.toLong() == 0L) {
      val withoutZeroes = wholeResult.getWithoutLeadingZeroes()
      return if (shouldBeNegative) "-${withoutZeroes}" else withoutZeroes
    }

    // now we need to calculate the decimal
    val decimalResult = StringBuilder()
    val remainderToLastIndex = mutableMapOf<DivisionResult, Int>()
    while (numeratorChars.toLong() != 0L) {
      numeratorChars.append('0')
      val divisionResult = divide(numeratorChars.toLong(), denominator)
      val previousIndex = remainderToLastIndex[divisionResult]

      if (previousIndex != null) {
        val decimalResultStr = decimalResult.toString()
        val decimalResultNonRepeating = decimalResultStr.substring(0, previousIndex)
        val decimalResultRepeating = decimalResultStr.substring(previousIndex, decimalResultStr.length)
        val repeatingDecimalResult = "${decimalResultNonRepeating}(${decimalResultRepeating})"
        val wholeNum = "${wholeResult.getWithoutLeadingZeroes()}.${repeatingDecimalResult}"
        return if (shouldBeNegative) "-${wholeNum}" else wholeNum
      }
      else if (divisionResult.result != 0L) {
        remainderToLastIndex[divisionResult] = decimalResult.length
      }
      decimalResult.append(divisionResult.result)
      numeratorChars = StringBuilder()
      numeratorChars.append(divisionResult.remainder)
    }

    val wholeLong = wholeResult.getWithoutLeadingZeroes()
    return if (shouldBeNegative) "-${wholeLong}.${decimalResult}" else "${wholeLong}.${decimalResult}"
  }

  private fun divide(numerator: Long, denominator: Long): DivisionResult {
    val result = numerator / denominator
    val amountRemoved = denominator * result
    return DivisionResult(result, numerator - amountRemoved)
  }

  private fun StringBuilder.getWithoutLeadingZeroes(): String {
    var i = 0
    while (i < this.length && this[i] == '0') {
      i++
    }
    if (i == this.length) {
      return "0"
    }
    return this.toString().substring(i, this.length)
  }

  private fun StringBuilder.toLong(): Long {
    return this.toString().toLong()
  }

  private fun MutableList<Char>.toLong(): Long {
    return this.joinToString("").toLong()
  }
}

data class DivisionResult(val result: Long, val remainder: Long)