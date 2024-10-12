package problems.problem0243

class Solution {
  fun shortestDistance(wordsDict: Array<String>, word1: String, word2: String): Int {
    var firstIndex: Int? = null
    var secondIndex: Int? = null
    var shortestSoFar: Int? = null

    fun setShortest(num: Int) {
      if (shortestSoFar == null || shortestSoFar!! > num) {
        shortestSoFar = num
      }
    }

    for (i in wordsDict.indices) {
      val word = wordsDict[i]
      if (word == word1) {
        secondIndex?.let {
          setShortest(i - it)
        }
        firstIndex = i
      }
      else if (word == word2) {
        firstIndex?.let {
          setShortest(i - it)
        }
        secondIndex = i
      }
    }
    return shortestSoFar ?: -1
  }
}