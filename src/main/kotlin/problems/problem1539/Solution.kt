package problems.problem1539

class Solution {
  fun findKthPositive(arr: IntArray, k: Int): Int {
    var totalNumMissing = 0
    for (i in arr.indices) {
      val prev = arr.getOrNull(i - 1) ?: 0
      val cur = arr[i]
      val curNumMissing = cur - prev - 1
      if (totalNumMissing + curNumMissing >= k) {
        return prev + (k - totalNumMissing)
      }
      totalNumMissing += curNumMissing
    }
    return arr.last() + (k - totalNumMissing)
  }
}