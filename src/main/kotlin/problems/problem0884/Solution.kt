package problems.problem0884

class Solution {
  fun uncommonFromSentences(s1: String, s2: String): Array<String> {
    val wordsMap1 = getWordsMap(s1)
    val wordsMap2 = getWordsMap(s2)
    val intersection = wordsMap1.keys.intersect(wordsMap2.keys)
    intersection.forEach {
      wordsMap1.remove(it)
      wordsMap2.remove(it)
    }
    val singleWords1 = wordsMap1.entries.filter { it.value == 1}.map { it.key }.toSet()
    val singleWords2 = wordsMap2.entries.filter { it.value == 1}.map { it.key }.toSet()
    return (singleWords1 + singleWords2).toTypedArray()
  }

  fun getWordsMap(s: String): MutableMap<String, Int> {
    val words = s.split(" ")
    val frequencyMap = mutableMapOf<String, Int>()
    for (word in words) {
      frequencyMap.merge(word, 1) {cur, acc -> cur + acc}
    }
    return frequencyMap
  }
}