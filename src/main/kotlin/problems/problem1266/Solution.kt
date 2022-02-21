package problems.problem1266

class Solution {
  fun minTimeToVisitAllPoints(points: Array<IntArray>): Int {
    if (points.isEmpty() || points.size == 1) {
      return 0
    }
    val pointList = points.map { Point(it[0], it[1]) }

    var currentPoint = pointList[0]
    var length = 0
    for (i in 1 until points.size) {
      val nextPoint = pointList[i]
      val xDistance = Math.abs(currentPoint.x - nextPoint.x)
      val yDistance = Math.abs(currentPoint.y - nextPoint.y)
      val diagonalMovements = Math.min(xDistance, yDistance)
      val straightMoves = Math.max(xDistance, yDistance) - diagonalMovements
      length += (diagonalMovements + straightMoves)
      currentPoint = nextPoint
    }
    return length
  }
}

data class Point(val x: Int, val y: Int)