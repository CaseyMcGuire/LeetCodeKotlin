package problems.problem0647

class Solution {
  fun countSubstrings(s: String): Int {
    var total = 0
    val cache = mutableMapOf<Range, Boolean>()
    for (i in s.indices) {
      val p = Range(i, i)
      cache[p] = true
    }

    for (i in 0 until s.length - 1) {
      val p = Range(i, i + 1)
      cache[p] = s[i] == s[i + 1]
    }
    for (i in 3..s.length) {

      for (j in 0 until s.length) {
        val endingIndex = i + j - 1
        if (endingIndex >= s.length) {
          break
        }
        val curPalindrome = Range(j, endingIndex)
        val prevWasPalindrome = cache[Range(j + 1, endingIndex - 1)]!!
        cache[curPalindrome] = prevWasPalindrome && s[j] == s[endingIndex]
      }
    }
    return cache.values.filter { it }.size
  }
}

data class Range(val i: Int, val j: Int)