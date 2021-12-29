package problems.problem1769

class Solution {
  fun minOperations(boxes: String): IntArray {
    var rightSum = 0
    var numRightOnes = 0
    for (i in boxes.indices.reversed()) {
      if (boxes[i] == '1') {
        rightSum += i
        numRightOnes += 1
      }
    }

    var leftSum = 0
    var numLeftOnes = 0
    val arr = IntArray(boxes.length)
    for (i in boxes.indices) {
      // need to remove box from right
      if (boxes[i] == '1') {
        numRightOnes--
      }
      arr[i] = rightSum + leftSum

      if (boxes[i] == '1') {
        numLeftOnes++
      }
      rightSum -= numRightOnes
      leftSum += numLeftOnes
    }
    return arr
  }
}