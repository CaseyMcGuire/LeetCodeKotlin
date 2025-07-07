package problems.problem2951

class Solution {
  fun findPeaks(mountain: IntArray): List<Int> {
    val peakIndices = mutableListOf<Int>()
    for (i in 1 until mountain.size - 1) {
      if (mountain[i] > mountain[i - 1] && mountain[i] > mountain[i + 1]) {
        peakIndices.add(i)
      }
    }
    return peakIndices
  }
}