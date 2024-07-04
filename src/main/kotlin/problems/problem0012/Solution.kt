package problems.problem0012

class Solution {

  companion object {
    val ROMAN_NUMERALS = listOf(
      RomanNumeral('M', 1000),
      RomanNumeral('D', 500),
      RomanNumeral('C', 100),
      RomanNumeral('L', 50),
      RomanNumeral('X', 10),
      RomanNumeral('V', 5),
      RomanNumeral('I', 1)
    )
  }

  fun intToRoman(num: Int): String {
    val builder = StringBuilder()
    var curNum = num
    for (i in 0 until ROMAN_NUMERALS.size step 2) {
      val numeral = ROMAN_NUMERALS[i]
      val factor = curNum / numeral.value
      curNum -= (numeral.value * factor)
      if (i == 0) {
        for (j in 0 until factor) {
          builder.append(numeral.c)
        }
        continue
      }

      val fiveMultipleNumeral = ROMAN_NUMERALS[i - 1]
      val prevNumeral = ROMAN_NUMERALS[i - 2]
      if (factor == 9) {
        builder.append(numeral.c)
        builder.append(prevNumeral.c)
      }
      else if (factor in 5..9) {
        val remainder = factor - 5
        builder.append(fiveMultipleNumeral.c)
        for (j in 0 until remainder) {
          builder.append(numeral.c)
        }
      }
      else if (factor == 4) {
        builder.append(numeral.c)
        builder.append(fiveMultipleNumeral.c)
      }
      else {
        for (j in 0 until factor) {
          builder.append(numeral.c)
        }
      }
    }
    return builder.toString()
  }
}

data class RomanNumeral(val c: Char, val value: Int)