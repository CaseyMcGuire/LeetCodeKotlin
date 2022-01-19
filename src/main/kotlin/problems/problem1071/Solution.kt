package problems.problem1071

class Solution {
  fun gcdOfStrings(str1: String, str2: String): String {
    val smaller = if (str1.length < str2.length) str1 else str2
    for (i in smaller.length downTo 1) {
      val substring = smaller.substring(0, i)
      if (str1.isDivisibleBy(substring) && str2.isDivisibleBy(substring)) {
        return substring
      }
    }

    return ""
  }

  private fun String.isDivisibleBy(other: String): Boolean {
    if (this.length % other.length != 0) {
      return false
    }
    var j = 0
    for (i in this.indices) {
      if (this[i] != other[j]) {
        return false
      }
      j++
      if (j == other.length) {
        j = 0
      }
    }
    return true

  }
}