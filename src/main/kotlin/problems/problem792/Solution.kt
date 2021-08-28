package problems.problem792

class Solution {
  fun numMatchingSubseq(s: String, words: Array<String>): Int {
    return words.filter { it.isSubsequence(s) }.size
  }

  private fun String.isSubsequence(word: String): Boolean {
    if (this.length > word.length) {
      return false
    }
    var i = 0
    var j = 0
    while (j < word.length) {
      if (this[i] == word[j]) {
        i++
        if (i == this.length) {
          return true
        }
      }
      j++
    }
    return false
  }
}