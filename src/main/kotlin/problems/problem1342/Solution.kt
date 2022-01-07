package problems.problem1342

class Solution {
  fun numberOfSteps(num: Int): Int {
    var cur = num
    var numSteps = 0
    while (cur != 0) {

      if (cur % 2 == 0) {
        cur = cur / 2
      }
      else {
        cur = cur - 1
      }
      numSteps++
    }
    return numSteps
  }
}