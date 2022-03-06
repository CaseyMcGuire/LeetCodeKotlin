package problems.problem0336

class Solution {
  // O(N * k^2) where N is the length of the array and k is the length of the longest string
  // This is a difficult problem... Even this solution times out sometimes
  fun palindromePairs(words: Array<String>): List<List<Int>> {
    val wordsToIndex = mutableMapOf<String, Int>()
    val pairs = mutableSetOf<List<Int>>()
    for (i in words.indices) {
      val word = words[i]
      wordsToIndex[word] = i
    }
    for (i in words.indices) {
      val word = words[i]
      if (word.isBlank()) {
        for (entry in wordsToIndex) {
          if (entry.key != "" && entry.key.isPalindrome()) {
            pairs.add(listOf(i,entry.value))
            pairs.add(listOf(entry.value, i))
          }
        }
        continue
      }

      val reverse = word.reversed()
      val reverseIndex = wordsToIndex[reverse]
      if (reverseIndex != null && reverse != word) {
        pairs.add(listOf(i, reverseIndex))
      }

      val prefixes = getPrefixesWhereSuffixIsPalindrome(word)
      for (prefix in prefixes) {
        val reversedPrefix = prefix.reversed()
        val reversedPrefixIndex = wordsToIndex[reversedPrefix]
        if (reversedPrefixIndex != null && reversedPrefixIndex != i) {
          pairs.add(listOf(i, reversedPrefixIndex))
        }
      }


      val suffixes = getSuffixesWherePrefixIsPalindrome(word)
      for (suffix in suffixes) {
        val reversedSuffix = suffix.reversed()
        val reversedSuffixIndex = wordsToIndex[reversedSuffix]
        if (reversedSuffixIndex != null && reversedSuffixIndex != i) {
          pairs.add(listOf(reversedSuffixIndex, i))
        }
      }
    }

    return pairs.toList()
  }


  private fun getPrefixesWhereSuffixIsPalindrome(word: String): List<String> {
    val prefixes = mutableListOf<String>()
    for (i in 0..word.length) {
      if (word.isPalindromeBetween(i, word.length)) {
        prefixes.add(word.substring(0, i))
      }
    }
    return prefixes
  }

  private fun getSuffixesWherePrefixIsPalindrome(word: String): List<String> {
    val suffixes = mutableListOf<String>()
    for (i in 0..word.length) {
      if (word.isPalindromeBetween(0, i)) {
        suffixes.add(word.substring(i, word.length))
      }
    }
    return suffixes
  }

  private fun String.isPalindromeBetween(start: Int, length: Int): Boolean {
    var i = start
    var j = length - 1
    while (i < j) {
      if (this[i] != this[j]) {
        return false
      }
      i++
      j--
    }
    return true
  }

  private fun String.isPalindrome(): Boolean {
    var i = 0
    var j = this.length - 1
    while (i < j) {
      if (this[i] != this[j]) {
        return false
      }
      i++
      j--
    }
    return true
  }
}