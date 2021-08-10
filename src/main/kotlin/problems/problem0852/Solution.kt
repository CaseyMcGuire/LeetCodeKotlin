package problems.problem0852

class Solution {
  fun peakIndexInMountainArray(arr: IntArray): Int {
    var largestSoFar = 0
    arr.forEachIndexed { index, num ->
      if (arr[largestSoFar] < num) largestSoFar = index
    }
    return largestSoFar
  }
}