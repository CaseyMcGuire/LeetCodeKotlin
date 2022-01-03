package problems.problem0065

class Solution {
  fun isNumber(s: String): Boolean {
    if (s.isEmpty() || s == ".") {
      return false
    }
    var i = 0
    val decimalComponents = s.split(".")
    if (decimalComponents.size > 2) {
      return false
    }
    val numEs = s.filter { it.isE() }.length
    if (numEs > 1) {
      return false
    }
    if (decimalComponents.size == 2) {
      val first = decimalComponents[0]
      val isFirstNumber = decimalComponents[0].isValidNum()
      val firstEIndex = decimalComponents[0].indexOfFirst { it.isE() }
      val isFirstValid = first.isEmpty() || first == "+" || first == "-" || isFirstNumber
      if (!isFirstValid) {
        return false
      }
      if (firstEIndex != -1) {
        return false
      }
      val eIndex = decimalComponents[1].indexOfFirst { it.isE() }


      if (eIndex != -1) {
        if (eIndex == 0) {
          if (!isFirstNumber) {
            return false
          }
          else if (!decimalComponents[1].substring(1, decimalComponents[1].length).isValidNum()) {
            return false
          }
        }
        else {
          val first = decimalComponents[1].substring(0, eIndex)
          val second = decimalComponents[1].substring(eIndex + 1, decimalComponents[1].length)

          val firstIsValid = first.isValidNum()
          val secondIsValid = second.isValidNum()
          val isValid = firstIsValid && secondIsValid
          if (!isValid) {
            return false
          }
        }
      }
      else {
        if (decimalComponents[1].contains('+') || decimalComponents[1].contains('-')) {
          return false
        }
        return (isFirstNumber && decimalComponents[1].isEmpty()) || decimalComponents[1].isValidNum()
      }

      return true
    }
    else {
      return isValidEnum(s)
    }
  }

  private fun isValidEnum(num: String): Boolean {
    val numEs = num.filter { it.isE() }.length
    if (numEs > 1) {
      return false
    }
    if (numEs == 1) {
      var split = num.split("e")
      if (split.size == 1) {
        split = num.split("E")
      }
      val firstIsValid = split[0].toLongOrNull() != null
      val secondIsValid = split[1].toLongOrNull() != null
      return firstIsValid && secondIsValid
    }
    return num.toLongOrNull() != null
  }

  private fun String.isValidNum(): Boolean {
    var start = 0
    if (this.isEmpty()) {
      return false
    }
    if (this[0] == '+' || this[0] == '-') {
      start++
    }
    var containsOneDigit = false
    for (i in start until this.length) {

      if (!Character.isDigit(this[i])) {
        return false
      }
      containsOneDigit = true
    }
    return containsOneDigit
  }

  private fun Char.isE(): Boolean {
    return this == 'e' || this == 'E'
  }
}

fun main(args: Array<String>) {
  //println(Solution().isNumber("+.8"))
  //println(!Solution().isNumber("+."))
  println(Solution().isNumber("46.e3"))
  println(!Solution().isNumber(".e1"))
  println(Solution().isNumber(".0e7"))
  println(!Solution().isNumber("6e6.5"))
  println(Solution().isNumber("3."))
  println(!Solution().isNumber("i.1"))
  println(!Solution().isNumber(".-4"))
  println(Solution().isNumber("32.e-80123"))
  println(Solution().isNumber("5897972791"))
  println(Solution().isNumber(".65640791247040"))
}