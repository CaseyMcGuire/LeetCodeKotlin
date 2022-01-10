package problems.problem1492

class Solution {
  fun kthFactor(n: Int, k: Int): Int {
    if (k == 1) {
      return 1
    }
    val isOdd = n % 2 == 1
    var numFactors = 1
    for (i in 2..n) {
      if (n % i == 0) {
        numFactors++
      }
      if (numFactors == k) {
        return i
      }
    }
    return -1
  }
}