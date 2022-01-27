package problems.problem1160

class Solution {
  fun countCharacters(words: Array<String>, chars: String): Int {
    var sumOfLengths = 0
    val charMap = createFrequencyMap(chars)
    for (word in words) {
      val wordMap = createFrequencyMap(word)

      var isGood = true
      for (entry in wordMap) {
        val charFrequency = charMap[entry.key]
        if (charFrequency == null || charFrequency < entry.value) {
          isGood = false
          break
        }
      }
      if (isGood) {
        sumOfLengths += word.length
      }
    }
    return sumOfLengths
  }

  private fun createFrequencyMap(s: String): Map<Char, Int> {
    val map = mutableMapOf<Char, Int>()
    for (c in s) {
      map.merge(c, 1) { cur, acc -> cur + acc }
    }
    return map
  }
}