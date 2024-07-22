package problems.problem1732

class Solution {
  fun largestAltitude(gain: IntArray): Int {
    var sum = 0
    var highestSoFar = 0
    for (num in gain) {
      sum += num
      if (highestSoFar < sum) {
        highestSoFar = sum
      }
    }
    return highestSoFar
  }
}