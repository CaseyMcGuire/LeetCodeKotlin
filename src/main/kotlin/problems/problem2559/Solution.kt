package problems.problem2559

class Solution {
  fun vowelStrings(words: Array<String>, queries: Array<IntArray>): IntArray {
    val prefixSum = mutableListOf<Int>()
    for (word in words) {
      val runningSum = prefixSum.lastOrNull() ?: 0
      if (word.first().isVowel() && word.last().isVowel()) {
        prefixSum.add(runningSum + 1)
      }
      else {
        prefixSum.add(runningSum)
      }
    }

    val queryResults = mutableListOf<Int>()
    for (query in queries) {
      val from = query[0]
      val to = query[1]
      val result = prefixSum[to] - (prefixSum.getOrNull(from - 1) ?: 0)
      queryResults.add(result)
    }
    return queryResults.toIntArray()
  }

  private fun Char.isVowel(): Boolean {
    return when(this.lowercaseChar()) {
      'a', 'e','i', 'o', 'u' -> true
      else -> false
    }
  }
}