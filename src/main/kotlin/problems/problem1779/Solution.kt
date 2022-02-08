package problems.problem1779

class Solution {
  fun nearestValidPoint(x: Int, y: Int, points: Array<IntArray>): Int {
    val currentLocation = Point(x, y, -1)
    val pointList = points.mapIndexed { i, elem -> Point(elem[0], elem[1], i) }
    val validPoints = pointList.filter { currentLocation.isValidTo(it) }
    val sortedPoints = validPoints.sortedWith(compareBy(
      { currentLocation.calculateManhattanDistance(it) },
      { it.index }
    ))
    return if (sortedPoints.isEmpty()) - 1 else sortedPoints[0].index
  }
}

data class Point(val x: Int, val y: Int, val index: Int) {
  fun calculateManhattanDistance(other: Point): Int {
    return Math.abs(this.x - other.x) + Math.abs(this.y - other.y)
  }

  fun isValidTo(other: Point): Boolean {
    return x == other.x || y == other.y
  }
}