package problems.problem1016

class Solution {
  fun queryString(s: String, n: Int): Boolean {
    val longest = n.toTruncatedBinaryString().length
    val substrings = getSubstrings(s, longest)

    for (i in 1..n) {
      if (!substrings.contains(i.toTruncatedBinaryString())) {
        return false
      }
    }
    return true
  }

  private fun Int.toTruncatedBinaryString(): String {
    val str = Integer.toBinaryString(this)
    var indexOfOne = 0
    while (indexOfOne > 0 && str[indexOfOne] != '1') {
      indexOfOne++
    }
    if (indexOfOne == -1) {
      return "0"
    }
    return str.substring(indexOfOne, str.length)
  }

  private fun getSubstrings(s: String, largestWindow: Int): Set<String> {
    val substrings = mutableSetOf<String>()
    for (windowSize in largestWindow downTo 1) {
      for (i in 0 until s.length) {
        val end = i + windowSize
        if (end > s.length) {
          break
        }
        substrings.add(s.substring(i, end))
      }
    }
    return substrings
  }
}