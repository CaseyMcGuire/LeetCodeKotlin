package problems.problem1400

class Solution {
  fun canConstruct(s: String, k: Int): Boolean {
    val frequency = mutableMapOf<Char, Int>()
    for (c in s) {
      frequency.merge(c, 1) { cur, acc -> cur + acc }
    }
    var numDoubles = 0
    var numSingles = 0

    for (entry in frequency.entries) {
      numSingles += entry.value % 2
      numDoubles += entry.value / 2
    }

    if (numSingles > k) {
      return false
    }

    var remaining = k - numSingles
    return 2 * numDoubles >= remaining
  }
}