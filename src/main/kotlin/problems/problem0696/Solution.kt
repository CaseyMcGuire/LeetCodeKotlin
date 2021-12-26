package problems.problem0696

class Solution {
  fun countBinarySubstrings(s: String): Int {
    val pairs = findBorders(s)
    var count = 0
    val range = 0 until s.length
    for (pair in pairs) {
      count++
      var i = pair.first - 1
      var j = pair.second + 1
      while (i in range && j in range) {
        val matches = s[i] == s[i + 1] && s[j] == s[j - 1]
        if (matches) {
          count++
        }
        else {
          break
        }
        i--
        j++
      }
    }
    return count
  }

  private fun findBorders(s: String): List<Pair<Int, Int>> {
    val pairs = mutableListOf<Pair<Int, Int>>()
    for (i in 0 until s.length - 1) {
      if (s[i] != s[i + 1]) {
        pairs.add(Pair(i, i + 1))
      }
    }
    return pairs
  }

}