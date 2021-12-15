package problems.problem1051

class Solution {
  fun heightChecker(heights: IntArray): Int {
    val expected = heights.sorted()
    var count = 0
    for (i in heights.indices) {
      if (heights[i] != expected[i]) {
        count++
      }
    }
    return count
  }
}