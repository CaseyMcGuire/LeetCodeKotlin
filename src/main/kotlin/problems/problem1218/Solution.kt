package problems.problem1218

class Solution {
  fun longestSubsequence(arr: IntArray, difference: Int): Int {
    if (arr.isEmpty()) {
      return 0
    }
    val numToSequenceLength = mutableMapOf<Int, Int>()
    var longest = 1
    for (i in arr.size - 1 downTo 0) {
      val remainder = arr[i] + difference
      val sequenceLength = (numToSequenceLength[remainder] ?: 0) + 1
      numToSequenceLength[arr[i]] = sequenceLength
      if (sequenceLength > longest) {
        longest = sequenceLength
      }
    }
    return longest

  }
}