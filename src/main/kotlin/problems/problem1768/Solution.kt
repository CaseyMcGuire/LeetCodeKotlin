package problems.problem1768

class Solution {
  fun mergeAlternately(word1: String, word2: String): String {
    val mergedString = StringBuilder()
    var i = 0
    var j = 0
    var first = true
    while (i < word1.length || j < word2.length) {
      if (i >= word1.length) {
        mergedString.append(word2[j])
        j++
      }
      else if (j >= word2.length) {
        mergedString.append(word1[i])
        i++
      }
      else {
        if (first) {
          mergedString.append(word1[i])
          i++
        }
        else {
          mergedString.append(word2[j])
          j++
        }
        first = !first
      }
    }
    return mergedString.toString()
  }
}