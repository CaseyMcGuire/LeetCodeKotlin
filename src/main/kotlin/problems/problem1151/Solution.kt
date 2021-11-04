package problems.problem1151

class Solution {
  fun minSwaps(data: IntArray): Int {
    val numOnes = data.filter { it == 1 }.size
    if (numOnes == 0) {
      return 0
    }
    var numOnesInWindow = 0
    for (i in 0 until numOnes) {
      if (data[i] == 1) {
        numOnesInWindow++
      }
    }
    var smallestSwapsSoFar = numOnes - numOnesInWindow
    for (i in 1 until data.size - numOnes + 1) {

      if (data[i - 1] == 1) {
        numOnesInWindow--
      }
      if (data[i + numOnes - 1] == 1) {
        numOnesInWindow++
      }
      val numsInCurrentWindow = numOnes - numOnesInWindow
      if (numsInCurrentWindow < smallestSwapsSoFar) {
        smallestSwapsSoFar = numsInCurrentWindow
      }
    }
    return smallestSwapsSoFar
  }
}
