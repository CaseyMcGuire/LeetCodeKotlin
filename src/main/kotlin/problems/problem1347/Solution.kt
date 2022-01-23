package problems.problem1347

class Solution {
  fun minSteps(s: String, t: String): Int {
    val sMap = mutableMapOf<Char, Int>()
    for (c in s) {
      sMap.merge(c, 1) { cur, acc -> cur + acc }
    }
    for (c in t) {
      val frequency = sMap[c]
      if (frequency == null) {
        continue
      }
      if (frequency == 1) {
        sMap.remove(c)
      }
      else {
        sMap[c] = frequency - 1
      }
    }
    return sMap.values.sum()
  }

}