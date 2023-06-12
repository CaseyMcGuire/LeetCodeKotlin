package problems.problem0383

class Solution {
  fun canConstruct(ransomNote: String, magazine: String): Boolean {
    val frequencyMap = mutableMapOf<Char, Int>()
    for (c in magazine) {
      frequencyMap.merge(c, 1) {cur, acc -> cur + acc}
    }

    for (c in ransomNote) {
      val frequency = frequencyMap[c]
      if (frequency == null || frequency == 0) {
        return false
      }
      frequencyMap[c] = frequency - 1
    }
    return true
  }
}