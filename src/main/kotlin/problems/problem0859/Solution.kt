package problems.problem0859

class Solution {
  fun buddyStrings(s: String, goal: String): Boolean {
    if (s.length != goal.length) {
      return false
    }
    val frequencyMap = mutableMapOf<Char, Int>()
    val nonMatchingIndices = mutableListOf<Int>()
    for (i in s.indices) {
      frequencyMap.merge(s[i], 1) { cur, acc -> cur + acc }
      if (s[i] != goal[i]) {
        nonMatchingIndices.add(i)
      }
    }

    if (nonMatchingIndices.size == 0) {
      for (value in frequencyMap.values) {
        if (value > 1) {
          return true
        }
      }
      return false
    }
    else if (nonMatchingIndices.size == 2) {
      val first = nonMatchingIndices[0]
      val second = nonMatchingIndices[1]
      return goal[first] == s[second] && goal[second] == s[first]
    }
    return false
  }
}