package problems.problem0978

class Solution {
  fun maxTurbulenceSize(arr: IntArray): Int {
    if (arr.isEmpty()) {
      return 0
    }
    if (arr.size == 1) {
      return 1
    }

    var maxSoFar = 1
    var curLength = 1
    for (i in 0 until arr.size - 1) {
      val prev = arr.getOrNull(i - 1)
      val cur = arr[i]
      var next = arr[i + 1]
      if (cur == next) {
        curLength = 1
      }
      else if (prev == null) {
        curLength = 2
      }
      else if (cur < prev) {
        if (next > cur) {
          curLength++
        }
        else {
          curLength = 2
        }
      }
      else { // cur > prev
        if (next < cur) {
          curLength++
        }
        else {
          curLength = 2
        }
      }
      maxSoFar = Math.max(maxSoFar, curLength)
    }
    return maxSoFar
  }
}