package problems.problem0245

import java.util.*

class Solution {
  fun shortestWordDistance(wordsDict: Array<String>, word1: String, word2: String): Int {
    val leftWordToIndices = mutableMapOf<String, TreeSet<Int>>()
    val rightWordToIndices = mutableMapOf<String, TreeSet<Int>>()
    for (i in wordsDict.size - 1 downTo 1) {
      val indices = rightWordToIndices[wordsDict[i]] ?: TreeSet()
      indices.add(i)
      rightWordToIndices[wordsDict[i]] = indices
    }

    var smallestSoFar: Int? = null
    for (i in wordsDict.indices) {
      val curWord = wordsDict[i]
      val rightIndices = rightWordToIndices[word2] ?: TreeSet()
      val leftIndices = leftWordToIndices[word2] ?: TreeSet()
      val closestLeftIndex = leftIndices.lower(i)
      val closestRightIndex = rightIndices.higher(i)
      if (curWord == word1) {
        val smallestDistance = if (closestLeftIndex != null && closestRightIndex != null) {
          Math.min(Math.abs(closestLeftIndex - i), Math.abs(closestRightIndex - i))
        }
        else if (closestLeftIndex != null) {
          Math.abs(closestLeftIndex - i)
        }
        else if (closestRightIndex != null) {
          Math.abs(closestRightIndex - i)
        }
        else {
          Integer.MAX_VALUE
        }
        if (smallestSoFar == null || smallestDistance < smallestSoFar) {
          smallestSoFar = smallestDistance
        }
      }
      val curLeftIndices = leftWordToIndices[curWord] ?: TreeSet()
      val curRightIndices = rightWordToIndices[curWord] ?: TreeSet()
      curLeftIndices.add(i)
      curRightIndices.remove(i)
      leftWordToIndices[curWord] = curLeftIndices
      rightWordToIndices[curWord] = curRightIndices
    }
    return smallestSoFar ?: -1
  }
}