package problems.problem0011

class Solution {
  fun maxArea(height: IntArray): Int {
    var leftPointer = 0
    var rightPointer = height.size - 1
    var maxArea = -1
    while (leftPointer < rightPointer) {
      val smallerHeight = height[leftPointer].coerceAtMost(height[rightPointer])
      val width = rightPointer - leftPointer
      val area = smallerHeight * width
      maxArea = maxArea.coerceAtLeast(area)
      if (height[leftPointer] < height[rightPointer]) {
        leftPointer++
      }
      else {
        rightPointer--
      }
    }
    return maxArea
  }
}