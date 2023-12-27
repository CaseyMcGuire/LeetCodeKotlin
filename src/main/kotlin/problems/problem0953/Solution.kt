package problems.problem0953

class Solution {
  fun isAlienSorted(words: Array<String>, order: String): Boolean {
    val newToOldOrder = getNewToOldOrder(order)
    val oldOrderedWords = words.map { s ->
      s.map { newToOldOrder[it] }.joinToString("")
    }
    val oldOrderedWordsSorted = oldOrderedWords.sorted()
    return oldOrderedWords == oldOrderedWordsSorted
  }

  private fun getNewToOldOrder(order: String): Map<Char, Char> {
    val newToOldOrder = mutableMapOf<Char, Char>()
    for ((i, c) in ('a'..'z').withIndex()) {
      newToOldOrder[order[i]] = c
    }
    return newToOldOrder
  }
}