package problems.problem0244

import java.util.*

class WordDistance(wordsDict: Array<String>) {

  private val wordToIndices: MutableMap<String, TreeSet<Int>> = mutableMapOf()

  init {
    for (index in wordsDict.indices) {
      val word = wordsDict[index]
      val currentIndices = wordToIndices.getOrDefault(word, TreeSet<Int>())
      currentIndices.add(index)
      wordToIndices[word] = currentIndices
    }
  }

  fun shortest(word1: String, word2: String): Int {
    val word1Indices = wordToIndices[word1]!!
    val word2Indices = wordToIndices[word2]!!

    var currentShortest: Int? = null
    for (index in word1Indices) {
      val larger = word2Indices?.higher(index)
      if (larger != null) {
        val difference = larger - index
        if (currentShortest == null || difference < currentShortest)  {
          currentShortest = difference
        }
      }

      val lower = word2Indices?.lower(index)
      if (lower != null) {
        val difference = index - lower
        if (currentShortest == null || difference < currentShortest) {
          currentShortest = difference
        }
      }
    }
    return currentShortest ?: -1
  }

}