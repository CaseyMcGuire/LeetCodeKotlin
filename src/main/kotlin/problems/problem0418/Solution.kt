package problems.problem0418

class Solution {
  fun wordsTyping(sentence: Array<String>, rows: Int, cols: Int): Int {
    var currentRow = 0
    var i = 0
    var numSentences = 0
    while (currentRow < rows) {
      var lengthOfCurrentRow = cols
      while (lengthOfCurrentRow > 0) {
        val currentWord = sentence[i]
        if (currentWord.length > cols) {
          return 0
        }
        if (lengthOfCurrentRow < currentWord.length) {
          break
        }
        lengthOfCurrentRow = lengthOfCurrentRow - currentWord.length - 1
        i++
        if (i == sentence.size) {
          numSentences++
          i = 0
        }
      }
      currentRow++
    }
    return numSentences
  }
}

fun main(args: Array<String>) {
  println(Solution().wordsTyping(arrayOf("a", "bcd", "e"), 3, 6))
}