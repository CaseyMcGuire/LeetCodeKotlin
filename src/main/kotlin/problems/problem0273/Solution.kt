package problems.problem0273

class Solution {
  fun numberToWords(num: Int): String {
    if (num == 0) {
      return "Zero"
    }
    val words = mutableListOf<String>()
    val numStr = num.toString()
    var i = numStr.length - 1
    for (suffix in NumberSuffix.values()) {
      val builder = StringBuilder()
      for (j in i downTo i - 2) {
        if (j in numStr.indices) {
          builder.append(numStr[j])
        }
        else {
          builder.append('0')
        }
      }
      val word = builder.reversed().toString()
      i -= 3;
      if (word == "000") {
        continue
      }
      val numWord = getNumberWord(word, suffix).let {
        if (suffix == NumberSuffix.HUNDRED) {
          it
        }
        else {
          "$it ${suffix.word}"
        }
      }

      words.add(numWord)
    }

    return words.reversed().filter { it.isNotEmpty() }.joinToString(" ")
  }



  fun getNumberWord(numStr: String, suffix: NumberSuffix): String {
    if (numStr.length > 3) {
      throw IllegalArgumentException("`num` must not be larger than 3 characters. num: $numStr")
    }

    val numInt = numStr.substring(1, 3).toInt()
    val suffixWord = suffix.word
    val firstTwoDigits = if (numInt in 10..90 step 10) {
      "${numberWord(numInt)}"
    }
    else if (numInt in 0..19) {
      numberWord(numInt)
    }
    else {
      val doubleDigit = "${numStr[1]}0".toInt()
      val singleDigit = "${numStr[2]}".toInt()
      (numberWord(doubleDigit) + " " + numberWord(singleDigit)).trim()
    }

    if (numStr[0] == '0') {
      return "$firstTwoDigits"
    }

    val thirdDigit = numberWord(numStr[0].digitToInt())
    if (thirdDigit.isEmpty()) {
      return firstTwoDigits
    }
    return "$thirdDigit ${NumberSuffix.HUNDRED.word} $firstTwoDigits".trim()
  }

  fun numberWord(num: Int): String {
    return when (num) {
      0 -> ""
      1 -> "One"
      2 -> "Two"
      3 -> "Three"
      4 -> "Four"
      5 -> "Five"
      6 -> "Six"
      7 -> "Seven"
      8 -> "Eight"
      9 -> "Nine"
      10 -> "Ten"
      11 -> "Eleven"
      12 -> "Twelve"
      13 -> "Thirteen"
      14 -> "Fourteen"
      15 -> "Fifteen"
      16 -> "Sixteen"
      17 -> "Seventeen"
      18 -> "Eighteen"
      19 -> "Nineteen"
      20 -> "Twenty"
      30 -> "Thirty"
      40 -> "Forty"
      50 -> "Fifty"
      60 -> "Sixty"
      70 -> "Seventy"
      80 -> "Eighty"
      90 -> "Ninety"
      else -> ""
    }
  }

}

enum class NumberSuffix(val word: String) {
  HUNDRED("Hundred"),
  THOUSAND("Thousand"),
  MILLION("Million"),
  BILLION("Billion")
}