package problems.problem1207

class Solution {
  fun uniqueOccurrences(arr: IntArray): Boolean {
    val frequencyMap = mutableMapOf<Int, Int>()
    for (num in arr) {
      frequencyMap.merge(num, 1) { cur, acc -> cur + acc }
    }
    val values = frequencyMap.values
    val deduped = values.toSet()
    return values.size == deduped.size
  }
}