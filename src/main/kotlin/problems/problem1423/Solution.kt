package problems.problem1423

class Solution {
  fun maxScore(cardPoints: IntArray, k: Int): Int {
    var currentValue = 0
    for (i in 0 until k) {
      currentValue += cardPoints[i]
    }
    var maxValueSoFar = currentValue
    var endingIndex = cardPoints.size - 1
    var startingIndex = k - 1
    while (startingIndex >= 0) {
      currentValue -= cardPoints[startingIndex]
      currentValue += cardPoints[endingIndex]
      endingIndex--
      startingIndex--
      if (currentValue > maxValueSoFar) {
        maxValueSoFar = currentValue
      }
    }
    return maxValueSoFar
  }
}