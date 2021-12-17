package problems.problem0013

class Solution {
  fun romanToInt(s: String): Int {
    var i = 0
    var count = 0
    while (i < s.length) {
      val cur = s[i]
      var incrementTwo = false
      val nextSum = when (cur) {
        'I' -> {
          val subtracted = getPossibleSubtractedValue(s, i, 5, 10)
          incrementTwo = subtracted.second
          subtracted.first
        }
        'X' -> {
          val subtracted = getPossibleSubtractedValue(s, i, 50, 100)
          incrementTwo = subtracted.second
          subtracted.first
        }
        'C' -> {
          val subtracted = getPossibleSubtractedValue(s, i, 500, 1000)
          incrementTwo = subtracted.second
          subtracted.first
        }
        else -> s.romanValueAt(i)
      }

      count = count + nextSum
      if (incrementTwo) {
        i += 2
      }
      else {
        i++
      }
    }

    return count
  }

  private fun getPossibleSubtractedValue(s: String, i: Int, first: Int, second: Int): Pair<Int, Boolean> {
    val value = s.romanValueAt(i)
    if (i < s.length - 1) {
      val next = s.romanValueAt(i + 1)
      if (next == first || next == second) {
        return Pair(next - value, true)
      }
    }
    return Pair(value, false)
  }

  private fun String.romanValueAt(i: Int): Int {
    return when (this[i]) {
      'I' -> 1
      'V' -> 5
      'X' -> 10
      'L' -> 50
      'C' -> 100
      'D' -> 500
      'M' -> 1000
      else -> throw RuntimeException()
    }
  }
}