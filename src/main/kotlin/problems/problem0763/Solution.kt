package problems.problem0763

class Solution {
  fun partitionLabels(s: String): List<Int> {

    val charToLastOccurrence = mutableMapOf<Char, Int>()
    for (i in s.indices) {
      charToLastOccurrence[s[i]] = i
    }
    val sizes = mutableListOf<Int>()
    var lastIndex = -1
    var prevIndex = 0
    for (i in s.indices) {
      if (lastIndex == -1) {
        val lastOccurrence = charToLastOccurrence[s[i]]!!
        if (lastOccurrence == i) {
          sizes.add(1)
        }
        else {
          lastIndex = lastOccurrence
          prevIndex = i
        }
      }
      else if (i == lastIndex) {
        sizes.add(lastIndex - prevIndex + 1)
        lastIndex = -1
      }
      else {
        val lastOccurrence = charToLastOccurrence[s[i]]!!
        if (lastOccurrence > lastIndex) {
          lastIndex = lastOccurrence
        }
      }
    }
    return sizes
  }
}