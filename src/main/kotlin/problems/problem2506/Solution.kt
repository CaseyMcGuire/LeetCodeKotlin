package problems.problem2506

class Solution {
  fun similarPairs(words: Array<String>): Int {
    val strs = mutableMapOf<Set<Char>, Int>()
    for (word in words) {
      val charSet = word.toSet()
      strs.merge(charSet, 1) { old, new -> old + new }
    }

    var numPairs = 0
    for (value in strs.values) {
      numPairs += (value - 1).sumUpTo()
    }
    return numPairs
  }

  private fun Int.sumUpTo(): Int {
    if (this < 1) {
      return 0
    }
    var result = 0
    for (i in 1..this) {
      result += i
    }
    return result
  }
}