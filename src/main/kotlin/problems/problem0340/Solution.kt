package problems.problem0340

class Solution {
  fun lengthOfLongestSubstringKDistinct(s: String, k: Int): Int {
    if (s.length == 0 || k == 0) {
      return 0
    }
    val frequencyMap = mutableMapOf<Char, Int>()
    var start = 0
    var end = 0
    var currentMaxSoFar = 0
    while (true) {
      if (frequencyMap.size > k) {
        frequencyMap.decrement(s[start])
        start++
      }
      else {

        val currentWindow = end - start
        if (currentWindow > currentMaxSoFar) {
          currentMaxSoFar = currentWindow
        }

        if (end == s.length) {
          break
        }
        frequencyMap.increment(s[end])
        end++
      }
    }
    return currentMaxSoFar
  }


  fun MutableMap<Char,Int>.increment(c: Char) {
    this.merge(c, 1) { cur, acc -> cur + acc }
  }

  fun MutableMap<Char,Int>.decrement(c: Char) {
    val currentFrequency = this[c]
    if (currentFrequency == null) {
      return
    }
    if (currentFrequency == 1) {
      this.remove(c)
    }
    else {
      this[c] = currentFrequency - 1
    }
  }
}