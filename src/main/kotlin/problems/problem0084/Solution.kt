package problems.problem0084

import java.util.*

class Solution {
  // This really is a hard problem
  // The key observation is that one of the bars is the tallest bar
  // in the maximal rectangle. That is, for each bar, you just need
  // to find how far right and left it can go.
  // In this solution, we use an increasing monotonic stack to keep track of
  // the rectangles that are still growing. Once you hit an element that's less
  // than an element on the stack, that means the rectangles with that bar as its
  // can't grow anymore.
  fun largestRectangleArea(heights: IntArray): Int {
    val stack = ArrayDeque<Int>()
    var maxSoFar = 0
    stack.push(-1)
    for (i in heights.indices) {
      val curHeight = heights[i]
      // if any element on the stack is smaller than the current height,
      // it means that the rectangle with height can't grow anymore
      while (stack.peek() != -1 && heights[stack.peek()] >= curHeight) {
        val previousIndex = stack.pop()
        // Here, we're seeing how far back the current height goes
        // I.e. there may be multiple bars of the same height
        val width = i - stack.peek() - 1
        val rectangleArea = heights[previousIndex] * width
        maxSoFar = Math.max(rectangleArea, maxSoFar)
      }
      stack.push(i)
    }

    while (stack.peek() != -1) {
      val previousIndex = stack.pop()
      val width = heights.size - stack.peek() - 1
      val rectangleArea = width * heights[previousIndex]
      maxSoFar = Math.max(rectangleArea, maxSoFar)
    }

    return maxSoFar ?: -1
  }
}