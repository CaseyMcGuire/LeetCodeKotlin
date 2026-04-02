package problems.problem2645

class Solution {
  fun addMinimum(word: String): Int {
    var i = 0
    var j = 0
    val validWord = "abc"
    var numAdditions = 0
    while (i < word.length) {
      if (word[i] != validWord[j]) {
        numAdditions++
      }
      else {
        i++
      }
      j++
      if (j == validWord.length) {
        j = 0
      }
    }
    if (j != 0) {
      numAdditions += (validWord.length - j)
    }
    return numAdditions
  }
}