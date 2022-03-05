package problems.problem0567

class Solution {
  fun checkInclusion(s1: String, s2: String): Boolean {
    val frequencyMap = mutableMapOf<Char, Int>()
    for (c in s1) {
      frequencyMap.merge(c, 1) { cur, acc -> cur + acc }
    }

    val window = mutableMapOf<Char, Int>()
    var windowSize = s1.length
    for (i in s2.indices) {
      window.merge(s2[i], 1) { cur, acc -> cur + acc }
      val toRemove = i - windowSize
      if (toRemove >= 0) {
        window.decrement(s2[toRemove])
      }

      if (window == frequencyMap) {
        return true
      }
    }

    return false
  }

  private fun MutableMap<Char, Int>.decrement(c: Char) {
    val currentFrequency = this[c] ?: return
    if (currentFrequency == 1) {
      this.remove(c)
    }
    else {
      this[c] = currentFrequency - 1
    }
  }
}