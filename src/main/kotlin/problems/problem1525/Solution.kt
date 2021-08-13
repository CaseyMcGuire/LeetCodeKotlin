package problems.problem1525

class Solution {
  fun numSplits(s: String): Int {
    val leftWindow = mutableSetOf<Char>()
    val rightWindow = s.toCharArray()
      .toTypedArray()
      .groupingBy { it }
      .eachCount()
      .toMutableMap()

    var numMatches = 0
    for (c in s.toCharArray()) {
      leftWindow.add(c)
      val frequency = rightWindow[c]
      if (frequency == 1) {
        rightWindow.remove(c)
      }
      else {
        rightWindow[c] = frequency!! - 1
      }

      if (leftWindow.size == rightWindow.size) {
        numMatches++
      }
    }
    return numMatches

  }
}