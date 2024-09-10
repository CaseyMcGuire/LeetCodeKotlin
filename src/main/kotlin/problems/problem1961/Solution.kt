package problems.problem1961

class Solution {
  fun isPrefixString(s: String, words: Array<String>): Boolean {
    if (s.isEmpty()) {
      return false
    }

    var i = 0
    var j = 0
    var k = 0
    while (true) {
      if (k == s.length) {
        return j == 0
      }
      if (i == words.size) {
        return false
      }
      val curChar = s[k]
      val otherChar = words[i][j]
      if (curChar != otherChar) {
        return false
      }
      j++
      if (words[i].length == j) {
        i++
        j = 0
      }

      k++
    }
  }
}