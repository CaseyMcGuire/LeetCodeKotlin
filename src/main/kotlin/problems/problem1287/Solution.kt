package problems.problem1287

class Solution {
  fun findSpecialInteger(arr: IntArray): Int {
    if (arr.isEmpty()) {
      return -1
    }

    var quarterAmount = arr.size / 4

    var curNum = arr[0]
    var curFrequency = 1
    for (i in 1 until arr.size) {
      val num = arr[i]
      if (curNum == num) {
        curFrequency++
      }
      else {
        curNum = num
        curFrequency = 1
      }

      if (curFrequency > quarterAmount) {
        return curNum
      }
    }
    return curNum
  }
}