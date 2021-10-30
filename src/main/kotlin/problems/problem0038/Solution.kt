package problems.problem0038

class Solution {
  fun countAndSay(n: Int): String {
    if (n == 1) {
      return "1"
    }
    else if (n == 2) {
      return "11"
    }
    var curSequence = "11"
    for (i in 3..n) {
      curSequence = groupString(curSequence)
    }
    return curSequence
  }

  private fun groupString(str: String): String {

    var curChar = str[0]
    var curFrequency = 1
    val grouped = mutableListOf<String>()
    for (i in 1 until str.length) {
      val nextChar = str[i]
      if (nextChar != curChar) {
        grouped.add(curFrequency.toString())
        grouped.add(curChar.toString())
        curChar = str[i]
        curFrequency = 1
      }
      else {
        curFrequency++
      }

      if (i == str.length - 1) {
        grouped.add(curFrequency.toString())
        grouped.add(curChar.toString())
      }
    }
    return grouped.joinToString("")
  }
}