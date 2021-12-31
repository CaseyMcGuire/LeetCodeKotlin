package problems.problem0091

class Solution {
  fun numDecodings(s: String): Int {
    if (s.isEmpty()) {
      return 0
    }
    if (s.length == 1) {
      return if (getMapping(s[0].toString()) != null) 1 else 0
    }
    if (s.length > 1 && s[0] == '0') {
      return 0
    }

    val decodings = IntArray(s.length)

    for (i in decodings.size - 1 downTo 0) {
      if (s[i] == '0') {
        decodings[i] = 0
        continue
      }
      val firstMapping = getMapping(s[i].toString())
      if (firstMapping != null) {
        if (i == decodings.size - 1) {
          decodings[i] = 1
          continue
        }
        else {
          decodings[i] = decodings[i + 1]
        }
      }

      val secondMappings = getMapping(s[i].toString() + s[i + 1].toString())
      if (secondMappings != null) {
        if (i == decodings.size - 2) {
          decodings[i] = decodings[i + 1] + 1
        }
        else {
          decodings[i] = decodings[i + 2] + decodings[i + 1]
        }
      }
    }

    return decodings[0]
  }

  private fun getMapping(s: String): String? {
    return when(s) {
      "1" -> "A"
      "2" -> "B"
      "3" -> "C"
      "4" -> "D"
      "5" -> "E"
      "6" -> "F"
      "7" -> "G"
      "8" -> "H"
      "9" -> "I"
      "10" -> "J"
      "11" -> "K"
      "12" -> "L"
      "13" -> "M"
      "14" -> "N"
      "15" -> "O"
      "16" -> "P"
      "17" -> "Q"
      "18" -> "R"
      "19" -> "S"
      "20" -> "T"
      "21" -> "U"
      "22" -> "V"
      "23" -> "W"
      "24" -> "X"
      "25" -> "Y"
      "26" -> "Z"
      else -> null
    }
  }
}