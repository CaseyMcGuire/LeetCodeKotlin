package problems.problem1258

import java.util.*

class Solution {
  fun generateSentences(synonyms: List<List<String>>, text: String): List<String> {
    val possibleWords = getAllPossibleWords(synonyms)
    val sentences = mutableListOf<String>()
    val textList = text.split(" ")
    val currentSentence = LinkedList<String>()
    fun recurse(index: Int) {
      if (index == textList.size) {
        sentences.add(currentSentence.joinToString(" "))
        return
      }
      val curWord = textList[index]
      val replacements = possibleWords[curWord]
      if (replacements == null) {
        currentSentence.addLast(curWord)
        recurse(index + 1)
        currentSentence.removeLast()
      }
      else {
        for (replacement in replacements) {
          currentSentence.addLast(replacement)
          recurse(index + 1)
          currentSentence.removeLast()
        }
      }
    }
    recurse(0)
    return sentences.sorted()
  }

  private fun getAllPossibleWords(synonyms: List<List<String>>): MutableMap<String, Set<String>> {
    val allSynonyms = mutableMapOf<String, Set<String>>()
    val sMap = getSynonymMap(synonyms)
    fun dfs(curWord: String, visited: MutableSet<String>){
      if (!visited.add(curWord)) {
        return
      }
      val synonyms = sMap[curWord]
      if (synonyms == null) {
        return
      }
      for (synonym in synonyms) {
        dfs(synonym, visited)
      }
    }
    for (key in sMap.keys) {
      if (allSynonyms.containsKey(key)) {
        continue
      }
      val set = mutableSetOf<String>()
      dfs(key, set)
      for (word in set) {
        allSynonyms[word] = set
      }
    }
    return allSynonyms
  }

  private fun getSynonymMap(synonyms: List<List<String>>): Map<String, MutableSet<String>> {
    val map = mutableMapOf<String, MutableSet<String>>()
    for (synonym in synonyms) {
      val first = synonym[0]
      val second = synonym[1]
      val set1 = map[first] ?: mutableSetOf()
      val set2 = map[second] ?: mutableSetOf()
      set1.add(second)
      set2.add(first)
      val newSet = (set1 union set2).toMutableSet()
      map[first] = newSet
      map[second] = newSet
    }
    return map
  }
}