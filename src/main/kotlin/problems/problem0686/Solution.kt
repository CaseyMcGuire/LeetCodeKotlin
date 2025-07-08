package problems.problem0686

class Solution {
  fun repeatedStringMatch(a: String, b: String): Int {
    for (startingIndex in a.indices) {
      var numRepeats = 1
      val startingChar = a[startingIndex]
      var i = startingIndex
      for (j in b.indices) {
        if (a[i] != b[j]) {
          break
        }
        else if (j == b.length - 1) {
          return numRepeats
        }

        i++
        if (i == a.length) {
          i = 0
          numRepeats++
        }
      }
    }
    return -1
  }
}