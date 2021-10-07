package problems.problem0939

class Solution {
  fun minAreaRect(points: Array<IntArray>): Int {
    val xPoints = mutableMapOf<Int, MutableSet<Point>>()
    val yPoints = mutableMapOf<Int, MutableSet<Point>>()
    val allPoints = mutableSetOf<Point>()
    for (p in points) {
      val point = Point(p[0], p[1])
      xPoints.add(point.x, point)
      yPoints.add(point.y, point)
      allPoints.add(point)
    }
    var maxSoFar: Int? = null
    val pointsList = allPoints.toList().sortedWith(
      compareBy(
        {-it.y},
        {it.x}
      ))
    for (current in pointsList) {
      val rightPoints = yPoints[current.y]!!
      val bottomPoints = xPoints[current.x]!!
      rightPoints.remove(current)
      bottomPoints.remove(current)
      for (rightPoint in rightPoints) {
        for (bottomPoint in bottomPoints) {
          val bottomRightPoint = Point(rightPoint.x, bottomPoint.y)
          if (allPoints.contains(bottomRightPoint)) {
            val height = current.y - bottomPoint.y
            val width = rightPoint.x - current.x
            val area = height * width
            if (maxSoFar == null || area < maxSoFar) {
              maxSoFar = area
            }
          }
        }
      }

    }
    return maxSoFar ?: 0
  }

  fun MutableMap<Int, MutableSet<Point>>.add(i: Int, p: Point) {
    val existing = this[i]
    if (existing == null) {
      this[i] = mutableSetOf<Point>(p)
      return
    }
    existing.add(p)
  }
}

data class Point(val x: Int, val y: Int)