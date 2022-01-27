package problems.problem0819

class Solution {
  fun mostCommonWord(paragraph: String, banned: Array<String>): String {
    val bannedSet = banned.toSet()
    val words = getWordsFromParagraph(paragraph)
    val nonBannedWords = words.filter { !bannedSet.contains(it) }


    val frequencyMap = mutableMapOf<String, Int>()
    var wordFrequency = 0
    var mostFrequentWord = ""
    for (word in nonBannedWords) {
      frequencyMap.merge(word, 1) { cur, acc -> cur + acc }
      val curFrequency = frequencyMap[word]!!
      if (curFrequency > wordFrequency) {
        wordFrequency = curFrequency
        mostFrequentWord = word
      }
    }
    return mostFrequentWord
  }

  private fun getWordsFromParagraph(paragraph: String): List<String> {
    val list = mutableListOf<String>()
    var curWord = StringBuilder()
    for (c in paragraph) {
      if (Character.isLetterOrDigit(c)) {
        curWord.append(c)
      }
      else {
        if (curWord.isNotEmpty()) {
          list.add(curWord.toString().toLowerCase())
          curWord = StringBuilder()
        }
      }
    }
    if (curWord.isNotEmpty()) {
      list.add(curWord.toString().toLowerCase())
    }
    return list
  }

}