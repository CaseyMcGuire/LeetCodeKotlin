package problems.problem1048

class Solution {
  fun longestStrChain(words: Array<String>): Int {
    val visitedWords = mutableSetOf<String>()
    val groupedWords = getGroupedWords(words)
    fun recurse(word: String): Int {
      if (!visitedWords.add(word)) {
        return 0
      }
      val nextWordLength = word.length + 1
      val nextWords = groupedWords[nextWordLength] ?: return 1
      val successors = nextWords.filter { word.isPredecessor(it) }
      val maxChain = successors.map {recurse(it)}.max() ?: 0
      return maxChain + 1
    }

    return words
      .sortedBy { it.length }
      .map {recurse(it)}.max() ?: 0
  }

  private fun String.isPredecessor(other: String): Boolean {
    if (other.length != this.length + 1) {
      return false
    }
    var foundInsertion = false
    var thisIndex = 0
    var otherIndex = 0
    while (thisIndex < this.length) {
      val areEqual = other[otherIndex] == this[thisIndex]
      if (!areEqual) {
        if (foundInsertion) {
          return false
        }
        else {
          foundInsertion = true
          otherIndex++
        }
      }
      else {
        otherIndex++
        thisIndex++
      }
    }
    return true
  }

  private fun getGroupedWords(words: Array<String>): MutableMap<Int, MutableList<String>> {
    val groupedWords = mutableMapOf<Int, MutableList<String>>()
    for (word in words) {
      groupedWords.merge(word.length, mutableListOf(word)) { cur, acc ->
        cur.addAll(acc)
        cur
      }
    }
    return groupedWords
  }
}

fun main(args: Array<String>) {
  println(Solution().longestStrChain(arrayOf("a","b","ba","bca","bda","bdca")))
}