package problems.problem0438

class Solution {
  fun findAnagrams(s: String, p: String): List<Int> {
    val anagram = mutableMapOf<Char, Int>()
    for (c in p) {
      anagram.increment(c)
    }

    val window = mutableMapOf<Char, Int>()
    val windowSize = p.length
    val startIndices = mutableListOf<Int>()
    for (i in s.indices) {
      val c = s[i]
      window.increment(c)
      if (window.totalSize() < windowSize) {
        continue
      }
      val startIndex = i - windowSize + 1
      if (window == anagram) {
        startIndices.add(startIndex)
      }
      window.decrement(s[startIndex])
    }
    return startIndices
  }

  private fun MutableMap<Char, Int>.totalSize(): Int {
    return this.values.sum()
  }

  private fun MutableMap<Char, Int>.increment(c: Char) {
    this.merge(c, 1) { cur, acc -> cur + acc }
  }

  private fun MutableMap<Char, Int>.decrement(c: Char) {
    val curFrequency = this[c] ?: return
    if (curFrequency == 1) {
      this.remove(c)
    }
    else {
      this[c] = curFrequency - 1
    }
  }
}