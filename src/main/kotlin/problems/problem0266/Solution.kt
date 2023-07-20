package problems.problem0266

class Solution {
  fun canPermutePalindrome(s: String): Boolean {
    val charToFrequency = mutableMapOf<Char, Int>()
    s.forEach { c -> charToFrequency.merge(c, 1) { cur, acc -> cur + acc } }
    val oddChars = charToFrequency.values.filter { it % 2 == 1 }
    return oddChars.size == 1 || oddChars.size == 0
  }
}