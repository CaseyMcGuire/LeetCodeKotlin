package problems.problem1182

import java.util.*

class Solution {
  fun shortestDistanceColor(colors: IntArray, queries: Array<IntArray>): List<Int> {
    val colorToIndicesRight = mutableMapOf<Int, Deque<Int>>()
    val colorToIndicesLeft = mutableMapOf<Int, Int>()
    val queryList = queries.map { Query(it[0], it[1]) }
    val colorSet = mutableSetOf<Int>()

    // build map from right to left
    for (i in colors.size - 1 downTo 0) {
      colorSet.add(colors[i])
      val curStack = colorToIndicesRight.getOrDefault(colors[i], ArrayDeque())
      curStack.push(i)
      colorToIndicesRight[colors[i]] = curStack
    }

    val distancesToColors = mutableListOf<Map<Int, Int>>()
    // for each color at each index, find the closest distance to every other color and cache it
    // in a map for quick retrieval
    for (i in colors.indices) {
      val colorToClosestIndex = mutableMapOf<Int, Int>()
      for (c in colorSet) {
        val closestIndexRight = colorToIndicesRight[c]?.peek()
        val closestIndexLeft = colorToIndicesLeft[c]
        var distance =
          if (closestIndexRight != null && closestIndexLeft != null) {
            val rightDistance = Math.abs(i - closestIndexRight)
            val leftDistance = Math.abs(i - closestIndexLeft)
            if (rightDistance < leftDistance) rightDistance else leftDistance
          }
          else if (closestIndexRight != null) {
            Math.abs(i - closestIndexRight)
          }
          else if (closestIndexLeft != null) {
            Math.abs(i - closestIndexLeft)
          }
          else {
            -1
          }
        colorToClosestIndex[c] = distance
      }
      colorToIndicesLeft[colors[i]] = i
      colorToIndicesRight[colors[i]]?.pop()
      distancesToColors.add(colorToClosestIndex)
    }
    val distances = mutableListOf<Int>()
    for (query in queryList) {

      val distancesToColorMap = distancesToColors[query.index]
      distances.add(distancesToColorMap[query.color] ?: -1)
    }
    return distances
  }
}

data class Query(val index: Int, val color: Int)