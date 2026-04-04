package problems.problem0916

class Solution {
  fun wordSubsets(words1: Array<String>, words2: Array<String>): List<String> {
    val subsetCharToMinFrequency = mutableMapOf<Char, Int>()
    for (word in words2) {
      for ((key, value) in word.getFrequencyMap()) {
        subsetCharToMinFrequency.merge(key, value) { cur, acc -> Math.max(cur, acc) }
      }
    }

    val universalStrings = mutableListOf<String>()
    for (word in words1) {
      val frequencyMap = word.getFrequencyMap()
      var isUniversal = true
      for ((char, minFrequency) in subsetCharToMinFrequency) {
        val actualFrequency = frequencyMap[char] ?: 0
        if (actualFrequency < minFrequency) {
          isUniversal = false
          break
        }
      }

      if (isUniversal) {
        universalStrings.add(word)
      }
    }

    return universalStrings
  }

  private fun String.getFrequencyMap(): Map<Char, Int> {
    val frequencyMap = mutableMapOf<Char, Int>()
    for (c in this) {
      frequencyMap.merge(c, 1) { cur, acc -> cur + acc }
    }
    return frequencyMap
  }
}