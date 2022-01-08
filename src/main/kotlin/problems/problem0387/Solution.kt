package problems.problem0387

class Solution {
  fun firstUniqChar(s: String): Int {
    val frequencyMap = mutableMapOf<Char, Int>()
    for (c in s) {
      frequencyMap.merge(c, 1) { cur, acc -> cur + acc }
    }

    for (i in s.indices) {
      val c = s[i]
      if (frequencyMap[c] == 1) {
        return i
      }
    }
    return -1
  }
}