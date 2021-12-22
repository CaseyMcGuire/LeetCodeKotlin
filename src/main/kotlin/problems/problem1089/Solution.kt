package problems.problem1089

class Solution {
  fun duplicateZeros(arr: IntArray): Unit {
    var other = IntArray(arr.size)
    var j = 0
    for (i in arr.indices) {
      if (j >= other.size) {
        break
      }
      val elem = arr[i]
      if (elem == 0) {
        other[j] = 0
        j++
        if (j < other.size) {
          other[j] = 0
        }
        j++
      }
      else {
        other[j] = elem
        j++
      }
    }
    for (i in other.indices) {
      arr[i] = other[i]
    }

  }
}