package problems.problem0524

class Solution {
  fun findLongestWord(s: String, dictionary: List<String>): String {
    var longestSoFar: String? = null

    val curCharToWord = mutableMapOf<Char, MutableList<Entry>>()
    for (word in dictionary) {
      curCharToWord.getOrPut(word[0]) { mutableListOf() }.add(Entry(0, word))
    }

    for (c in s) {
      val entries = curCharToWord[c]
        ?: continue
      curCharToWord.remove(c)

      for (entry in entries) {
        if (entry.index == entry.word.length - 1) {
          if (longestSoFar == null ||
            longestSoFar.length < entry.word.length ||
            longestSoFar.length == entry.word.length && longestSoFar > entry.word) {
            longestSoFar = entry.word
          }
        }
        else {
          val nextEntry = Entry(entry.index + 1, entry.word)
          curCharToWord.getOrPut(entry.word[nextEntry.index]) { mutableListOf() }
            .add(nextEntry)
        }
      }
    }
    return longestSoFar ?: ""
  }

  data class Entry(val index: Int, val word: String)
}