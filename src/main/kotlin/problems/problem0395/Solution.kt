package problems.problem0395

class Solution {
  fun longestSubstring(s: String, k: Int): Int {

    fun longestSubstring(start: Int, end: Int): Int {
      if (start >= end) {
        return 0
      }
      val charToFrequency = mutableMapOf<Char, Int>()
      val charToIndices = mutableMapOf<Char, MutableList<Int>>()
      for (i in start until end) {
        charToFrequency.merge(s[i], 1) { prev, acc -> prev + acc }
        val prevIndices = charToIndices[s[i]] ?: mutableListOf<Int>()
        prevIndices.add(i)
        charToIndices[s[i]] = prevIndices
      }


      val charsOverK = mutableListOf<Char>()
      for (entry in charToFrequency.entries) {
        val char = entry.key
        val frequency = entry.value

        if (frequency >= k) {
          charsOverK.add(char)
        }

      }

      if (charsOverK.size == charToFrequency.size) {
        return end - start
      }
      if (charsOverK.isEmpty()) {
        return 0
      }

      val validIndices = mutableListOf<Int>()
      for (c in charsOverK) {
        validIndices.addAll(charToIndices[c] ?: emptyList())
      }
      validIndices.sort()

      val ranges = mutableListOf<MutableList<Int>>()
      var currentRange = mutableListOf<Int>()
      ranges.add(currentRange)
      for (index in validIndices) {
        if (currentRange.isEmpty()) {
          currentRange.add(index)
        }
        else if (currentRange.last() == index - 1) {
          currentRange.add(index)
        }
        else {
          currentRange = mutableListOf<Int>(index)
          ranges.add(currentRange)
        }
      }

      val longestPerSubstring = mutableListOf<Int>()
      for (range in ranges) {
        longestPerSubstring.add(longestSubstring(range[0], range.last() + 1))
      }

      return longestPerSubstring.max() ?: 0
    }

    return longestSubstring(0, s.length)
  }


}