package problems.problem1859

class Solution {
  class Solution {
    fun sortSentence(s: String): String {
      return s.split(" ")
        .map { Word(it.substring(0, it.length - 1), Character.getNumericValue(it[it.length - 1])) }
        .sortedBy { it.order }
        .map {it.word}
        .joinToString(" ")

    }
  }

}
data class Word(val word: String, val order: Int)
