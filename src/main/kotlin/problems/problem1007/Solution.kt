package problems.problem1007

class Solution {
  fun minDominoRotations(tops: IntArray, bottoms: IntArray): Int {
    var minSwaps = -1
    fun recurse(i: Int, numSwaps: Int, elem: Int, arr: IntArray, other: IntArray) {
      if (minSwaps != -1 && numSwaps > minSwaps) {
        return
      }
      if (i == tops.size) {
        if (minSwaps == -1 || minSwaps != -1 && minSwaps > numSwaps) {
          minSwaps = numSwaps
        }
        return
      }
      if (elem == arr[i]) {
        recurse(i + 1, numSwaps, elem, arr, other)
      }
      else if (elem == other[i]) {
        recurse(i + 1, numSwaps + 1, elem, arr, other)
      }
    }
    recurse(1, 0, tops[0], tops, bottoms)
    recurse(1, 1, bottoms[0], tops, bottoms)
    recurse(1, 0, bottoms[0], bottoms, tops)
    recurse(1, 1, tops[0], bottoms, tops)
    return minSwaps
  }
}