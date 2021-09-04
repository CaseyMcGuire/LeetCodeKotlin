package problems.problem0451

class Solution {
  fun frequencySort(s: String): String {
    val frequencyMap = mutableMapOf<Char, Int>()
    for (c in s.toCharArray()) {
      frequencyMap.merge(c, 1) {cur, acc -> cur + acc}
    }
    val entries = frequencyMap.entries.sortedBy {it.value}
    val builder = StringBuilder()
    for (entry in entries.reversed()) {
      for (i in 0 until entry.value) {
        builder.append(entry.key)
      }
    }
    return builder.toString()
  }
}